package com.bodik.resources;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bodik.logic.Student;
import com.bodik.services.StudentServices;


@Controller
@RequestMapping("/students")
public class RestController {

	@Autowired
	StudentServices studentServices;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addStudent(@RequestBody Student student) {
		try {
			studentServices.addEntity(student);
			return new ResponseEntity<String>("Student added Successfully!",
					HttpStatus.CREATED);
		} catch (Exception e) {
			Logger.getLogger(RestController.class).error(
					"Error creating user!", e);
			return new ResponseEntity<String>("Error creating user!",
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Student> getStudent(@PathVariable("id") long id) {
		Student student = null;
		try {
			student = studentServices.getEntityById(id);
			if (student != null) {
				return new ResponseEntity<Student>(student, HttpStatus.OK);
			} else {
				return new ResponseEntity<Student>(student,
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			Logger.getLogger(RestController.class).error("Error loading data!",
					e);
			return new ResponseEntity<Student>(student, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Student>> getStudents() {
		List<Student> studentList = null;
		try {
			studentList = studentServices.getEntityAll();
			return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
		} catch (Exception e) {
			Logger.getLogger(RestController.class).error("Error loading data!",
					e);
			return new ResponseEntity<List<Student>>(studentList,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteStudents(@PathVariable("id") long id) {
		try {
			studentServices.deleteEntity(id);
			return new ResponseEntity<String>("Student deleted Successfully!",
					HttpStatus.OK);
		} catch (Exception e) {
			Logger.getLogger(RestController.class)
					.error("Failed to remove the student, a student may not exist!",
							e);
			return new ResponseEntity<String>(
					"Failed to remove the student, a student may not exist!",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateStudent(@RequestBody Student student) {
		try {
			studentServices.updateEntity(student);
			return new ResponseEntity<String>("Student updated Successfully!",
					HttpStatus.OK);
		} catch (Exception e) {
			Logger.getLogger(RestController.class).error(
					"Error updating Student!", e);
			return new ResponseEntity<String>("Error updating Student!",
					HttpStatus.BAD_REQUEST);
		}
	}

}
