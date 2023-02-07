package com.hydroyura.dictinaryapp.httpclient.response.dto;

public class DTOTranslateResponse {

    private long statusCode;
    private String texts;

    private String tl;


    public DTOTranslateResponse() {}


    public long getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(long statusCode) {
        this.statusCode = statusCode;
    }

    public String getTexts() {
        return texts;
    }

    public void setTexts(String texts) {
        this.texts = texts;
    }

    public String getTl() {
        return tl;
    }

    public void setTl(String tl) {
        this.tl = tl;
    }

    @Override
    public String toString() {
        return "DTOTranslateResponse{" +
                "statusCode=" + statusCode +
                ", texts='" + texts + '\'' +
                ", tl='" + tl + '\'' +
                '}';
    }
}
