package com.mongo_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongo_project.service.AiRestService;

@RestController
@RequestMapping("/ai")
public class AiRestController {
    private final AiRestService aiRestService;

    public AiRestController(AiRestService aiRestService) {
        this.aiRestService = aiRestService;
    }

    @GetMapping("/poem")
    public String generatePoem(@RequestParam String topic) {
        return aiRestService.generatePoem(topic);   
    }
}
