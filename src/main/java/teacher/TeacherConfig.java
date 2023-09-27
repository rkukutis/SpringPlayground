package teacher;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import teacher.Teacher;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class TeacherConfig {

    @Bean
    CommandLineRunner commandLineRunner(TeacherRepository repository) {
        return args -> {
            Teacher josh = new Teacher("Josh", "Berry", "Teacher");
            Teacher mary = new Teacher("Mary", "Kloss", "Teacher");
            repository.saveAll(List.of(josh, mary));
        };
    }


}
