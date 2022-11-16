package com.vh.emp.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vh.emp.model.Employee;
import com.vh.emp.service.EmpService;

@Controller
public class EmpController {
	
	@Autowired
	private EmpService service;
	

	@RequestMapping("/")
	public String registerEmployee(@ModelAttribute("employee")Employee employee, Model model)
	{
		return "register";
	}
	
	@RequestMapping("/registered")
	public String register(Model model)
	{		
		List<Employee> empList = service.listAll();
		model.addAttribute("listAll",empList);
		
		return "home";
	}
		
	@RequestMapping("/save")
	public String saveEmployee(@ModelAttribute("employee")Employee employee, Model model, BindingResult result)
	{
		if(employee!=null)
			{
			service.registerEmp(employee);
			return "saveddata";
			}
		else 
			return "emptyform";
	}
	
	@RequestMapping("/deleteEmp")
	public String deleteEmployee()
	{
		return "delete";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam int id)
	{
		boolean res=service.empDelete(id);
		if(res==true)
			return "deletesuccess";
		else 
			return "empnotfound";
	}
	
	@RequestMapping("/editEmp")
	public String editEmp()
	{
		return "edit";
	}
	
	@RequestMapping("/edit")
	public String edit(@ModelAttribute("employee") Employee employee)
	{
		Employee e1=service.empEdit(employee);
		
		if(e1 != null)
		{
			return "editsuccess";
			
		}
		else
		{
			return "notedit";
		}
	}
	
	
}
