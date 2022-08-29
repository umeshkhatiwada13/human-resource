package com.humanresource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

/**
 * @author umeshkhatiwada13@infodev
 * @project human-resource
 * @created 29/08/2022 - 07:18
 */

@Getter
@Setter
public class EmployeePojo {
    private Integer id;
    private String name;

    private String address;

    private String gender;

    private Integer age;
    private Integer years;
    private String position;
    private String employmentStatus;
    private float salary;

    private String username;

    private String password;

    private String role;
}
