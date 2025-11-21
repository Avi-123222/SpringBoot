package com.jt.sms.service;

import java.util.List;
import com.jt.sms.model.Student;
import com.jt.sms.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;

    public List<Student> getStudents() {
        return repository.findAll();
    }
    public Student saveStudent(Student newStudent){
       return repository.save(newStudent);
    }
}
