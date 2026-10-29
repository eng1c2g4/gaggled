package com.github.eng1c2g4.eng1c2g4.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.eng1c2g4.eng1c2g4.gaggled;

public class GameScreen implements Screen {
    //Initialise camera, placeholder for textures and sprites
    private final gaggled game;
    private final OrthographicCamera camera;
    private final Texture backgroundTexture = new Texture("sprites/goose.png");
    private final Texture playerTexture = new Texture("sprites/student.png");

    protected Sprite playerSprite = new Sprite(playerTexture);
    public GameScreen(gaggled game) {
        //Initialise camera properly
        this.game = game;
        camera = new OrthographicCamera(30,30);
        camera.position.set(0, 0, 0);
        camera.update();
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        //Process inputs and then draw the sprites
        processInputs();
        camera.update();

        playerSprite.setSize(10,10);
        ScreenUtils.clear(Color.BLACK);
        game.sprites.setProjectionMatrix(camera.combined);

        game.sprites.begin();
        game.sprites.draw(backgroundTexture, 0, 0, 100, 100);
        playerSprite.draw(game.sprites);
        game.sprites.end();
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

    @Override
    public void dispose() {

    }
    /** Processes user inputs for the game screen */
    public void processInputs(){

        float delta = Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
            playerSprite.translateY(5*delta);
            camera.translate(0, 5*delta);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerSprite.translateX(-5*delta);
            camera.translate(-5*delta,0 );
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerSprite.translateY(-5*delta);
            camera.translate(0, -5*delta);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerSprite.translateX(5*delta);
            camera.translate(5*delta,0 );
        }

    }
}
