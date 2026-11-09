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
import com.github.eng1c2g4.eng1c2g4.GameState;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Scaling;

public class MenuScreen implements Screen {

    private GameState parent;
    private Stage stage;
    private Skin skin;


    //Menu UI loaded from assets/menu/
    private Texture backgroundTexture;
    private Texture buttonUpTexture;
    private Texture buttonOverTexture;
    private Texture buttonDownTexture;
    private Texture logoTexture;


    public MenuScreen(GameState gameState) {
        parent = gameState;
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        Gdx.input.setInputProcessor(stage);
        //stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        //stage.draw();

        //the menu background UI
        backgroundTexture = new Texture(Gdx.files.internal("menu/background.png"));
        Image background = new Image(backgroundTexture);
        background.setScaling(Scaling.fill);
        background.setFillParent(true);
        stage.addActor(background);





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

        logoTexture = new Texture(Gdx.files.internal("menu/logo.png"));
        Cell titleCell = table.getCell(title);
        titleCell.setActor(new Image(logoTexture));

        //button menu UI at its normal state , when its pressed and hovered over
        buttonUpTexture = new Texture(Gdx.files.internal("menu/button_up.png"));
        buttonDownTexture = new Texture(Gdx.files.internal("menu/button_down.png"));
        buttonOverTexture = new Texture(Gdx.files.internal("menu/button_over.png"));

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle(skin.get(TextButton.TextButtonStyle.class));

        buttonStyle.up = new NinePatchDrawable(new NinePatch(buttonUpTexture, 16, 16, 16, 16));
        buttonStyle.over = new NinePatchDrawable(new NinePatch(buttonOverTexture, 16, 16, 16, 16));
        buttonStyle.down = new NinePatchDrawable(new NinePatch(buttonDownTexture, 16, 16, 16, 16));


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



        //Apply the UI to the buttons of the menu
        playButton.setStyle(buttonStyle);
        settingsButton.setStyle(buttonStyle);
        tutorialButton.setStyle(buttonStyle);
        quitButton.setStyle(buttonStyle);
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

        //clear the menu ui assets loaded for the menu screen
        backgroundTexture.dispose();
        logoTexture.dispose();
        buttonUpTexture.dispose();
        buttonDownTexture.dispose();
        buttonOverTexture.dispose();
    }
}