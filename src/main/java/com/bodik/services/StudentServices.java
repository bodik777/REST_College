package com.bodik.services;

import java.util.List;

import com.bodik.logic.Student;

public interface StudentServices {

	public boolean addEntity(Student student) throws Exception;

	public boolean updateEntity(Student student) throws Exception;

	public Student getEntityById(long id) throws Exception;

	public List<Student> getEntityAll() throws Exception;

	public boolean deleteEntity(long id) throws Exception;
}