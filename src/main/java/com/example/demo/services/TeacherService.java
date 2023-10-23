package com.example.demo.services;
import java.util.List;
import java.util.logging.Logger;

import com.example.demo.models.Teacher;
import com.example.demo.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
	private final TeacherRepository teacherRepository;

	@Autowired
	public TeacherService(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

	public Page<Teacher> getAllTeachers(Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		System.out.print("INFO: ");
		System.out.println(page);
		System.out.println(pageSize);
		return teacherRepository.findAll(pageable);
	}

	public List<Teacher> getTeacher(String lastName) {
		return teacherRepository.findTeacherByLastName(lastName);
	}

	public void addTeacher(Teacher teacher){
		teacherRepository.save(teacher);
	}

}
