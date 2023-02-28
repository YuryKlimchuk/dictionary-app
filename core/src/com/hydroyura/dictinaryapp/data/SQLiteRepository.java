package com.hydroyura.dictinaryapp.data;

import com.hydroyura.dictinaryapp.data.entity.Word;
import com.hydroyura.dictinaryapp.data.repository.IRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class SQLiteRepository implements IRepository {

    private String string;

    @Override
    public Collection<Word> getWordByFilter(Map<String, Object> filter) {
        return null;
    }

    @Override
    public Optional<Word> addWord(Word word) {
        return Optional.empty();
    }

    @Override
    public Optional<Word> deleteWord(Word word) {
        return Optional.empty();
    }

    @Override
    public Optional<Word> updateWord(Word word) {
        return Optional.empty();
    }

    @Override
    public void init() throws IOException {

    }
}
