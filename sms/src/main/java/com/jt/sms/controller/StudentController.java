package com.jt.sms.controller;

import com.jt.sms.dto.StudentRequestDTO;
import com.jt.sms.model.Student;
import com.jt.sms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/students")
    public List<Student> getStudent() {

        return studentService.getStudent();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student newStudent) {
        System.out.println("////////" + newStudent);
        return studentService.saveStudent(newStudent);
    }

    @GetMapping("/student/{id}")

    public Student getStudentInfo(@PathVariable String id){
        return studentService.getStudents(id);

    }
    @GetMapping("student/roll/{roll}")
    public Student getStudentByRoll(@PathVariable int roll){

            return studentService.getStudentByRoll(roll);



    }
    @DeleteMapping("/student/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Student deletetByid(@PathVariable String id){
        return studentService.deleteStudentById(id);
    }
    @PutMapping("/student/update/{id}")
    public Student updateStudentById(@PathVariable String id,@RequestBody Student student){

            return studentService.updateStudentById(id,student);
    }
    @PatchMapping("/student/update-by-field/{id}")
    public Student updateStudentPatch(@PathVariable String id,@RequestBody StudentRequestDTO student){
        return studentService.partialUpdateStudentById(id,student);
    }


}

