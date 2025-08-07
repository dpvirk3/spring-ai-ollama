package com.dv.spring_ai_ollama.services;

import com.dv.spring_ai_ollama.model.Answer;
import com.dv.spring_ai_ollama.model.Question;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service
public class OllamaServiceImpl implements OllamaService {
    /**
     * @param question
     * @return
     */
    private final OllamaChatModel chatModel;

    public OllamaServiceImpl(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public Answer getAnswer(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();

        ChatResponse chatResponse = chatModel.call(prompt);

        return new Answer(chatResponse.getResult().getOutput().getText());
    }

    @Override
    public String getAnswer(String question) {

        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();

        ChatResponse chatResponse = chatModel.call(prompt);

        return chatResponse.getResult().getOutput().getText();
    }
}
