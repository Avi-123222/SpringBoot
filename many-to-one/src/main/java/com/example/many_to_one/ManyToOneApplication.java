package com.example.many_to_one;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class ManyToOneApplication {

	private final SubjectRepository subjectRepository;
	private final TeacherRepository teacherRepository;

	public static void main(String[] args) {
		SpringApplication.run(ManyToOneApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			// var sub1 = Subject.builder().subjectName("C").build();
			// var sub2 = Subject.builder().subjectName("C++").build();
			// var sub3 = Subject.builder().subjectName("Python").build();
			// var sub4 = Subject.builder().subjectName("Java").build();
			// var sub5 = Subject.builder().subjectName("JavaScript").build();
			// var sub6 = Subject.builder().subjectName(".Net").build();

			// var teacher1 = Teacher.builder().teacherName("Rashmi Sir").build();
			// var teacher2 = Teacher.builder().teacherName("Kaushik sir").build();
			// var teacher3 = Teacher.builder().teacherName("Abinash sir").build();

			// sub1.setTeacher(teacher1);
			// sub2.setTeacher(teacher1);
			// sub3.setTeacher(teacher2);
			// sub4.setTeacher(teacher2);
			// sub5.setTeacher(teacher3);
			// sub6.setTeacher(teacher3);

			// subjectRepository.saveAll(List.of(sub1, sub2, sub3, sub4, sub5, sub6));

			// var existingSubject = subjectRepository.findById(1).orElseThrow();
			// existingSubject.setSubjectName("C2");
			// existingSubject.getTeacher().setTeacherName("Rashmi sir 2");
			// subjectRepository.save(existingSubject);

			// var existingSubject = subjectRepository.findById(2).orElseThrow();
			// subjectRepository.deleteById(existingSubject.getSubjectId());
			// teacherRepository.deleteById(existingSubject.getTeacher().getTeacherId());

			// var existingSubject1 = subjectRepository.findById(3).orElseThrow();
			// // var existingSubject2 = subjectRepository.findById(4).orElseThrow();
			// // subjectRepository.deleteAll(List.of(existingSubject1, existingSubject2));
			// // we cannot delete both at a time
			// subjectRepository.deleteAll(List.of(existingSubject1));

			// =======================Bidirectional Mapping ====================
			// var sub1 = Subject.builder().subjectName("C").build();
			// var sub2 = Subject.builder().subjectName("C++").build();
			// var sub3 = Subject.builder().subjectName("Python").build();
			// var sub4 = Subject.builder().subjectName("Java").build();
			// var sub5 = Subject.builder().subjectName("JavaScript").build();
			// var sub6 = Subject.builder().subjectName(".Net").build();

			// var teacher1 = new Teacher();
			// teacher1.setTeacherName("Rashmi sir");

			// var subjectsForTeacher1 = teacher1.getSubjects();
			// subjectsForTeacher1.add(sub1);
			// subjectsForTeacher1.add(sub2);
			// teacher1.setSubjects((subjectsForTeacher1));

			// sub1.setTeacher(teacher1);
			// sub2.setTeacher(teacher1);
			// teacherRepository.save(teacher1);

			var existingTeacher = teacherRepository.findById(1).orElseThrow();
			existingTeacher.setTeacherName("Rashmi sir 2");
			List<Subject> existingsubjects = existingTeacher.getSubjects();
			var firstSubject = existingsubjects.remove(0);
			firstSubject.setSubjectName("C2");
			existingsubjects.add(0, firstSubject);
			teacherRepository.save(existingTeacher);

		};
	}
}