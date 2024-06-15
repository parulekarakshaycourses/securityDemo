package com.example.securityDemo.repo;

import com.example.securityDemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Long>
{
    Employee findByUsername(String username);
}
