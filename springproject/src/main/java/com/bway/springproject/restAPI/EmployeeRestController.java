package com.bway.springproject.restAPI;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bway.springproject.model.Employee;
import com.bway.springproject.model.Product;
import com.bway.springproject.service.EmployeeService;
import com.bway.springproject.service.ProductService;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private ProductService productService;

	// Get All Employees Data
	@GetMapping("/list")
	public List<Employee> list() {
		return empService.getAllEmps();
	}

	// Get a particular employee's data
	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable long id) {
		return empService.getEmpById(id);
	}

	@PostMapping("/add")
	public String add(@RequestBody Employee emp) {
		empService.addEmp(emp);
		return "Data Added Successfully";
	}

	@PostMapping("/add/many")
	public String add(@RequestBody List<Employee> emps) {
		// Loop through the list and add each employee
		for (Employee emp : emps) {
			empService.addEmp(emp);
		}
		return "All Data Added Successfully";
	}

	@PutMapping("/update")
	public String updateEmployee(@RequestBody Employee emp) {
		empService.updateEmp(emp);
		return "Employee Data Updated";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable long id) {
		empService.deleteEmp(id);
		return "Employee Data Deleted Successfully";
	}

	//Fetching the data from the url below:
	@GetMapping("/saveProduct")
	public String saveProductDataInDb() {
		RestTemplate rt = new RestTemplate();
		Product[] plist = rt.getForObject("https://fakestoreapi.com/products", Product[].class);
		productService.saveAllProd(Arrays.asList(plist));
		return "Product Data Saved Successfully.";
	}

}
