package com.hydroyura.dictinaryapp.data.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class WordCollectionStore {

    private Collection<WordCollection> collections = new ArrayList<>();
    private LocalDate lastUpdate;


    public WordCollectionStore() {}


    public Collection<WordCollection> getCollections() {
        return collections;
    }

    public void setCollections(Collection<WordCollection> collections) {
        this.collections = collections;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
