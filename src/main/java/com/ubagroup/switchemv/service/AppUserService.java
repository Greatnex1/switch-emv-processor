package com.ubagroup.switchemv.service;

import com.ubagroup.switchemv.dto.LoginRequest;
import com.ubagroup.switchemv.dto.LoginResponse;
import com.ubagroup.switchemv.dto.RegisterRequest;
import com.ubagroup.switchemv.model.AppUser;
import com.ubagroup.switchemv.model.RefreshToken;
//import com.ubagroup.switchemv.model.Role;
import com.ubagroup.switchemv.model.Wallet;
import com.ubagroup.switchemv.model.enums.RoleType;
import com.ubagroup.switchemv.repository.AppUserRepository;
import com.ubagroup.switchemv.repository.RefreshTokenRepository;
//import com.ubagroup.switchemv.repository.RoleRepository;
import com.ubagroup.switchemv.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jpos.q2.cli.builtin.USERDATA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository userRepo;
//    private final RoleRepository roleRepository;
    private final WalletRepository walletRepo;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

//    @Value("jwt.expiry")
    private String jwtExpiry;
    @Transactional
    public void register(RegisterRequest req) {

        if (userRepo.existsByUsername(req.getUsername()))
            throw new IllegalArgumentException("Username already exists");

//        RoleType roleType = RoleType.valueOf(req.getRole());
//
//        Role role = roleRepository.findByRoleType(roleType);
//                .orElseThrow(() ->
//                        new IllegalArgumentException("Role not found: " + roleType)
//                );
        AppUser user = new AppUser();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
         user.setRole(RoleType.valueOf(req.getRole()));
//        user.setRole(role);

        userRepo.save(user);

        Wallet wallet = new Wallet();
//        wallet.setUserId(user.getId());
        wallet.setBalance(BigDecimal.ZERO);
   user.setWallet(wallet);
        walletRepo.save(wallet);
    }

    @Transactional
    public LoginResponse login(LoginRequest req) {

        AppUser user = userRepo.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

//        AppUser user = userRepo.findByUsernameWithAuthorities(req.getUsername().toLowerCase())
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        if (!passwordEncoder.matches(req.getPassword(), user.getPassword()))
            throw new IllegalArgumentException("Invalid credentials");

        String token = jwtUtil.generate(user);
//        String roleName = user.getRole() != null ? user.getRole().getRoleType().name() : null;

       System.out.println("Role: " + user.getRole());
        return LoginResponse.builder()
                .accessToken(token)
                .refreshToken(token)
                .username(user.getUsername())
                .role(RoleType.valueOf(user.getRole().toString()))
                .wallets(List.of(user.getWallet()))
                .build();
    }

    public RefreshToken createRefreshToken(AppUser appUser) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(appUser);
        refreshToken.setToken(jwtUtil.generate(appUser));
        refreshToken.setExpiryDate(Instant.now());

       return refreshTokenRepository.save(refreshToken);

    }

    @Transactional
    public void logout(String username) {
        AppUser user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // delete all refresh tokens for this user
        refreshTokenRepository.deleteByUser(user);
    }
}
