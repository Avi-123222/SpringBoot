package com.jt.sms.service;

import com.jt.sms.dto.StudentDto;

import java.util.List;

public interface IStudentService {
    List<StudentDto>  getStudents();
    StudentDto saveStudent(StudentDto dto);
    StudentDto getStudent(String id);
    StudentDto getStudentByRoll(int roll);
    StudentDto deleteStudentById(String id);
    StudentDto updateStudentById(String id, StudentDto Dto);
    StudentDto partialUpdateStudentById(String id, StudentDto Dto);
}
