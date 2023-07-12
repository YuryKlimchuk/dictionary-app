package com.hydroyura.dictinaryapp.core.http.translate.response;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.dictinaryapp.core.context.annotations.Bean;
import com.hydroyura.dictinaryapp.core.context.annotations.Inject;
import com.hydroyura.dictinaryapp.core.http.translate.TranslateAPI;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Bean(name = "TranslateResponseListener")
public class TranslateResponseListener implements HttpResponseListener {

    @Inject(name = "TranslateAPI")
    private TranslateAPI translateAPI;

    @Inject(name = "ObjectMapper")
    private ObjectMapper objectMapper;

    @Override
    public void handleHttpResponse(HttpResponse httpResponse) {
        Gdx.app.getApplicationLogger().log(
                this.getClass().toString(),
                "got response from [translate API] with status [" + httpResponse.getStatus().getStatusCode() + "]"
        );

        String jsonResponse = httpResponse.getResultAsString().replace("[", "").replace("]", "");
        try {
            Map<String, String> map = objectMapper.readValue(jsonResponse, new TypeReference<Map<String, String>>() {});
            String translate = map.getOrDefault("texts", "");
            translateAPI.putResult(List.of(translate));

        } catch (IOException e) {
            e.printStackTrace();
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
