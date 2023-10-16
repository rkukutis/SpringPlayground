package com.example.demo.repositories;

import com.example.demo.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

     List<Teacher> findTeacherByLastName(String lastName);
}