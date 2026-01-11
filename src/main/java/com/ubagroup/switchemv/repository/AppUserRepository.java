package com.ubagroup.switchemv.repository;

import com.ubagroup.switchemv.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
        Optional<AppUser> findByUsername(String username);
         boolean existsByUsername(String username);
}
