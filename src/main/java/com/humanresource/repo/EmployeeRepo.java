package com.humanresource.repo;

import com.humanresource.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author umeshkhatiwada13@infodev
 * @project spring-security
 * @created 27/08/2022 - 17:45
 */
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    @Query(value = "SELECT * from employee WHERE is_active= TRUE",
            nativeQuery = true)
    List<Employee> findAllActive();

    @Query(value = "UPDATE employee SET is_active= FALSE WHERE id = ?1",
            nativeQuery = true)
    void updateDeletedStatusById(Integer id);
}
