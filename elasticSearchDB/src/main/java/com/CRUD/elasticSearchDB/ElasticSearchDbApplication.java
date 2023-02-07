package com.CRUD.elasticSearchDB;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.CRUD.elasticSearchDB.model.Employee;
import com.CRUD.elasticSearchDB.repository.EmployeeRepository;


@SpringBootApplication
@RestController
public class ElasticSearchDbApplication {
	
	@Autowired
	private EmployeeRepository repository;
	
/*
	http://localhost:8090/saveEmployee
		[
		{
		    "id":,
		    "firstname":"",
		    "lastname":"",
		    "age": 
		}
		]
 */
	@PostMapping("/saveEmployee")
	public int saveEmployee(@RequestBody List<Employee> employees) {
		System.out.println(employees.toString());
		repository.saveAll(employees);
		return employees.size();
	}

//	http://localhost:8090/findEmpList
	@GetMapping("/findEmpList")
	public Iterable<Employee> findAllEmployees() {
		return repository.findAll();
	}

//	http://localhost:8090/findByFName/{firstName}
	@GetMapping("/findByFName/{firstName}")
	public List<Employee> findByFirstName(@PathVariable String firstName) {
		return repository.findByFirstname(firstName);
	}
	
//	http://localhost:8090/findById/{id}
	@GetMapping("/findById/{id}")
	public Optional<Employee> findById(@PathVariable String id) {
		return repository.findById(id);
	}
	
//	http://localhost:8090/deleteEmp/{id}
	@DeleteMapping("/deleteEmp/{id}")
	public Optional<Employee> deleteEmployee(@PathVariable String id){
		Optional<Employee> Emp=repository.findById(id);
		repository.deleteById(id);
		return Emp;
	}
	
	@PutMapping("/updateEmp")
	public Employee updateEmployee(@RequestBody Employee CurrEmployee){
		Employee upEmployee=repository.findById(CurrEmployee.getId()).get();
		upEmployee.setFirstname(CurrEmployee.getFirstname());
		upEmployee.setLastname(CurrEmployee.getLastname());
		upEmployee.setAge(CurrEmployee.getAge());
		return repository.save(upEmployee);
	}
	

	public static void main(String[] args) {
		SpringApplication.run(ElasticSearchDbApplication.class, args);
	}
}