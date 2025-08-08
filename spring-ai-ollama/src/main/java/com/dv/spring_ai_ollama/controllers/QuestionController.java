package com.dv.spring_ai_ollama.controllers;

import com.dv.spring_ai_ollama.model.*;
import com.dv.spring_ai_ollama.services.OllamaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {
    private final OllamaService ollamaService;

    public QuestionController(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @PostMapping("/capitalWithInfoInitial")
    public Answer getCapitalWithInfoInitial(@RequestBody GetCapitalRequest getCapitalRequest) {
        return ollamaService.getCapitalWithInfo(getCapitalRequest);
    }

    @PostMapping("/capitalWithInfo")
    public GetCapitalWithInfoResponse getCapitalWithInfo(@RequestBody GetCapitalRequest getCapitalRequest) {
        return ollamaService.getCapitalWithJson(getCapitalRequest);
    }

    @PostMapping("/capitalInitial")
    public Answer getCapitalInitial(@RequestBody GetCapitalRequest getCapitalRequest) {
        return ollamaService.getCapitalInitial(getCapitalRequest);
    }


    @PostMapping("/capital")
    public GetCapitalResponse getCapital(@RequestBody GetCapitalRequest getCapitalRequest) {
        return ollamaService.getCapital(getCapitalRequest);
    }

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return ollamaService.getAnswer(question);
    }
}
