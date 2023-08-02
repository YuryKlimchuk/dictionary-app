package com.hydroyura.dictinaryapp.core.repository;

import com.badlogic.gdx.utils.Disposable;
import com.hydroyura.dictinaryapp.core.model.Word;

public interface IRepository extends Disposable {

    public void initConnection();

    public boolean addWord(Word word);

    /*
    Addword
ChangewordProcess
GetTranslation
Getwordbytranslation
Getwordsfromcollections

     */


}
