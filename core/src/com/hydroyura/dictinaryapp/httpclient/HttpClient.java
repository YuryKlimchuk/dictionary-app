package com.hydroyura.dictinaryapp.httpclient;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;

import java.util.Map;

public class HttpClient {



    public static final String URL_TRANSLATE = "https://ai-translate.p.rapidapi.com/translates";
    public static final String URL_AUTOCOMPLETE = "";

    private ResponseListener listener = new ResponseListener();

    private HttpRequestBuilder requestBuilder = new HttpRequestBuilder();

    private void sendRequest(String method, String url, Map<String, String> headers, String body) {
        requestBuilder
                .newRequest()
                .method(method.toString())
                .url(url);

        if(body != null || !body.isEmpty()) requestBuilder.content(body);

        headers.forEach(requestBuilder::header);

        Gdx.net.sendHttpRequest(requestBuilder.build(), listener);
    }

    public void get(String url, Map<String, String> headers) {
        sendRequest(HttpMethods.GET, url, headers, null);
    }

    public void post(String url, Map<String, String> headers, String body) {
        sendRequest(HttpMethods.POST, url, headers, body);
    }

}
