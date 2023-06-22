package com.hydroyura.dictinaryapp.core.http;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.hydroyura.dictinaryapp.core.context.annotations.Bean;

@Bean(name = "HttpConnection")
public class HttpConnection implements IHttpConnection {
    @Override
    public void sendRequest(HttpRequest request, HttpResponseListener responseListener) {
        Gdx.app.getApplicationLogger().log(this.toString(),
                "Send [" + request.getMethod() + "] request to [" + request.getUrl() + "]"
                        + " with body [" + request.getContent() + "]");
        Gdx.net.sendHttpRequest(request, responseListener);
        Gdx.app.getApplicationLogger().log(this.toString(), "SUCCESS");
    }
}
