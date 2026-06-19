package com.bway.springproject.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springproject.model.Department;
import com.bway.springproject.repository.DepartmentRepository;
import com.bway.springproject.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentRepository deptRepo;

	@Override
	public void addDept(Department dept) {
		deptRepo.save(dept);
	}

	@Override
	public void updateDept(Department dept) {
		deptRepo.save(dept);
	}

	@Override
	public void deleteDept(int id) {
		deptRepo.deleteById(id);
	}

	@Override
	public List<Department> getAllDepts() {

		return deptRepo.findAll();
	}

	@Override
	public Department getDeptByID(int id) {
		return deptRepo.findById(id).get(); // .get() returns the value if present and null if not
	}

}
