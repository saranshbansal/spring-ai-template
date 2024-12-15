package com.sbansal.ai.template.web;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {
	private final ChatClient chatClient;

	public ChatController(ChatClient.Builder chatClientBuilder) {
		this.chatClient = chatClientBuilder.build();
	}

	@GetMapping("/ask")
	public String ask(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
		return this.chatClient.prompt()
				.user(message)
				.call()
				.content();
	}
}
