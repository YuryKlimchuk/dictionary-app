package com.hydroyura.dictinaryapp.httpclient;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpResponseListener;


import java.util.Map;

public class HttpClient {



    public static final String URL_TRANSLATE = "https://ai-translate.p.rapidapi.com/translates";
    public static final String URL_AUTOCOMPLETE = "https://typewise-ai.p.rapidapi.com/completion/complete";

    private HttpRequestBuilder requestBuilder = new HttpRequestBuilder();

    private void sendRequest(String method, String url, Map<String, String> headers, String body, HttpResponseListener listener) {
        requestBuilder
                .newRequest()
                .method(method.toString())
                .url(url);

        if(body != null || !body.isEmpty()) requestBuilder.content(body);

        headers.forEach(requestBuilder::header);

        Gdx.net.sendHttpRequest(requestBuilder.build(), listener);
    }

    public void get(String url, Map<String, String> headers, HttpResponseListener listener) {
        sendRequest(HttpMethods.GET, url, headers, null, listener);
    }

    public void post(String url, Map<String, String> headers, String body, HttpResponseListener listener) {
        sendRequest(HttpMethods.POST, url, headers, body, listener);
    }

}
