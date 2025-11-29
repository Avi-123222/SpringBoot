package com.jt.sms.service.impl;

import com.jt.sms.dto.StudentDto;
import com.jt.sms.exception.StudentNotFoundException;
import com.jt.sms.mapper.StudentMapper;
import com.jt.sms.model.Student;
import com.jt.sms.repository.StudentRepository;
import com.jt.sms.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService{
    private final StudentRepository repository;

    public List<StudentDto> getStudents() {
        List<StudentDto> studentDTOS = repository.findAll()
                .stream()
//                .map(student -> StudentMapper.convertStudentToStudentDTO(student))
                .map(StudentMapper :: convertStudentToStudentDto)
                .toList();

        return studentDTOS;
    }

    public StudentDto saveStudent(StudentDto dto){
        Student newStudent = StudentMapper.convertStudentDtoToStudent(dto);
        Student savedStudent = repository.save(newStudent);

        return StudentMapper.convertStudentToStudentDto(savedStudent);
    }

    public StudentDto getStudent(String id) {
//        Student existingStudent = repository.findById(id)
//                .orElseThrow(() -> new StudentNotFoundException("Student Not Found With id " + id));
        Student existingStudent = getStudentById(id);

        return StudentMapper.convertStudentToStudentDto(existingStudent);
    }

    public StudentDto getStudentByRoll(int roll) {
//        try {
//            return repository.findByRoll(roll).orElseThrow();
//        } catch (NoSuchElementException e) {
//            System.out.println("Student Not Found");
//            return null;
//        }

        Student existingStudent = repository.findByRoll(roll)
                .orElseThrow(() -> new StudentNotFoundException("Student Not Found with roll " + roll));

        return StudentMapper.convertStudentToStudentDto(existingStudent);
    }

    public StudentDto deleteStudentById(String id) {
        StudentDto existingStudentDTO = getStudent(id);
        repository.deleteById(id);
        return existingStudentDTO;
    }

    public StudentDto updateStudentById(String id, StudentDto dto) {
        //getStudent(id);
        getStudentById(id);

        Student toBeUpdated = StudentMapper.convertStudentDtoToStudent(dto);
        toBeUpdated.setId(id);
        Student updatedStudent = repository.save(toBeUpdated);

        return StudentMapper.convertStudentToStudentDto(updatedStudent);
    }

    public StudentDto partialUpdateStudentById(String id, StudentDto dto) {
//        StudentDto existingStudentDTO = getStudent(id);
//        Student existingStudent = StudentMapper.convertStudentDtoToStudent(dto);
//        existingStudent.setId(id);
        Student existingStudent = getStudentById(id);

        if(dto.getRoll() != null) existingStudent.setRoll(dto.getRoll());
        if(dto.getName() != null) existingStudent.setName(dto.getName());
        if(dto.getEmail() != null) existingStudent.setEmail(dto.getEmail());
        if(dto.getFee() != null) existingStudent.setFee(dto.getFee());
        if(dto.getPhoneNumber() != null) existingStudent.setPhoneNumber(dto.getPhoneNumber());

        Student updatedStudent = repository.save(existingStudent);
        return StudentMapper.convertStudentToStudentDto(updatedStudent);
    }
    private Student getStudentById(String id){
        return repository.findById(id).orElseThrow(()->new StudentNotFoundException("Student not Found"));
    }
}