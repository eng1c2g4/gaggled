package Screens;

import com.badlogic.gdx.Game;

public final class GameState extends Game {


    private MainScreen mainScreen;
    private MenuScreen menuScreen;
    private PauseScreen pauseScreen;

    public final static int MENU = 0;
    public final static int MAIN = 1;
    public final static int PAUSE = 2;


    @Override
    public void create () {
        menuScreen = new MenuScreen(this);
        setScreen(MenuScreen);
    }

    public void changeScreen(int screen) {
        switch(screen){
            case MENU:
                if (menuScreen == null) menuScreen = new MenuScreen(this);
                this.setScreen(menuScreen);
                break;
            case MAIN:
                if (mainScreen == null) mainScreen = new MainScreen(this);
                this.setScreen(mainScreen);
                break;
            case PAUSE:
                if (pauseScreen == null) pauseScreen = new PauseScreen(this);
                this.setScreen(pauseScreen);
                break;
        }
    }
}