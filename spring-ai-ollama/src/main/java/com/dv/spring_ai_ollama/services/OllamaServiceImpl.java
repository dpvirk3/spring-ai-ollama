package com.dv.spring_ai_ollama.services;

import com.dv.spring_ai_ollama.model.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

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


    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;


    @Value("classpath:templates/get-capital-with-info-prompt.st")
    private Resource getCapitalWithInfoPrompt;

    @Override
    public GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest) {
        // PromptTemplate promptTemplate = new PromptTemplate("What is the capital of " +
        //        getCapitalRequest.stateOrCountry() + "?");

        BeanOutputConverter<GetCapitalResponse> converter = new BeanOutputConverter<>(GetCapitalResponse.class);
        String format = converter.getFormat();

        System.out.println("converter format:" + format);

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry",getCapitalRequest.stateOrCountry(),
                "format", format));

        ChatResponse chatResponse = chatModel.call(prompt);

        System.out.println("chatResponse: " + chatResponse.getResult().getOutput().getText());

        return   converter.convert(Objects.requireNonNull(chatResponse.getResult().getOutput().getText()));
    }
    @Override
    public Answer getCapitalInitial(GetCapitalRequest getCapitalRequest) {
        // PromptTemplate promptTemplate = new PromptTemplate("What is the capital of " +
        //        getCapitalRequest.stateOrCountry() + "?");

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry",getCapitalRequest.stateOrCountry()));

        ChatResponse chatResponse = chatModel.call(prompt);

        String responseString=chatResponse.getResult().getOutput().getText();
        responseString = responseString.replaceAll("```json","");
        try {
            JsonNode jsonNode = objectMapper.readTree(responseString);
            responseString = jsonNode.get("answer").asText();
        } catch (JsonProcessingException e) {
            System.out.println("JSON mapping error");
            System.out.println(responseString);
            throw new RuntimeException(e);
        }

        //return new Answer(chatResponse.getResult().getOutput().getText());
        return   new Answer(responseString);
    }

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest) {
        System.out.println("Calling capital with info");
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalWithInfoPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry",getCapitalRequest.stateOrCountry()));

        ChatResponse chatResponse = chatModel.call(prompt);

        return new Answer(chatResponse.getResult().getOutput().getText());
    }

    @Override
    public GetCapitalWithInfoResponse getCapitalWithJson (GetCapitalRequest getCapitalRequest) {

        BeanOutputConverter<GetCapitalWithInfoResponse> converter = new BeanOutputConverter<>(GetCapitalWithInfoResponse.class);
        String format = converter.getFormat();

        System.out.println("converter format:" + format);

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry",getCapitalRequest.stateOrCountry(),
                "format", format));

        ChatResponse chatResponse = chatModel.call(prompt);

        System.out.println("chatResponse: " + chatResponse.getResult().getOutput().getText());

        return   converter.convert(Objects.requireNonNull(chatResponse.getResult().getOutput().getText()));
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
