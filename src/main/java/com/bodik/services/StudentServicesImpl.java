package com.bodik.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bodik.dao.StudentDAO;
import com.bodik.logic.Student;

public class StudentServicesImpl implements StudentServices {

	@Autowired
	StudentDAO studentDAO;

	@Override
	public boolean addEntity(Student student) throws Exception {
		return studentDAO.addEntity(student);
	}

	@Override
	public boolean updateEntity(Student student) throws Exception {
		return studentDAO.updateEntity(student);
	}

	@Override
	public Student getEntityById(long id) throws Exception {
		return studentDAO.getEntityById(id);
	}

	@Override
	public List<Student> getEntityAll() throws Exception {
		return studentDAO.getEntityAll();
	}

	@Override
	public boolean deleteEntity(long id) throws Exception {
		return studentDAO.deleteEntity(id);
	}

}
