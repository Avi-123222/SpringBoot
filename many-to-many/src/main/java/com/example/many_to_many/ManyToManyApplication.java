package com.example.many_to_many;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class ManyToManyApplication {
	private final StudentRepository studentRepository;
	private final CourseRepository courseRepository; // Keep this injected

	public static void main(String[] args) {
		SpringApplication.run(ManyToManyApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {

			// If you uncomment the initial data setup, ensure it's idempotent or only runs
			// once.
			/*
			 * Course course1 = Course.builder().courseName("C").build();
			 * Course course2 = Course.builder().courseName("C++").build();
			 * Course course3 = Course.builder().courseName("Java").build();
			 * Course course4 = Course.builder().courseName("Python").build();
			 * 
			 * Student student1 = Student.builder()
			 * .studentName("Biswa")
			 * .courses(List.of(course1, course2))
			 * .build();
			 * 
			 * Student student2 = Student.builder()
			 * .studentName("Amiya")
			 * .courses(List.of(course2, course3))
			 * .build();
			 * 
			 * Student student3 = Student.builder()
			 * .studentName("Ankit")
			 * .courses(List.of(course3, course4))
			 * .build();
			 * 
			 * // NOTE: Since Courses are part of the Student's List and have no ID,
			 * // studentRepository.saveAll will **persist** the new Course entities.
			 * // This assumes you have CascadeType.PERSIST/MERGE on the @ManyToMany
			 * mapping.
			 * studentRepository.saveAll(List.of(student1, student2, student3));
			 */

			// =========================================
			// --- 1. Retrieve the existing Student (Amiya, ID 2) ---
			Student existingStudent = studentRepository.findById(2)
					.orElseThrow(() -> new RuntimeException("Student with ID 2 not found"));

			// --- 2. Update the Student's Name (Always safe via owning repository) ---
			existingStudent.setStudentName("Amiya Patra");

			// --- 3. Update the associated Course Name(s) (Requires explicit save) ---
			// We iterate through the managed Course entities associated with the Student.
			// The entities are attached to the Persistence Context.
			existingStudent.getCourses().forEach(course -> {
				// Find the specific course you want to update (e.g., "Java").
				// We assume here the Course name hasn't changed from the initial seed value
				// ("Java").
				if ("Java".equals(course.getCourseName())) {
					// Update the course name property
					course.setCourseName("Advanced Java Programming");
					// 💡 BUG FIX: Explicitly save the shared Course entity to reliably
					// trigger the UPDATE SQL for the T_COURSE table.
					courseRepository.save(course);
				}
			});

			// --- 4. Save the Owning Entity (Student) ---
			// This saves the student's new name and ensures the join table is consistent
			// (though no changes to the join table mapping are occurring here).
			studentRepository.save(existingStudent);

			System.out.println("✅ Student and Course names updated successfully.");
		};
	}
}