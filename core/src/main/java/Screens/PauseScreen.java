package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class PauseScreen implements Screen {

    private GameState parent;
    private Stage stage;
    private Skin skin;

    public PauseScreen(GameState gameState) {
        parent = gameState;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        //stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        //stage.draw();

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        Label title = new Label("PAUSED", skin);
        title.setFontScale(3f);
        table.add(title).padBottom(40);
        table.row();

        TextButton Resume = new TextButton("RESUME",skin);
        table.add(Resume).width(240).height(52).padBottom(15);
        table.row();
        Resume.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y){
                    parent.goback();
            }
        });

        TextButton Settings = new TextButton("SETTINGS",skin);
        table.add(Settings).width(240).height(52).padBottom(15);
        table.row();
        Settings.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y){
                parent.changeScreen(GameState.SETTINGS);
            }
        });

        TextButton Quit = new TextButton("QUIT",skin);
        table.add(Quit).width(240).height(52).padBottom(15);
        table.row();
        Quit.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });

        // stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear((GL20.GL_COLOR_BUFFER_BIT));
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
        skin.dispose();
    }

}