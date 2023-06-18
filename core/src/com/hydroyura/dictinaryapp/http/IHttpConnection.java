package com.hydroyura.dictinaryapp.http;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.Net.HttpRequest;

import java.util.Map;

public interface IHttpConnection {

    public void sendRequest(HttpRequest request, HttpResponseListener responseListener);

}
