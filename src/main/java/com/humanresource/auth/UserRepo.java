package com.humanresource.auth;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author umeshkhatiwada13@infodev
 * @project spring-security
 * @created 27/08/2022 - 21:34
 */
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
