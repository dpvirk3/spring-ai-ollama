package com.dv.spring_ai_ollama.services;

import com.dv.spring_ai_ollama.model.Answer;
import com.dv.spring_ai_ollama.model.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OllamaServiceImplTest {

    @Autowired
    OllamaService service;

    @Test
    void getAnswer() {
        Answer answer = service.getAnswer(new Question("Jane is faster than Joe. " +
                "Joe is faster than Sam. Is Sam faster " +
                "than Jane? Explain your reasoning step by step."));

        System.out.println(answer.answer());

        answer = service.getAnswer(new Question( "Create JSON for the following: There are 3 people, " +
                "two males. One is named Mark. Another is named Joe. And a third " +
                "person is a woman named Sam. The woman is age 20 and the two men are both 19."));

        System.out.println(answer.answer());

        answer = service.getAnswer(new Question( "25 - 4 * 2 + 3 = ?"));

        System.out.println(answer.answer());
    }
}