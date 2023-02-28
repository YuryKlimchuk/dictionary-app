package com.hydroyura.dictinaryapp.data.repository;

import com.hydroyura.dictinaryapp.data.entity.Word;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface IRepository {

    Collection<Word> getWordByFilter(Map<String, Object> filter);
    Optional<Word> addWord(Word word);
    Optional<Word> deleteWord(Word word);
    Optional<Word> updateWord(Word word);
    void init() throws IOException;



}
