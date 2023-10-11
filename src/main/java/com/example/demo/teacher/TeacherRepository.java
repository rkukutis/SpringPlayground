package com.example.demo.teacher;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

     List<Teacher> findTeacherByLastName(String lastName);
}