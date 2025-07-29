package ca.sheridancollege.siddiq73.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.siddiq73.models.Answer;
import ca.sheridancollege.siddiq73.models.ChatEntry;
import ca.sheridancollege.siddiq73.models.Question;
import ca.sheridancollege.siddiq73.services.OpenAiService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ViewController {
	@Autowired
	private OpenAiService openAiService;
	
	private static final int MAX_PROMPTS = 10;
	
	@GetMapping("/")
	public String index() {
		return "landing";
	}
	
	@GetMapping("/GPTDesk")
	public String showForm(HttpSession session, Model model) {
	    List<ChatEntry> history = (List<ChatEntry>) session.getAttribute("chatHistory");
	    if (history == null) {
	        history = new ArrayList<>();
	    }

	    model.addAttribute("chatHistory", history);

	    Integer promptCount = (Integer) session.getAttribute("promptCount");
	    if (promptCount == null) {
	        promptCount = 0;
	    }

	    boolean limitReached = promptCount >= MAX_PROMPTS;
	    model.addAttribute("limitReached", limitReached);

	    return "index";
	}
	
	// To create a new session after the maximum limit for prompts is reached
	@GetMapping("/reset")
	public String resetSession(HttpSession session) {
	    session.invalidate(); 
	    return "redirect:/GPTDesk";  
	}

	
	@PostMapping("/ask")
	public String askQuestion(Question question, Model model, HttpSession session) {
	    Integer promptCount = (Integer) session.getAttribute("promptCount");
	    if (promptCount == null) {
	        promptCount = 0;
	    }

	    if (promptCount >= MAX_PROMPTS) {
	        model.addAttribute("limitReached", true);
	    } else {
	        Answer answer = openAiService.getAnswer(question);

	        List<ChatEntry> history = (List<ChatEntry>) session.getAttribute("chatHistory");
	        if (history == null) {
	            history = new ArrayList<>();
	        }

	        history.add(new ChatEntry(question.question(), answer.answer()));
	        session.setAttribute("chatHistory", history);

	        // Update prompt count
	        session.setAttribute("promptCount", promptCount + 1);
	        model.addAttribute("limitReached", promptCount + 1 >= MAX_PROMPTS);
	    }

	    // Always show current history
	    model.addAttribute("chatHistory", session.getAttribute("chatHistory"));

	    return "index";
	}

}
