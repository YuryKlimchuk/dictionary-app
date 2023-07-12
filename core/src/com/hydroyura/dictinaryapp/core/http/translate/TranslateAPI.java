package com.hydroyura.dictinaryapp.core.http.translate;

import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.dictinaryapp.core.context.annotations.Bean;
import com.hydroyura.dictinaryapp.core.context.annotations.Inject;
import com.hydroyura.dictinaryapp.core.http.HttpConnection;
import com.hydroyura.dictinaryapp.core.http.translate.response.TranslateResponseListener;

import java.util.*;

@Bean(name = "TranslateAPI")
public class TranslateAPI implements ITranslateAPI {

    @Inject(name = "ObjectMapper")
    private ObjectMapper objectMapper;

    @Inject(name = "HttpConnection")
    private HttpConnection httpConnection;

    @Inject(name = "HttpRequestBuilder")
    private HttpRequestBuilder requestBuilder;

    @Inject(name = "TranslateResponseListener")
    private TranslateResponseListener responseListener;

    private List<String> result = new ArrayList<>();

    private static final String URL = "https://ai-translate.p.rapidapi.com/translates";

    private Map<String, String> headers = Map.of(
            "X-RapidAPI-Key", "244b5dd242msh2d660d8777daa5cp110e59jsn2bcfbca1afed",
            "X-RapidAPI-Host", "ai-translate.p.rapidapi.com",
            "content-type", "application/json"
    );


    @Override
    public void post(String word) {
        if(false) return;

        clearResult();

        Map<String, Object> body = new HashMap<>();
        body.put("texts", Arrays.asList(word));
        body.put("tls", Arrays.asList("ru"));
        body.put("sl", "en");

        try {
            String json = objectMapper.writeValueAsString(body);
            requestBuilder.newRequest()
                    .content(json)
                    .method(HttpMethods.POST)
                    .url(URL);
            headers.forEach(requestBuilder::header);
            httpConnection.sendRequest(requestBuilder.build(), responseListener);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    public boolean isResultReady() {
        return !result.isEmpty();
    }

    public void putResult(List<String> worlds) {
        result.addAll(worlds);
    }

    public void clearResult() {
        result.clear();
    }

}
