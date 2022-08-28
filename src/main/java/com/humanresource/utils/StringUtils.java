package com.humanresource.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author umeshkhatiwada13@infodev
 * @project human-resource
 * @created 28/08/2022 - 17:48
 */
public class StringUtils {

    public static final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
}
