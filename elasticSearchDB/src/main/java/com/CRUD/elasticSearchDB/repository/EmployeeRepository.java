package com.CRUD.elasticSearchDB.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.CRUD.elasticSearchDB.model.Employee;

public interface EmployeeRepository extends ElasticsearchRepository<Employee, String> {

	List<Employee> findByFirstname(String firstName);

	Optional<Employee> findById(String id);

}