package scm.SmartContactManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import scm.SmartContactManager.services.EmailService;

@SpringBootTest
class SmartContactManagerApplicationTests {

	@Test
	void contextLoads() {

	}

	@Autowired
	private EmailService e;
	@Test
	void sendEmailTest()
	{
          e.sendEmail("atharvavdalvi@gmail.com","Congratulations !!!","You are Selected For Software Intern Role At Amazon !!!");
	}

}
