package com.hydroyura.dictinaryapp.http;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponseListener;

public class HttpConnection implements IHttpConnection {

    @Override
    public void sendRequest(HttpRequest request, HttpResponseListener responseListener) {
        Gdx.net.sendHttpRequest(request, responseListener);
    }

}
