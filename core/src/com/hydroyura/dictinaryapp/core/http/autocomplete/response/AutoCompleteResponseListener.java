package com.hydroyura.dictinaryapp.core.http.autocomplete.response;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.dictinaryapp.core.context.annotations.Bean;
import com.hydroyura.dictinaryapp.core.context.annotations.Inject;
import com.hydroyura.dictinaryapp.core.http.autocomplete.AutocompleteAPI;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Bean(name = "AutoCompleteResponseListener")
public class AutoCompleteResponseListener implements HttpResponseListener {

    @Inject(name = "AutocompleteAPI")
    private AutocompleteAPI autocompleteAPI;

    @Inject(name = "ObjectMapper")
    private ObjectMapper objectMapper;

    @Override
    public void handleHttpResponse(HttpResponse httpResponse) {
        Gdx.app.getApplicationLogger().log(this.getClass().toString(), "got response from [autocomplete API] with status ["
                + httpResponse.getStatus().getStatusCode() + "]");

        String jsonResponse = httpResponse.getResultAsString();
        try {
            Map<String, Object> map = objectMapper.readValue(jsonResponse, new TypeReference<Map<String, Object>>() {});
            String key = String.valueOf(map.get("text"));

            Set<String> value = ((List<Object>) map.get("predictions"))
                                    .stream()
                                        .map(item -> ((Map<String, String>) item).get("text"))
                                        .sorted(Comparator.naturalOrder())
                                    .collect(Collectors.toSet());

            autocompleteAPI.putValueToBuffer(key, value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void failed(Throwable t) {
        Gdx.app.getApplicationLogger().log(this.getClass().toString(), "failed");
    }

    @Override
    public void cancelled() {
        Gdx.app.getApplicationLogger().log(this.getClass().toString(), "cancelled");
    }
}
