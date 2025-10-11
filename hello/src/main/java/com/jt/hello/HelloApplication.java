package com.jt.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("beans.xml")
public class HelloApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(HelloApplication.class, args);
		Student s = context.getBean(Student.class);
		s.sayHello();
		// Student s = new Student();
		// s.sayHello();
		// Teacher t = context.getBean(Teacher.class);
		// t.sayHello();
		
		// Person p1 = context.getBean(Person.class);
		// p1.sayHello();
		Student s1 = context.getBean(Student.class);
		Student s2 = context.getBean(Student.class);
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());

		Person p1 = context.getBean(Person.class);
		Person p2 = context.getBean(Person.class);
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());



	}

}
