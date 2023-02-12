package com.hydroyura.dictinaryapp.stages.main.listners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hydroyura.dictinaryapp.AppStarter;
import com.hydroyura.dictinaryapp.stages.main.MainStage;
import com.hydroyura.dictinaryapp.stages.main.fsm.body.BodyFSMStates;
import com.hydroyura.dictinaryapp.stages.main.fsm.footer.FooterMainFSMStates;
import com.hydroyura.dictinaryapp.stages.main.fsm.header.HeaderFindWordFSMStates;

import static com.hydroyura.dictinaryapp.stages.main.MainStageConstants.*;

public class AutoCompleteWordListener extends ClickListener {

    private TextButton.TextButtonStyle style;
    private MainStage stage;

    public AutoCompleteWordListener() {
        AppStarter app = (AppStarter) Gdx.app.getApplicationListener();
        this.style = app.getResource("skins/main-skin.json", Skin.class).get("autocomplete-result-label", TextButton.TextButtonStyle.class);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        TextButton button = (TextButton) event.getListenerActor();
        Gdx.app.log(this.getClass().toString(), "clicked(), word = " + button.getText());

        // FIXME: class cast exception
        Table table = (Table) button.getParent();


        if(table.getParent().findActor(BODY_SELECTED_WORD_ID) != null) table.getParent().removeActor(table.getParent().findActor(BODY_SELECTED_WORD_ID));

        // FIXME: To add null-check
        TextButton buttonSelectedWord = new TextButton(button.getText().toString(), style);
        buttonSelectedWord.setName(BODY_SELECTED_WORD_ID);
        buttonSelectedWord.setVisible(false);
        table.getParent().addActor(buttonSelectedWord);

        // FIXME: To move to constructor
        if(stage == null) setStage(event);

        stage.getFsm(HEADER_FIND_WORD_ID).changeState(HeaderFindWordFSMStates.HIDE);
        stage.getFsm(BODY_ID).changeState(BodyFSMStates.SELECT_TRANSLATE);
        stage.getFsm(FOOTER_MAIN_ID).changeState(FooterMainFSMStates.HIDE);

    }

    private void setStage(InputEvent event) {
        this.stage = (MainStage) event.getStage();
    }

}
