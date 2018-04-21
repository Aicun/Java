package com.lac.dao;

import java.util.List;

public class StudentDaoImpl implements StudentDao {
	
	private List<Student> stuList;

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return stuList;
	}

	@Override
	public Student getStudent(int rollNo) {
		// TODO Auto-generated method stub
		return stuList.get(rollNo);
	}

	@Override
	public void updateStudent(Student student) {
		stuList.get(student.getRollNo()).setName(student.getName());
		System.out.println("Student: Roll No " + student.getRollNo() + ", updated in the database");
	}

	@Override
	public void deleteStudent(Student student) {
		stuList.remove(student.getRollNo());
	}

}
