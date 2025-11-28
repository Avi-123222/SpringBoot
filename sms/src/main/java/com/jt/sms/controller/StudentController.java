package com.jt.sms.controller;

import com.jt.sms.dto.StudentDto;

import com.jt.sms.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@Tag(name ="Student API",
description = "This controller manages CRUD operation for Students")
public class StudentController {
    private final StudentService studentService;
@Operation(summary= "get all students",description = "Fetch All students")
    @GetMapping("/students")
    public List<StudentDto> getStudent() {

        return studentService.getStudents();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a new student")
    @ApiResponse(description = "Student validation failed",ref = "422",useReturnTypeSchema = true)
    public StudentDto createStudent(@RequestBody @Valid StudentDto dto) {
        System.out.println("////////" + dto);
        return studentService.saveStudent(dto);
    }

    @GetMapping("/student/{id}")

    public StudentDto getStudent(@PathVariable String id){
        return studentService.getStudent(id);

    }
    @GetMapping("student/roll/{roll}")
    public StudentDto getStudentByRoll(@PathVariable int roll){

            return studentService.getStudentByRoll(roll);



    }
    @DeleteMapping("/student/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public StudentDto deletetByid(@PathVariable String id){
        return studentService.deleteStudentById(id);
    }
    @PutMapping("/student/update/{id}")
    public StudentDto updateStudentById(@PathVariable String id,@RequestBody StudentDto dto){

            return studentService.updateStudentById(id,dto);
    }
    @PatchMapping("/student/update-by-field/{id}")
    public StudentDto updateStudentPatch(@PathVariable String id,@RequestBody StudentDto student){
        return studentService.partialUpdateStudentById(id,student);
    }


}

