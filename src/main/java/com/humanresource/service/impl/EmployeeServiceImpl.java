package com.humanresource.service.impl;

import com.humanresource.model.Employee;
import com.humanresource.repo.EmployeeRepo;
import com.humanresource.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @author umeshkhatiwada13@infodev
 * @project spring-security
 * @created 27/08/2022 - 17:45
 */
@Service
@RequiredArgsConstructor
//@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Override
    public Employee findById(Integer id) throws Exception {
        Optional<Employee> employeeOpt = employeeRepo.findById(id);
        if (!employeeOpt.isPresent()) {
            throw new Exception("Employee not Present");
        }
        return employeeOpt.get();
    }

    @Override
    public void save(Employee employee) {
        try {
            employeeRepo.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Integer id) {
        boolean success = false;
        try {
            Optional<Employee> employeeOpt = employeeRepo.findById(id);
            if (employeeOpt.isPresent() && employeeOpt.get().isActive()) {
                employeeRepo.updateDeletedStatusById(id);
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAllActive();
    }
}
