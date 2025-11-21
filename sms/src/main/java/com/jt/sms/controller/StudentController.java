package com.jt.sms.controller;

import com.jt.sms.model.Student;
import com.jt.sms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/students")
    public List<Student> getStudent() {

        return studentService.getStudents();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student newStudent) {
        System.out.println("////////" + newStudent);
        return studentService.saveStudent(newStudent);
    }
}
