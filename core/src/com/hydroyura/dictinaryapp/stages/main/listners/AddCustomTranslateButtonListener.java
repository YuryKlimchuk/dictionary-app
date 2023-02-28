package com.hydroyura.dictinaryapp.stages.main.listners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hydroyura.dictinaryapp.AppStarter;
import com.hydroyura.dictinaryapp.stages.main.fsm.body.BodyFSMStates;

import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.hydroyura.dictinaryapp.stages.main.MainStageConstants.*;

public class AddCustomTranslateButtonListener extends ClickListener {

    private boolean isShow = false;
    private Dialog dialog;

    private Label word;
    private TextField textField;

    public AddCustomTranslateButtonListener() {
        dialog = new Dialog("", AppStarter.getInstance().getStyle("skins/main-skin.json", "add-translate-dialog", Window.WindowStyle.class));
        dialog.setSize(Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight() * 0.5f);
        dialog.setDebug(true);

        dialog.getBackground().setMinWidth(Gdx.graphics.getWidth() * 0.8f);
        dialog.getBackground().setMinHeight(Gdx.graphics.getHeight() * 0.5f);

        TextButtonStyle styleAdd = AppStarter.getInstance().getStyle("skins/main-skin.json", "add-translate-dialog-btn-add", TextButtonStyle.class);
        TextButtonStyle styleCancel = AppStarter.getInstance().getStyle("skins/main-skin.json", "add-translate-dialog-btn-cancel", TextButtonStyle.class);

        TextButton btnAdd = new TextButton("Добавить", styleAdd);
        btnAdd.setDisabled(true);
        TextButton btnCancel = new TextButton("Отмена", styleCancel);

        // FIXME: create separate class
        btnCancel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.log(this.getClass().toString(), "clicked");
                getDialog(event.getTarget()).orElseThrow(RuntimeException::new).hide();
            }

            Optional<Dialog> getDialog(Actor actor) {
                Dialog d = null;
                Actor currentActor = actor;

                while(true) {
                    Group parent = currentActor.getParent();
                    if(parent == null) break;
                    if(parent instanceof Dialog) {
                        d = (Dialog) parent;
                        break;
                    }
                    currentActor = parent;
                }

                return Optional.ofNullable(d);
            }

        });


        btnAdd.addListener(new ClickListener() {

            Optional<Dialog> getDialog(Actor actor) {
                Dialog d = null;
                Actor currentActor = actor;

                while(true) {
                    Group parent = currentActor.getParent();
                    if(parent == null) break;
                    if(parent instanceof Dialog) {
                        d = (Dialog) parent;
                        break;
                    }
                    currentActor = parent;
                }

                return Optional.ofNullable(d);
            }


            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.log(this.getClass().toString(), "clicked, add");
                Dialog d = getDialog(event.getTarget()).orElseThrow(RuntimeException::new);

                TextField tf1 = d.getContentTable().findActor(ADD_TRANSLATE_DIALOG_TEXT_INPUT);

                // вот этот текст впихнуть в список дальше
                Gdx.app.log(this.getClass().toString(), "Text = " + tf1.getText());
                BodyFSMStates.SELECT_TRANSLATE.addTranslate(tf1.getText());

                getDialog(event.getTarget()).orElseThrow(RuntimeException::new).hide();
            }
        });


        Table table = new Table();
        float btnHeight = Gdx.graphics.getHeight() / 13f;
        float btnWidth = Gdx.graphics.getWidth() / 3f;
        float btnPad = Gdx.graphics.getWidth() / 20f;
        table.add(btnCancel).height(btnHeight).width(btnWidth).pad(btnPad);
        table.add(btnAdd).height(btnHeight).width(btnWidth).pad(btnPad);
        dialog.getButtonTable().add(table);

        LabelStyle styleWord = AppStarter.getInstance().getStyle("skins/main-skin.json", "label-for-add-word", LabelStyle.class);
        word = new Label("", styleWord);
        dialog.getContentTable().add(word).row();

        // add-translate-dialog-text-input
        TextFieldStyle styleTextField = AppStarter.getInstance().getStyle("skins/main-skin.json", "add-translate-dialog-text-input", TextFieldStyle.class);
        textField = new TextField("", styleTextField);
        textField.setName(ADD_TRANSLATE_DIALOG_TEXT_INPUT);
        textField.getStyle().background.setLeftWidth(Gdx.graphics.getWidth() / 10f);
        textField.getStyle().background.setRightWidth(Gdx.graphics.getWidth() / 10f);
        textField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                Gdx.app.log(this.getClass().toString(), "key = " + String.valueOf(c) + "; text = " + textField.getText());
            }
        });
        dialog.getContentTable().add(textField)
                .width(Gdx.graphics.getWidth() * 0.7f)
                .height(Gdx.graphics.getHeight() / 20f)
                .padTop(Gdx.graphics.getHeight() / 20f).row();

    }

    private static String getWord(Stage stage) {

        Group body = (Group) StreamSupport.stream(stage.getActors().spliterator(), false)
                .filter(actor -> actor.getName().equals(BODY_ID))
                .findFirst()
                .orElseThrow();

        Label word = body.findActor(BODY_WORDS_WORD_ADD_TITLE_ID);

        return word.getText().toString();
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        Gdx.app.log(this.getClass().toString(), "AddCustomTranslateButton is pressed");

        Stage stage = event.getStage();
        textField.setText("");
        //textField.clear();
        dialog.show(stage);
        word.setText(getWord(stage));


    }
}
