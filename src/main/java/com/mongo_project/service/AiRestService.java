package com.mongo_project.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiRestService {
    private ChatClient chatClient;

    public AiRestService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String generatePoem(String topic){
        String prompt = """
                Write a short 6 line creative poem about %s.
                Keep it emotional and simple.
                """.formatted(topic);
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
