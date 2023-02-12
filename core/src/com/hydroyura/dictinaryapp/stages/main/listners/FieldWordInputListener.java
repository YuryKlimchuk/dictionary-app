package com.hydroyura.dictinaryapp.stages.main.listners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.dictinaryapp.AppStarter;
import com.hydroyura.dictinaryapp.httpclient.HttpClient;
import com.hydroyura.dictinaryapp.httpclient.response.impl.AutoCompleteHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class FieldWordInputListener implements TextFieldListener {

    private HttpClient httpClient;

    private ObjectMapper mapper;

    private Net.HttpResponseListener listener;

    public FieldWordInputListener() {
        httpClient = ((AppStarter) Gdx.app.getApplicationListener()).getHttpClient();
        mapper = new ObjectMapper();
        listener = new AutoCompleteHttpResponse();
    }

    @Override
    public void keyTyped(TextField textField, char c) {
        Gdx.app.log(getClass().toString(), "input, textField = " + textField + "; char = " + c + "; current value = " + textField.getText());
        sendRequest(textField.getText());
    }

    private void sendRequest(String text) {
        String url2 = HttpClient.URL_AUTOCOMPLETE;
        Map<String, String> headers2 = Map.of(
                "X-RapidAPI-Key", "a29d3e29demshc51d3e75c10c484p1eebcejsn8aaa8c3f733f",
                "X-RapidAPI-Host", "typewise-ai.p.rapidapi.com",
                "content-type", "application/json"
        );

        Map<String, Object> body2 = new HashMap<>();
        body2.put("text", text);
        body2.put("correctTypoInPartialWord", false);
        body2.put("language", "en");

        try {
            mapper.writeValueAsString(body2);
            httpClient.post(url2, headers2, mapper.writeValueAsString(body2), listener);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
