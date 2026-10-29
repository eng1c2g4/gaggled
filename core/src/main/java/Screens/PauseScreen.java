package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PauseScreen implements Screen {

    private GameState parent;

    public PauseScreen(GameState gameState) {
        parent = gameState;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        TextButton Resume = new TextButton("Resume");
        TextButton Settings = new TextButton("Settings");
        TextButton Quit = new TextButton("Quit");

        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glclearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear((GL20.GL_COLOR_BUFFER_BIT));
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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

    public void input
}