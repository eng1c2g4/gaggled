package com.github.eng1c2g4.eng1c2g4.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.github.eng1c2g4.eng1c2g4.Main;

public final class Launcher {

    public static void main(String[] args) {
        new Lwjgl3Application(new Main(), buildConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration buildConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("eng1c2g4");
        configuration.useVsync(true);
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
        configuration.setWindowedMode(1280, 720);
        return configuration;
    }

}
