package com.example.demo.mappers;

import com.example.demo.models.Teacher;
import com.example.demo.dto.TeacherDto;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {
    public Teacher toTeacher(TeacherDto teacherDto) {
        return new Teacher(teacherDto.getFirstName(), teacherDto.getLastName(), teacherDto.getTitle());
    }

}
