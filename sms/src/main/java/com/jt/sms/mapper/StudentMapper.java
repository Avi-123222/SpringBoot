package com.jt.sms.mapper;

import com.jt.sms.dto.StudentDto;
import com.jt.sms.model.Student;
import org.springframework.beans.BeanUtils;

public class StudentMapper {
    private StudentMapper(){}
    public static Student convertStudentDtoToStudent(StudentDto dto){
        Student student = new Student();
        BeanUtils.copyProperties(dto, student);
        return student;
    }
    public static StudentDto convertStudentToStudentDto(Student student){
        StudentDto dto = new StudentDto();
         BeanUtils.copyProperties(student,dto);
        return dto;
    }
}
