package Screens;

import com.badlogic.gdx.Game;
import java.util.Stack;

public class  GameState extends Game {

    private int score = 0;

    private MainScreen mainScreen;
    private MenuScreen menuScreen;
    private PauseScreen pauseScreen;
    private GameScreen gamescreen;
    private SettingsScreen settingsScreen;
    private TutorialScreen tutorialScreen;

    private int currentScreen = MENU;
    private int previousScreen = MENU;
    private Stack<Integer> screenHistory = new Stack<>();

    public final static int MENU = 0;
    public final static int MAIN = 1;
    public final static int PAUSE = 2;
    public final static int GAME = 3;
    public final static int SETTINGS = 4;
    public final static int TUTORIAL = 5;


    @Override
    public void create () {
        menuScreen = new MenuScreen(this);
        setScreen(menuScreen);
    }

    public void changeScreen(int screen) {
        changeScreen(screen, true);
    }

    private void changeScreen(int screen, boolean saveHistory) {
        if (saveHistory) {
            screenHistory.push(currentScreen);
        }
        currentScreen = screen;
        switch(screen) {
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
            case GAME:
                if (gamescreen == null) gamescreen = new GameScreen(this);
                this.setScreen(gamescreen);
                break;
            case SETTINGS:
                if (settingsScreen == null) {
                    settingsScreen = new SettingsScreen(this);
                }
                this.setScreen(settingsScreen);
                break;
            case TUTORIAL:
                if (tutorialScreen == null) {
                    tutorialScreen = new TutorialScreen(this);
                }
                this.setScreen(tutorialScreen);
                break;
        }
    }

    public void goback() {
        if (!screenHistory.empty()) {
            changeScreen(screenHistory.pop(), false);
        }
    }

    public void increment_score(int amount) {
        this.score += amount;
    }
}