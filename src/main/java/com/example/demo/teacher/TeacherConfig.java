package com.example.demo.teacher;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;


@Configuration
public class TeacherConfig {

    @Bean
    CommandLineRunner commandLineRunnerTeacher(TeacherRepository repository) {
        return args -> {
            Teacher mary = new Teacher("Mary", "Kloss", "Teacher");
            Teacher josh = new Teacher("Josh", "Berry", "Teacher");
            Teacher kane = new Teacher("Kane", "Berry", "Teacher");
            repository.saveAll(List.of(josh, mary, kane));
        };
    }


}
