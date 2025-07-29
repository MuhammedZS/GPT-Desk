package ca.sheridancollege.siddiq73.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.siddiq73.models.Answer;
import ca.sheridancollege.siddiq73.models.Question;
import ca.sheridancollege.siddiq73.services.OpenAiService;

@RestController
public class GPTController {

	@Autowired
	private OpenAiService openAiService;

	@PostMapping("/GPTDesk")
	public Answer askQuestion(@RequestBody Question question) {
		return openAiService.getAnswer(question);
	}
}
