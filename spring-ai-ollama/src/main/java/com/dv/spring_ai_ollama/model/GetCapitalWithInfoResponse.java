package com.dv.spring_ai_ollama.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCapitalWithInfoResponse( @JsonPropertyDescription("This is the state or capital") String stateOrCountry,
                                          @JsonPropertyDescription("This is the capital city") String capital,
                                          @JsonPropertyDescription("This is its population") String population,
                                          @JsonPropertyDescription("This is region where capital exists") String region,
                                          @JsonPropertyDescription("This is language used ") String language,
                                          @JsonPropertyDescription("This is the currency used in stateOrCOuntry") String currency) {
}
