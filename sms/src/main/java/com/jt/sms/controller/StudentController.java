package com.jt.sms.controller;

import com.jt.sms.model.Student;
import com.jt.sms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/students")
    public List<Student> getStudent() {

        return studentService.getStudents();
    }
}
