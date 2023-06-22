package com.hydroyura.dictinaryapp.core.http.autocomplete;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.dictinaryapp.core.context.annotations.Bean;
import com.hydroyura.dictinaryapp.core.context.annotations.Inject;
import com.hydroyura.dictinaryapp.core.http.HttpConnection;
import com.hydroyura.dictinaryapp.core.http.autocomplete.response.AutoCompleteResponseListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Bean(name = "AutocompleteAPI")
public class AutocompleteAPI implements IAutocompleteAPI {

    private static final String URL = "https://typewise-ai.p.rapidapi.com/completion/complete";

    private Map<String, List<String>> buffer = new HashMap<>();

    @Inject(name = "ObjectMapper")
    private ObjectMapper objectMapper;

    @Inject(name = "HttpConnection")
    private HttpConnection httpConnection;

    @Inject(name = "HttpRequestBuilder")
    private HttpRequestBuilder requestBuilder;

    @Inject(name = "AutoCompleteResponseListener")
    private AutoCompleteResponseListener responseListener;

    private static final Map<String, String> headers = Map.of(
            "X-RapidAPI-Key", "a29d3e29demshc51d3e75c10c484p1eebcejsn8aaa8c3f733f",
            "X-RapidAPI-Host", "typewise-ai.p.rapidapi.com",
            "content-type", "application/json"
    );

    @Override
    public void post(String word) {
        if(isBufferContainValue(word)) return;

        Map<String, Object> body = new HashMap<>();
        body.put("text", word);
        body.put("correctTypoInPartialWord", false);
        body.put("language", "en");

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

    public boolean isBufferContainValue(String key) {
        return buffer.containsKey(key);
    }

    public List<String> getValueFromBuffer(String key) {
        return buffer.get(key);
    }

    public void putValueToBuffer(String key, List<String> value) {
        Gdx.app.getApplicationLogger().log(this.getClass().toString(), "try to put value[] with key[] into autocomplete buffer");
        Gdx.app.getApplicationLogger().log(this.getClass().toString(), "current buffer size[]");
        buffer.put(key, value);
    }
}