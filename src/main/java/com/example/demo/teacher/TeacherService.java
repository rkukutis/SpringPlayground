package com.example.demo.teacher;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
	private final TeacherRepository teacherRepository;

	@Autowired
	public TeacherService(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

	public List<Teacher> getTeacher(String lastName) {
		return teacherRepository.findTeacherByLastName(lastName);
	}

	public void addTeacher(Teacher teacher){
		teacherRepository.save(teacher);
	}

}
