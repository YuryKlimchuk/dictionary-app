package com.hydroyura.dictinaryapp.httpclient.response.converter.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.dictinaryapp.httpclient.response.converter.ResponseConverter;
import com.hydroyura.dictinaryapp.httpclient.response.dto.DTOTranslateResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TranslateConverter implements ResponseConverter<DTOTranslateResponse> {

    // FIXME: create bean for ObjectMapper (check if it thread safety ???)
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public DTOTranslateResponse convert(HttpResponse response) {

        DTOTranslateResponse dto = new DTOTranslateResponse();
        String responseString = response.getResultAsString();
        Map<String, Object> responseMap = null;

        try {
            List<Object> tmpList = mapper.readValue(responseString, new TypeReference<List<Map<String, Object>>>(){});
            responseMap = (Map<String, Object>) tmpList.get(0);
        } catch (IOException e) {
            //throw new RuntimeException(e);
            // FIXME: Have to add actual info in logs
            Gdx.app.log(getClass().toString(), "error log");
            responseMap = Collections.emptyMap();
        }

        dto.setTexts((String) responseMap.getOrDefault("texts", ""));
        dto.setTl((String) responseMap.getOrDefault("tl", ""));
        //dto.setStatusCode((long) responseMap.getOrDefault("code", 0));

        return dto;
    }
}


/*
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
 */