package com.jpa_annotation_in_spring;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class JpaAnnotationInSpringApplication {
	private final EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaAnnotationInSpringApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		CommandLineRunner runner = new CommandLineRunner() {

			@Override
			public void run(String... args) {
				Employee existingEmployee = employeeRepository.findById("5e03eaea-b07e-45ec-aabd-5aca77bd93fb")
						.orElseThrow();
				existingEmployee.setEmployeeStatus(EmployeeStatus.INACTIVE);
				employeeRepository.save(existingEmployee);
				Employee employee = Employee.builder()
						.employeeName("Avishek Das")
						.employeeDescription("A sample employee")
						.employeeSalary(BigDecimal.valueOf(50000.00))
						.employeeStatus(EmployeeStatus.ACTIVE)
						.build();
				employeeRepository.save(employee);

			}
		};
		return runner;
	}
}
