package com.hydroyura.dictinaryapp.httpclient.response.dto;

import java.util.Collection;

public class DTOAutoCompleteResponse {

    private String currentText;
    private Collection<String> autoCompleteTexts;


    public DTOAutoCompleteResponse() {}


    public String getCurrentText() {
        return currentText;
    }

    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }

    public Collection<String> getAutoCompleteTexts() {
        return autoCompleteTexts;
    }

    public void setAutoCompleteTexts(Collection<String> autoCompleteTexts) {
        this.autoCompleteTexts = autoCompleteTexts;
    }

    @Override
    public String toString() {
        return "DTOAutoCompleteResponse{" +
                "currentText='" + currentText + '\'' +
                ", autoCompleteTexts=" + autoCompleteTexts +
                '}';
    }
}
