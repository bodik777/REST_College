package com.bodik.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bodik.DAO.StudentDAO;
import com.bodik.logic.Student;

@Controller
@RequestMapping("/students")
public class RestController {

	@Autowired
	StudentDAO studentDAO;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addStudent(HttpServletResponse httpRes,
			@RequestBody Student student) {
		try {
			studentDAO.addEntity(student);
			httpRes.setStatus(HttpStatus.OK.value());
			return "Student added Successfully!";
		} catch (Exception e) {
			Logger.getLogger(RestController.class).error(
					"Error creating user!", e);
			httpRes.setStatus(HttpStatus.BAD_REQUEST.value());
			return "Error creating user!";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Student getStudent(HttpServletResponse httpRes,
			@PathVariable("id") long id) {
		Student student = null;
		try {
			student = studentDAO.getEntityById(id);
			if (student != null) {
				httpRes.setStatus(HttpStatus.OK.value());
			} else {
				httpRes.setStatus(HttpStatus.NOT_FOUND.value());
			}
		} catch (Exception e) {
			httpRes.setStatus(HttpStatus.NOT_FOUND.value());
			Logger.getLogger(RestController.class).error("Error loading data!",
					e);
			return student;
		}
		return student;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Student> getStudents(HttpServletResponse httpRes) {
		List<Student> studentList = null;
		try {
			studentList = studentDAO.getEntityAll();
			httpRes.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			httpRes.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			Logger.getLogger(RestController.class).error("Error loading data!",
					e);
		}
		return studentList;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteStudents(HttpServletResponse httpRes,
			@PathVariable("id") long id) {
		try {
			studentDAO.deleteEntity(id);
			httpRes.setStatus(HttpStatus.OK.value());
			return "Student deleted Successfully!";
		} catch (Exception e) {
			Logger.getLogger(RestController.class)
					.error("Failed to remove the student, a student may not exist!",
							e);
			httpRes.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return "Failed to remove the student, a student may not exist!!";
		}
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String updateStudent(HttpServletResponse httpRes,
			@RequestBody Student student) {
		try {
			studentDAO.updateEntity(student);
			httpRes.setStatus(HttpStatus.OK.value());
			return "Student updated Successfully!";
		} catch (Exception e) {
			Logger.getLogger(RestController.class).error(
					"Error updating user!", e);
			httpRes.setStatus(HttpStatus.BAD_REQUEST.value());
			return "Error updating user!";
		}
	}

}
