package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import teacher.Teacher;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student joe = new Student( "Joe",
                    LocalDate.of(1998, AUGUST, 6), "joe.schmoe@mail.com");
            Student max = new Student( "Max",
                    LocalDate.of(1995, AUGUST, 6), "max.Tothemax@mail.com");
            
            repository.saveAll(List.of(joe, max));
        };
    }


}
