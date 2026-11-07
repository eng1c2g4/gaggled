package Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import Screens.GameState;
import com.github.eng1c2g4.eng1c2g4.entity.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


public class GameScreen implements Screen {
    private final GameState main;
    private World world;
    private OrthographicCamera camera;
    private SpriteBatch batch;


    public GameScreen(GameState main)  {
        this.main = main;

        this.camera = new OrthographicCamera(1280,720);
        this.batch = new SpriteBatch();

        world = new World();
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(new com.badlogic.gdx.InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.ESCAPE) {
                    main.changeScreen(GameState.PAUSE);
                    return true;
                }
                return false;
            }
        });
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
