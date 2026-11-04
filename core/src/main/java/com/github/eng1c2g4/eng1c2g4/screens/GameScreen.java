package com.github.eng1c2g4.eng1c2g4.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.eng1c2g4.eng1c2g4.Main;
import com.github.eng1c2g4.eng1c2g4.entity.*;


public class GameScreen implements Screen {
    private final Main main;

    private World world;
    private OrthographicCamera camera;
    private SpriteBatch batch;



    public GameScreen(Main main) {
        this.main = main;

        this.camera = new OrthographicCamera(1280,720);
        this.batch = new SpriteBatch();

        world = new World();



    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(Color.BLACK);

        camera.update();

        batch.setProjectionMatrix(camera.combined);

        world.update(Math.min(delta, 0.05f));
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {

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


}
