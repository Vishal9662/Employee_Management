package com.vh.emp.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.vh.emp.model.Employee;

@Repository
public interface EmpByPagination extends PagingAndSortingRepository<Employee, Integer>
{
	Page<Employee> findAll( Pageable pageable);
}
