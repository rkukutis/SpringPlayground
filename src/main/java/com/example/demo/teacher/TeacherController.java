package com.example.demo.teacher;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Teacher> getStudents() {
        return teacherService.getAllTeachers();
    }

	@PostMapping
	@ResponseBody
	public ResponseEntity<String> addTeacher(@RequestBody TeacherDto teacherDto) {
		Teacher teacher = mapper.toTeacher(teacherDto);
		teacherService.addTeacher(teacher);
		return new ResponseEntity<>("New teacher added successfully", HttpStatus.OK);
	}
}
