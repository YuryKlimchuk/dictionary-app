package com.hydroyura.dictinaryapp.core.http;

import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.Net.HttpRequest;

public interface IHttpConnection {

    public void sendRequest(HttpRequest request, HttpResponseListener responseListener);

}
