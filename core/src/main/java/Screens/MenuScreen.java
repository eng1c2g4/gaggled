package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuScreen implements Screen {

    private GameState parent;
    private Stage stage;
    private Skin skin;

    public MenuScreen(GameState gameState) {
        parent = gameState;
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        Gdx.input.setInputProcessor(stage);
        //stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        //stage.draw();

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        table.center();
        table.defaults().center();
        table.padTop(20);
        stage.addActor(table);

        Label title = new Label("GAGGLED", skin);
        title.setFontScale(3.5f);
        table.add(title).padBottom(50);

        TextButton playButton = new TextButton("PLAY", skin);
        table.row();
        table.add(playButton).width(280).height(65).padBottom(18);
        playButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                parent.changeScreen(GameState.GAME);
            }
        });

        TextButton settingsButton = new TextButton("SETTINGS", skin);
        table.row();
        table.add(settingsButton).width(240).height(52).padBottom(14);
        settingsButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y){
                parent.changeScreen(GameState.SETTINGS);
            }

        });

        TextButton tutorialButton = new TextButton("TUTORIAL", skin);
        table.row();
        table.add(tutorialButton).width(240).height(52).padBottom(14);
        tutorialButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y){
                parent.changeScreen(GameState.TUTORIAL);
            }
        });

        TextButton quitButton = new TextButton("QUIT", skin);
        table.row();
        table.add(quitButton).width(220).height(52).padBottom(15);
        quitButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });
        //stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        Gdx.gl.glClear((GL20.GL_COLOR_BUFFER_BIT));
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height,true );
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
    }
}