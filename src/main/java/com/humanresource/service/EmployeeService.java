package com.humanresource.service;

import com.humanresource.model.Employee;

import java.util.List;

/**
 * @author umeshkhatiwada13@infodev
 * @project spring-security
 * @created 27/08/2022 - 17:44
 */
public interface EmployeeService {

    Employee findById(Integer id) throws Exception;

    void save(Employee employee);

    boolean delete(Integer id);

    List<Employee> findAll();

    String getHrEmail();
}
