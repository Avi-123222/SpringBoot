package com.jt.sms.repository;

import com.jt.sms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,String> {
    Optional<Student> findByRoll(int roll);
}
