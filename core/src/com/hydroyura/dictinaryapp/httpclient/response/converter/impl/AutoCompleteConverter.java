package com.hydroyura.dictinaryapp.httpclient.response.converter.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.dictinaryapp.httpclient.response.converter.ResponseConverter;
import com.hydroyura.dictinaryapp.httpclient.response.dto.DTOAutoCompleteResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class AutoCompleteConverter implements ResponseConverter<DTOAutoCompleteResponse> {

    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public DTOAutoCompleteResponse convert(HttpResponse response) {
        DTOAutoCompleteResponse dto = new DTOAutoCompleteResponse();
        String responseString = response.getResultAsString();
        Map<String, Object> responseMap = null;

        try {
            responseMap = mapper.readValue(responseString, new TypeReference<Map<String, Object>>(){});
        } catch (IOException e) {
            //throw new RuntimeException(e);
            // FIXME: Have to add actual info in logs
            Gdx.app.log(getClass().toString(), "error log");
            responseMap = Collections.emptyMap();
        }

        Collection<Map<String, String>> predictionsList = (Collection<Map<String, String>>) responseMap.getOrDefault("predictions", Collections.emptyList());
        Collection<String> autoCompleteTexts = predictionsList.stream()
                .map(item -> item.getOrDefault("text", ""))
                .filter(item -> !item.equals(""))
                .collect(Collectors.toSet());



        dto.setCurrentText((String) responseMap.getOrDefault("text", ""));
        dto.setAutoCompleteTexts(autoCompleteTexts);
        return dto;
    }
}
