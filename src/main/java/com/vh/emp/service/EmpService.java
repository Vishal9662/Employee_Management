package com.vh.emp.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vh.emp.model.Employee;
import com.vh.emp.repository.EmpRepo;


@Service
public class EmpService {

	@Autowired
	private EmpRepo empRepo;
	
	
	public List<Employee> listAll()
	{
		return empRepo.findAll();
	}
	
	public Employee registerEmp(Employee employee)
	{

		return empRepo.save(employee);
	}
	
	public boolean empDelete(int id)
	{
		Optional<Employee> e=empRepo.findById(id);
		if(e.isPresent())
		{
			empRepo.deleteById(id);
			return true;
		}
		else
			return false;
	}
	
	public Employee empEdit(Employee employee)
	{
		int id = employee.getId();
		Employee e1=empRepo.getById(id);
		
		if(e1!=null)
		{
			e1.setFirst_name(employee.getFirst_name());
			e1.setLast_name(employee.getLast_name());
			e1.setSalary(employee.getSalary());
			e1.setPosition(employee.getPosition());
			e1.setDepartment(employee.getDepartment());
			e1.setEmail_address(employee.getEmail_address());
			e1.setContact_number(employee.getContact_number());

			return 	empRepo.save(e1);
			
		}
		else
		{
			return null;
		}
	
	}
	
	// ----------------- REST API for filter Data By Department -----------------
	
	public List<Employee> getEmployeesByDepartment(String department) {
		List<Employee> emp= empRepo.findEmployeeBydepartment(department);
		return emp;
	}
	
	// ----------------- REST API for filter Data By Position -----------------

	public List<Employee> getEmployeeByPosition(String position)
	{
		List<Employee> emp = empRepo.findEmployeeByposition(position);
		return emp;
	}

	
	
		
	public List<Employee> getDataByPage(int pageNumber,int numberOfRecords)
	{
		Pageable firstPageWithTwoElements = PageRequest.of(pageNumber, numberOfRecords);
		
		Page<Employee> result= empRepo.findAll(firstPageWithTwoElements);
		if(result.getSize()>0)
			
		{
			return result.toList();
		}
		else
		{
			return null;
		}
		
	}


}
