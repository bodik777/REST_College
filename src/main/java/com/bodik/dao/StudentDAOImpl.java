package com.bodik.dao;

import java.util.List;

import com.bodik.logic.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentDAOImpl implements StudentDAO {
	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addEntity(Student student) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(student);
		tx.commit();
		session.close();
		return false;
	}

	@Override
	public boolean updateEntity(Student student) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.update(student);
		tx.commit();
		session.close();
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getEntityAll() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Student> studentList = session.createCriteria(Student.class)
				.list();
		tx.commit();
		session.close();
		return studentList;
	}

	@Override
	public Student getEntityById(long id) throws Exception {
		session = sessionFactory.openSession();
		Student student = (Student) session.get(Student.class, new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return student;
	}

	@Override
	public boolean deleteEntity(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Student.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

}