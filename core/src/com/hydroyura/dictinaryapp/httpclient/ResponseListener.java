package com.hydroyura.dictinaryapp.httpclient;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.Net.HttpResponse;

public class ResponseListener implements HttpResponseListener {


    @Override
    public void handleHttpResponse(HttpResponse httpResponse) {
        Gdx.app.log(getClass().toString(), "handleHttpResponse");
    }

    @Override
    public void failed(Throwable t) {
        Gdx.app.log(getClass().toString(), "failed");
    }

    @Override
    public void cancelled() {
        Gdx.app.log(getClass().toString(), "cancelled");
    }
}
