package com.dv.spring_ai_ollama.services;

import com.dv.spring_ai_ollama.model.Answer;
import com.dv.spring_ai_ollama.model.GetCapitalRequest;
import com.dv.spring_ai_ollama.model.Question;

public interface OllamaService {

    String getAnswer(String question);

    Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest);

    Answer getCapital(GetCapitalRequest getCapitalRequest);

    Answer getAnswer(Question question);
}
