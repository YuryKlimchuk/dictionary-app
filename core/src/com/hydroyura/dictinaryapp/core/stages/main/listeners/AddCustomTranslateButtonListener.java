package com.hydroyura.dictinaryapp.core.stages.main.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hydroyura.dictinaryapp.core.ApplicationStarter;
import com.hydroyura.dictinaryapp.core.stages.main.fsms.body.BodyFSMStates;

import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.hydroyura.dictinaryapp.core.stages.StageConstants.*;

public class AddCustomTranslateButtonListener extends ClickListener {
    private boolean isShow = false;
    private Dialog dialog;

    private Label word;
    private TextField textField;

    public AddCustomTranslateButtonListener() {
        Skin skin = ((ApplicationStarter) Gdx.app.getApplicationListener()).getContext().getBean("AssertManager", AssetManager.class).get("skins/main-skin.json");

        dialog = new Dialog("", skin.get( "add-translate-dialog", Window.WindowStyle.class));
        dialog.setSize(Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight() * 0.5f);
        dialog.setDebug(true);

        dialog.getBackground().setMinWidth(Gdx.graphics.getWidth() * 0.8f);
        dialog.getBackground().setMinHeight(Gdx.graphics.getHeight() * 0.5f);

        TextButton.TextButtonStyle styleAdd = skin.get("add-translate-dialog-btn-add", TextButton.TextButtonStyle.class);
        TextButton.TextButtonStyle styleCancel = skin.get("add-translate-dialog-btn-cancel", TextButton.TextButtonStyle.class);

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
                addCustomTranslate(tf1.getText());

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

        Label.LabelStyle styleWord = skin.get("label-for-add-word", Label.LabelStyle.class);
        word = new Label("", styleWord);
        dialog.getContentTable().add(word).row();

        // add-translate-dialog-text-input
        TextField.TextFieldStyle styleTextField = skin.get("add-translate-dialog-text-input", TextField.TextFieldStyle.class);
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

    public void addCustomTranslate(String translate) {
        Gdx.app.log(this.getClass().getName(), "Add custom translate[" + translate + "]");
    }
}
