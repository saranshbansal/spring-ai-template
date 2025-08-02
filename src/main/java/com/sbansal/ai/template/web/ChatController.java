package com.sbansal.ai.template.web;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatClient chatClient;

    @Value("${spring.ai.openai.chat.options.model:}")
    private String openAiChatModel;

    @Value("${spring.ai.anthropic.chat.options.model:}")
    private String anthropicChatModel;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/ask-openai")
    public String askOpenAi(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return this.chatClient.prompt()
                .user(message)
                .options(ChatOptions.builder()
                        .model(openAiChatModel)
                        .build())
                .call()
                .content();
    }

    @GetMapping("/ask-anthropic")
    public String askAnthropic(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return this.chatClient.prompt()
                .user(message)
                .options(ChatOptions.builder()
                        .model(anthropicChatModel)
                        .build())
                .call()
                .content();
    }
}
