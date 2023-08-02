package com.hydroyura.dictinaryapp.core.model;

public class Word {

    private String original;
    private String type;
    private String collectionId;
    private String[] translate;
    private int trainStatus;


    public Word() {}


    public String getOriginal() {
        return original;
    }

    public Word setOriginal(String original) {
        this.original = original;
        return this;
    }

    public String getType() {
        return type;
    }

    public Word setType(String type) {
        this.type = type;
        return this;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public Word setCollectionId(String collectionId) {
        this.collectionId = collectionId;
        return this;
    }

    public String[] getTranslate() {
        return translate;
    }

    public Word setTranslate(String[] translate) {
        this.translate = translate;
        return this;
    }

    public int getTrainStatus() {
        return trainStatus;
    }

    public Word setTrainStatus(int trainStatus) {
        this.trainStatus = trainStatus;
        return this;
    }
}
