package com.javaguides.apis.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javaguides.apis.entity.Employee;
import com.javaguides.apis.exception.ResourceNotFoundException;
import com.javaguides.apis.repository.EmployeeRepository;
import com.javaguides.apis.service1.EmployeeService;



@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}


	@Override
	public Employee saveEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}


	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();
	}


	@Override
	public Employee getEmployeeById(long id) {
//		
//		Optional<Employee> employee = employeeRepository.findById(id);
//		if(employee.isPresent()) {
//			return employee.get();
//		}
//		else {
//			
//			throw new ResourceNotFoundException("Employee", "id", id);
//		
//		}
		
		// using Lambda expression
		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
	}


	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// we need check whether given emp id exist in DB or not
		
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "id", id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		employeeRepository.save(existingEmployee);
		
		
		return existingEmployee;
	}


	@Override
	public void deleteEmployee(long id) {
		//check whether a emp exist in DB or not
		employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "id", id));
		employeeRepository.deleteById(id);
		
	}

}
