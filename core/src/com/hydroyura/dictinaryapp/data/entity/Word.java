package com.hydroyura.dictinaryapp.data.entity;

import java.util.ArrayList;
import java.util.Collection;

public class Word {

    private long id;
    private String type;
    private String value;
    private Collection<String> translations = new ArrayList<>();
    public String lang;
    public String translationLang;
    public WordCollection collection;


    public Word() {}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Collection<String> getTranslations() {
        return translations;
    }

    public void setTranslations(Collection<String> translations) {
        this.translations = translations;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTranslationLang() {
        return translationLang;
    }

    public void setTranslationLang(String translationLang) {
        this.translationLang = translationLang;
    }

    public WordCollection getCollection() {
        return collection;
    }

    public void setCollection(WordCollection collection) {
        this.collection = collection;
    }
}
