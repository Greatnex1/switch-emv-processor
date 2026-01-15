package com.ubagroup.switchemv.api;

import com.ubagroup.switchemv.constant.SystemMessage;
import com.ubagroup.switchemv.dto.ApiResponse;
import com.ubagroup.switchemv.dto.LoginRequest;
import com.ubagroup.switchemv.dto.LoginResponse;
import com.ubagroup.switchemv.dto.TokenResponse;
import com.ubagroup.switchemv.service.AppUserService;
import com.ubagroup.switchemv.service.JwtUtil;
import com.ubagroup.switchemv.util.ApiResponseHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ubagroup.switchemv.dto.ApiResponse.success;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final AppUserService userService;
//    private final ApiResponseHelper responseHelper;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> authenticate(@RequestBody LoginRequest clientAuthRequestDto) {
        log.info("username {}", clientAuthRequestDto.getUsername() + " is logged in");

        LoginResponse response = userService.login(clientAuthRequestDto);

        log.info("response {}", response);

    return ApiResponseHelper.ok(SystemMessage.LOGIN, response);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(Authentication authentication) {

        String username = authentication.getName();

        userService.logout(username);

        return ResponseEntity.ok(success("Logged out successfully")
        );

    }

}
