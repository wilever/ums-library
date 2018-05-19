package com.uproject.library.ums.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uproject.library.ums.domain.repository.TestRepository;

/**
 * TestApplication for test specification with {@link TestRepository}.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 */
@SpringBootApplication
public class TestApplication {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
