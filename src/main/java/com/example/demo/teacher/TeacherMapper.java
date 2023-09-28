package com.example.demo.teacher;

import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {
    public Teacher toTeacher(TeacherDto teacherDto) {
        return new Teacher(teacherDto.getFirstName(), teacherDto.getLastName(), teacherDto.getTitle());
    }

}
