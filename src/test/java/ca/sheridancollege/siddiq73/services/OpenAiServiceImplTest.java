package ca.sheridancollege.siddiq73.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OpenAiServiceImplTest {

	@Autowired
	private OpenAiService openAiService;
	
	@Test
	public void getAnswer() {
	String answer = openAiService.getAnswer("Tell me a dad joke. ");
	System.out.println("Got an answer through JUnit!");
	System.out.println(answer);
	}
}
