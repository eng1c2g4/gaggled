package Screens;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.eng1c2g4.eng1c2g4.GameState;
import com.github.eng1c2g4.eng1c2g4.entity.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.github.eng1c2g4.eng1c2g4.entity.component.PositionComponent;


public class GameScreen implements Screen {
    static final int WORLD_WIDTH = 1280;
    static final int WORLD_HEIGHT = 720;

    private final GameState main;
    private World world;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Sprite mapSprite;

    public GameScreen(GameState main)  {
        this.mapSprite = new Sprite(new Texture("map/map_ground_layer.png"));
        mapSprite.setPosition(0, 0);
        mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);
        this.main = main;
        world = new World();
        this.batch = new SpriteBatch();

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
        batch.begin();
        mapSprite.draw(batch);
        batch.end();
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
