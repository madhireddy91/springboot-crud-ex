package com.javaguides.apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaguides.apis.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
