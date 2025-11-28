package com.jt.sms.controller;

import com.jt.sms.model.Teacher;
import com.jt.sms.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/teacher")
public class TeacherController {
    private final TeacherService service;
    @PostMapping("/create")
    public Teacher createTeacher(@RequestBody Teacher newTeacher){
        return service.saveTeacher(newTeacher);
    }
    @PutMapping("/update/{id}")
    public Teacher updateTeacher(@PathVariable int id,@RequestBody Teacher toBeUpdated){
        return service.updatedTeacherById(id,toBeUpdated);
    }
}
