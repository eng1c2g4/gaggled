package com.github.eng1c2g4.eng1c2g4;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.eng1c2g4.eng1c2g4.screens.*;

public class gaggled extends Game {
    public SpriteBatch sprites;
    public FitViewport viewport;
    public BitmapFont font;
    @Override
    public void create() {
        //Initialise the starting viewport and sprites to draw for menu
        sprites = new SpriteBatch();
        viewport = new FitViewport(8,5);
        font = new BitmapFont();
        font.setUseIntegerPositions(false);
        font.getData().setScale(viewport.getWorldHeight() / Gdx.graphics.getHeight());
        this.setScreen(new MenuScreen(this));
    }

    public void changeScreen(int screen){
        switch(screen) {
            case(1):
                this.setScreen(new MenuScreen(this));
                break;
            case(2):
                this.setScreen(new GameScreen(this));
                break;
            case(3):
                this.setScreen(new PauseScreen(this));
                break;
            case(4):
                this.setScreen(new LoadingScreen(this));
                break;
        }
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        sprites.dispose();
        font.dispose();
    }
}
