package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;


@Configuration
public class StudentConfig {


    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student mariam = new Student(
                    "Ade",
                    "ade.dummy@gmail.com",
                    LocalDate.of(1999, 3, 23)
            );

            Student kelvin = new Student(
                    "kelvin",
                    "kelvin.dummy@gmail.com",
                    LocalDate.of(1997, 1, 21)
            );

            studentRepository.saveAll(List.of(mariam, kelvin));
        };
    }
}
