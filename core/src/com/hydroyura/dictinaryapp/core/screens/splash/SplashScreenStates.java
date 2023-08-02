package com.hydroyura.dictinaryapp.core.screens.splash;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.assets.AssetManager;
import com.hydroyura.dictinaryapp.core.model.Word;
import com.hydroyura.dictinaryapp.core.repository.IRepository;
import com.hydroyura.dictinaryapp.core.screens.main.MainScreen;

import java.util.Arrays;

public enum SplashScreenStates implements State<Screen> {

    ANIMATE() {
        @Override
        public void update(Screen entity) {
            if (entity instanceof SplashScreen) {
                SplashScreen screen = (SplashScreen) entity;
                if (screen.getContext().isReady()) {
                    if (screen.getContext().getBean("AssertManager", AssetManager.class).update()) {
                        screen.getFsm().changeState(SplashScreenStates.LOAD_FINISHED);
                    }
                }
            }
        }
    },

    LOAD_FINISHED() {
        @Override
        public void enter(Screen entity) {
            if (entity instanceof SplashScreen) {
                SplashScreen screen = (SplashScreen) entity;
                screen.getApplication().setScreen(screen.getContext().getBean("MainScreen", MainScreen.class));
            }
        }
    };


    @Override
    public void enter(Screen entity) {

    }

    @Override
    public void update(Screen entity) {

    }

    @Override
    public void exit(Screen entity) {

    }

    @Override
    public boolean onMessage(Screen entity, Telegram telegram) {
        return false;
    }
}
