package ca.sheridancollege.siddiq73.services;

import ca.sheridancollege.siddiq73.models.Answer;
import ca.sheridancollege.siddiq73.models.Question;

public interface OpenAiService {
	public String getAnswer(String question);
	public Answer getAnswer(Question question);
}
