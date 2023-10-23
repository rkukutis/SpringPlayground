package com.example.demo.controllers;
import java.util.List;

import com.example.demo.Exceptions.PaginationException;
import com.example.demo.mappers.TeacherMapper;
import com.example.demo.models.Teacher;
import com.example.demo.services.TeacherService;
import com.example.demo.dto.TeacherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/teacher")
public class TeacherController {
	private final TeacherService teacherService;
	private final TeacherMapper mapper;

	@Autowired
	public TeacherController(TeacherService teacherService, TeacherMapper mapper) {
		this.teacherService = teacherService;
		this.mapper = mapper;
	}

	@GetMapping
    public Page<Teacher> getTeachers(@RequestParam(name = "page", required = false)Integer page,
									 @RequestParam(name = "pageSize", required = false)Integer pageSize) {

		if (page == null || pageSize == null) {
			// defaults
			return teacherService.getAllTeachers(0, 10);
		}
		if (page < 1 || pageSize < 1) {
			throw new PaginationException("page and pageSize must be positive numbers");
		}
        return teacherService.getAllTeachers(page-1, pageSize);
    }

	@GetMapping(path = "{lastName}")
	public List<Teacher> getTeacherByLastName(@PathVariable("lastName")String lastName) {
		return teacherService.getTeacher(lastName);
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<String> addTeacher(@RequestBody TeacherDto teacherDto) {
		Teacher teacher = mapper.toTeacher(teacherDto);
		teacherService.addTeacher(teacher);
		return new ResponseEntity<>("New teacher added successfully", HttpStatus.OK);
	}
}
