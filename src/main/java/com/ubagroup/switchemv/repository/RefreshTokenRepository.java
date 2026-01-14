package com.ubagroup.switchemv.repository;

import com.ubagroup.switchemv.model.AppUser;
import com.ubagroup.switchemv.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    void deleteByUser(AppUser user);

    Optional<RefreshToken> findByToken(String token);
}
