package com.jt.sms.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.jt.sms.dto.StudentRequestDTO;
import com.jt.sms.exception.StudentNotFoundException;
import com.jt.sms.model.Student;
import com.jt.sms.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;

    public List<Student> getStudent() {
        return repository.findAll();
    }
    public Student saveStudent(Student newStudent){
       return repository.save(newStudent);
    }
    public Student getStudents(String id){
        return repository.findById(id).orElseThrow(()-> new StudentNotFoundException("student not found exception"+ id));
    }
    public Student getStudentByRoll(int roll){
        //try {
            return repository.findByRoll(roll).orElseThrow(()->new StudentNotFoundException("student not found with the roll" + roll));
       // }catch (NoSuchElementException e){
           // System.out.println("Student not Found");
            //return null;
        //}
    }
    public Student deleteStudentById(String id){

        Student existingStudent = getStudents(id);
        repository.deleteById(id);
        return existingStudent;
    }
    public Student updateStudentById(String id,Student updatedStudent){
        getStudents(id);
        updatedStudent.setId(id);
        return repository.save(updatedStudent);
    }

    public Student partialUpdateStudentById(String id, StudentRequestDTO updatedStudent) {
       Student existingStudents = getStudents(id);

        if(updatedStudent.getRoll() != null) existingStudents.setRoll(updatedStudent.getRoll());
        if(updatedStudent.getName() != null) existingStudents.setName(updatedStudent.getName());
        if(updatedStudent.getEmail() != null) existingStudents.setEmail(updatedStudent.getEmail());
        if(updatedStudent.getFee() != null) existingStudents.setFee(updatedStudent.getFee());
        if(updatedStudent.getPhoneNumber() != null) existingStudents.setPhoneNumber(updatedStudent.getPhoneNumber());

        return repository.save(existingStudents);


    }
}
