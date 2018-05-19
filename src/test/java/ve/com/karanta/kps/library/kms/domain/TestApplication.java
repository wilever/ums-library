package ve.com.karanta.kps.library.kms.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ve.com.karanta.kps.library.kms.domain.repository.TestRepository;

/**
 * TestApplication for test specification with {@link TestRepository}.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
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
