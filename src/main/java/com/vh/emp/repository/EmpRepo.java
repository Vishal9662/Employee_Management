package com.vh.emp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vh.emp.model.Employee;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer>{
	
	public List<Employee> findEmployeeByposition(String position);
	
	public List<Employee> findEmployeeBydepartment(String department);
	
	
	
}
