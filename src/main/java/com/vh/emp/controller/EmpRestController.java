package com.vh.emp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vh.emp.model.Employee;
import com.vh.emp.service.EmpService;

@RestController
@RequestMapping("/rest")
public class EmpRestController {

	@Autowired
	private EmpService service;
	

	@RequestMapping("/get/by/filter")
	public ResponseEntity<List<Employee>> getBasedOnFilter(@RequestBody Map<String, String> request) {
		List<Employee> emp = new ArrayList<>();
		if (request.containsKey("department")) {
			
			emp = service.getEmployeesByDepartment(request.get("department"));

		} else if (request.containsKey("position")) {
			
			emp = service.getEmployeeByPosition(request.get("position"));
			
		} else if (request.containsKey("pagination_limit")) {

			emp = service.getDataByPage(0, Integer.parseInt(request.get("pagination_limit")));

		}
		
		if (emp.size() > 0) {
			return ResponseEntity.ok(emp);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	
	
	@RequestMapping("/get/by/pages")
	public ResponseEntity<List<Employee>> getBasedOnPage(@RequestParam("pageNumber") int number,
			@RequestParam("noOfRecords") int recordsPerPage) {
		List<Employee> emp = service.getDataByPage(number, recordsPerPage);
		return emp.size() > 0 ? ResponseEntity.ok(emp) : ResponseEntity.notFound().build();

	}
}
