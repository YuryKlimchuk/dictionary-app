package com.hydroyura.dictinaryapp.data;
/*

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.dictinaryapp.AppStarter;
import com.hydroyura.dictinaryapp.data.entity.Word;
import com.hydroyura.dictinaryapp.data.repository.IRepository;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class FileDesktopRepository implements IRepository {

    public enum FILE_NAMES {
        COLLECTION, WORDS, LANG, TYPES;
    }

    private Map<String, String> files;
    private String path;

    public FileDesktopRepository(String path, Map<String, String> files) {
        Arrays.stream(FILE_NAMES.values()).map(Objects::toString).forEach(value -> {
            if (!files.containsKey(value)) throw new RuntimeException("Error!!!");
        });

        this.files = files;
        this.path = path;
    }

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

        String calcPath = path + files.get(FILE_NAMES.WORDS.toString());

        //boolean isWordsExist = Gdx.files.local(calcPath).exists();

        FileHandle wordsFile = Gdx.files.local(calcPath);

        FileInputStream inputStream = new FileInputStream(wordsFile.file());

        */
/*
        InputStreamReader br = new InputStreamReader(inputStream);

        BufferedReader bufferedReader = new BufferedReader(br);

        char[bufferedReader.read()] chars = bufferedReader.

        boolean flag = wordsFile.file().createNewFile();
        *//*


        String st = new String(inputStream.readAllBytes(), Charset.defaultCharset());


        ObjectMapper om = AppStarter.getInstance().getBean(ObjectMapper.class);

        Word word = om.readValue(wordsFile.file(), Word.class);

    }
}
*/
