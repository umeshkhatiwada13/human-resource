package com.humanresource.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author umeshkhatiwada13@infodev
 * @project demo1
 * @created 06/07/2022 - 5:37 PM
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    private String address;

    private String gender;

    private Integer age;

    //Joining year in company
    private Integer years;

    /*@Basic(optional = false)
    @Column(name = "date_of_birth", length = 15)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Basic(optional = false)
    @Column(name = "joiningDate", length = 15)
    @Temporal(TemporalType.DATE)
    private Date joiningDate;*/

    private String position;

    @Column(name = "employment_status")
    private String employmentStatus;

    private float salary;

    /*@OneToOne
    @JoinColumn(name = "user_id")
    private ApplicationUser user;*/

    @Column(name = "is_active")
    boolean isActive = true;
}
