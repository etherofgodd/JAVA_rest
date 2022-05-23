package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentEmail = studentRepository.findStudentByEmail(student.getEmail());

        if (studentEmail.isPresent()) {
            throw new IllegalStateException("Email Already Exists.");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean studentExists = studentRepository.existsById(studentId);
        if (!studentExists) {
            throw new IllegalStateException("Student id: " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String email, String name) {
        Student student  = studentRepository.findById(studentId).orElseThrow(
                ()->new IllegalArgumentException("Student with id: "+ studentId+" Does not exists"));

        if(name != null &&
        name.length()> 0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email != null &&
                email.length()> 0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalArgumentException("Email has been taken");
            }
            student.setEmail(email);
        }

    }
}
