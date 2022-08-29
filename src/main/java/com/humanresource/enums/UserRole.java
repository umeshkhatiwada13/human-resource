package com.humanresource.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author umeshkhatiwada13@infodev
 * @project spring-security
 * @created 27/08/2022 - 17:00
 */
@AllArgsConstructor
@Getter
public enum UserRole {
    USER("ROLE_User"), HR("ROLE_HR"), ADMIN("ROLE_Admin");

    private final String role;
}
