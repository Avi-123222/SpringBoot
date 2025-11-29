package com.jt.sms.controller;

import com.jt.sms.dto.StudentDto;
import com.jt.sms.service.IStudentService;
import com.jt.sms.service.impl.StudentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Student API",
        description = "This controller manages CRUD operation for Students"
)
public class StudentController {
    private final IStudentService service;

    @Operation(summary = "Get All Students", description = "Fetch All Students")
    @GetMapping("/students")
    public List<StudentDto> getStudents() {
        return service.getStudents();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a new Student")
    @ApiResponse(description = "Student vaidation failed", responseCode = "422")
    public StudentDto createStudent(@RequestBody @Valid StudentDto dto) {
        return service.saveStudent(dto);
    }

    @GetMapping("/student/{id}")
    public StudentDto getStudent(@PathVariable String id) {
        return service.getStudent(id);
    }

    @GetMapping("/student/roll/{roll}")
    public StudentDto getStudentByRoll(@PathVariable int roll) {
        return service.getStudentByRoll(roll);
    }

    @DeleteMapping("/student/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public StudentDto deleteById(@PathVariable String id) {
        return service.deleteStudentById(id);
    }

    @PutMapping("/student/update/{id}")
    public StudentDto updateStudent(@PathVariable String id, @RequestBody StudentDto dto                    ){
        return service.updateStudentById(id, dto);
    }

    @PatchMapping("/student/update-by-field/{id}")
    public StudentDto updateStudentPatch(@PathVariable String id,
                                         @RequestBody StudentDto student) {
        return service.partialUpdateStudentById(id, student);
    }
}