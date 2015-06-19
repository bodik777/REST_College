package com.bodik.logic;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "students")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "year_of_study")
	private Byte year_of_study;

	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "is_stipend")
	private Boolean is_stipend;

	@Column(name = "gpa")
	private Float gpa;

	public Student() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getYear_of_study() {
		return year_of_study;
	}

	public void setYear_of_study(Byte year_of_study) {
		this.year_of_study = year_of_study;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Boolean getIs_stipend() {
		return is_stipend;
	}

	public void setIs_stipend(Boolean is_stipend) {
		this.is_stipend = is_stipend;
	}

	public Float getGpa() {
		return gpa;
	}

	public void setGpa(Float gpa) {
		this.gpa = gpa;
	}

}
