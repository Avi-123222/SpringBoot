package com.jt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeanLifeCycleAndCommandLineRunnerApplication implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		System.out.println("CommandLineRunner is executed");
		
	}

	public static void main(String[] args) {
		//create the spring container
		// the run method of commandLine runner is executed after the SpringContainer is fully initialized (prepared)
		//we can populate the data inside database✅
		//we can perform some startup or logging
		// test small piece of code✅
		//we can load some cache data
	 SpringApplication.run(BeanLifeCycleAndCommandLineRunnerApplication.class, args).getBean(Greet.class).greet();
	}

}
// bena are managed by spring container(ioc container)
//life cycle of bean
//1.bean instantiation
//2.dependency injection
//3. bean initialization 
//4.bean used
//5.bean destroyed