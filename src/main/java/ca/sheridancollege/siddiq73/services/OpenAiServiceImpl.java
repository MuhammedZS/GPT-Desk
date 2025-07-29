package ca.sheridancollege.siddiq73.services;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;

import org.springframework.stereotype.Service;

import ca.sheridancollege.siddiq73.models.Answer;
import ca.sheridancollege.siddiq73.models.Question;

@Service
public class OpenAiServiceImpl implements OpenAiService {
	
	private final ChatModel chatModel;
	
	public OpenAiServiceImpl(ChatModel chatModel) {
		this.chatModel = chatModel;
	}

	@Override
	public String getAnswer(String question) {
	PromptTemplate promptTemplate = new PromptTemplate(question);
	Prompt prompt = promptTemplate.create();
	
	ChatResponse response = chatModel.call(prompt);
	return response.getResult().getOutput().getText();
	}
	
	public Answer getAnswer(Question question) {
		PromptTemplate promptTemplate = new PromptTemplate(question.question());
		Prompt prompt = promptTemplate.create();
		ChatResponse response = chatModel.call(prompt);
		return new Answer(response.getResult().getOutput().getText());
	}
}
