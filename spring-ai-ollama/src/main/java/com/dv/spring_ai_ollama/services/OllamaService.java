package com.dv.spring_ai_ollama.services;

import com.dv.spring_ai_ollama.model.*;

public interface OllamaService {

    String getAnswer(String question);

    Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest);

    Answer getCapitalInitial(GetCapitalRequest getCapitalRequest);

    GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest);

    GetCapitalWithInfoResponse getCapitalWithJson (GetCapitalRequest getCapitalRequest);

    Answer getAnswer(Question question);
}
