package com.github.eng1c2g4.eng1c2g4.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.eng1c2g4.eng1c2g4.gaggled;

public class MenuScreen implements Screen {
    private final gaggled game;

    public MenuScreen(gaggled game) {
        //Constructor to pass in the game to the screen
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Render the UI + apply the current viewport
        ScreenUtils.clear(Color.BLACK);

        game.viewport.apply();
        game.sprites.setProjectionMatrix(game.viewport.getCamera().combined);

        game.sprites.begin();
        game.font.draw(game.sprites, "Gaggled", game.viewport.getWorldWidth()/2f, game.viewport.getWorldHeight()/1.5f);
        game.font.draw(game.sprites, "Tap anywhere to begin!", game.viewport.getWorldWidth()/2f, game.viewport.getWorldHeight()/2f);
        game.sprites.end();

        //Check if user has pressed the screen to go to the game screen
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
