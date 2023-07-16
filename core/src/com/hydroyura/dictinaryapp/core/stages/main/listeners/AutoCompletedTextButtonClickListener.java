package com.hydroyura.dictinaryapp.core.stages.main.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hydroyura.dictinaryapp.core.ApplicationStarter;
import com.hydroyura.dictinaryapp.core.stages.main.MainStage;
import com.hydroyura.dictinaryapp.core.stages.main.fsms.body.BodyFSMStates;
import com.hydroyura.dictinaryapp.core.stages.main.fsms.footer.FooterAddWordFSMStates;
import com.hydroyura.dictinaryapp.core.stages.main.fsms.footer.FooterMainFSMStates;
import com.hydroyura.dictinaryapp.core.stages.main.fsms.header.HeaderFindWordFSMStates;

import static com.hydroyura.dictinaryapp.core.stages.StageConstants.*;

public class AutoCompletedTextButtonClickListener extends ClickListener {

    private TextButton.TextButtonStyle style;


    public AutoCompletedTextButtonClickListener() {
        AssetManager assetManager = ((ApplicationStarter) Gdx.app.getApplicationListener()).getContext().getBean("AssertManager", AssetManager.class);
        this.style = assetManager.get("skins/main-skin.json", Skin.class).get("autocomplete-result-label", TextButton.TextButtonStyle.class);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        TextButton button = (TextButton) event.getListenerActor();
        Table table = (Table) button.getParent();
        Gdx.app.log(this.getClass().toString(), "clicked button with value[" + button.getText() + "]");

        if(table.getParent().findActor(BODY_SELECTED_WORD_ID) != null) table.getParent().removeActor(table.getParent().findActor(BODY_SELECTED_WORD_ID));

        // FIXME: To add null-check
        TextButton buttonSelectedWord = new TextButton(button.getText().toString(), style);
        buttonSelectedWord.setName(BODY_SELECTED_WORD_ID);
        buttonSelectedWord.setVisible(false);
        table.getParent().addActor(buttonSelectedWord);

        // FIXME: To move to constructor
        MainStage stage = (MainStage) event.getStage();

        stage.getFsm(HEADER_FIND_WORD_ID).changeState(HeaderFindWordFSMStates.HIDE);
        stage.getFsm(BODY_ID).changeState(BodyFSMStates.SELECT_TRANSLATE);
        stage.getFsm(FOOTER_MAIN_ID).changeState(FooterMainFSMStates.HIDE);
        stage.getFsm(FOOTER_ADD_WORD_ID).changeState(FooterAddWordFSMStates.DISPLAY);
    }

}
