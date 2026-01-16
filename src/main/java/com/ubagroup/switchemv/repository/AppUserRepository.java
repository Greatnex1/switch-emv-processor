package com.ubagroup.switchemv.repository;

import com.ubagroup.switchemv.model.AppUser;
import com.ubagroup.switchemv.model.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
        Optional<AppUser> findByUsername(String username);
         boolean existsByUsername(String username);

//    @Transactional(readOnly = true)
//    @Query("""
//    SELECT DISTINCT u
//    FROM AppUser u
//    JOIN FETCH u.role r
//    LEFT JOIN FETCH r.permissions
//    WHERE LOWER(u.username) = LOWER(:username)
//""")
//    Optional<AppUser> findByUsernameWithAuthorities(@Param("username") String username);

//    Optional<AppUser> findByUsernameWithAuthorities(@Param("username") String username);


//    @Query("""
//              SELECT  u FROM AppUser u
//              JOIN FETCH u.role r
//              JOIN FETCH r.permissions WHERE u.username = :username
//              """)
//
//    Optional<AppUser> findByUsernameWithAuthorities(String username);
//
//    @Query("""
//              SELECT  u FROM Role r
//              JOIN FETCH r.permissions WHERE r.roleType = :roleType
//              """)
//
//    Optional<AppUser> findByTypeWithPermissions(RoleType roleType);
}
