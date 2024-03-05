package com.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.main.Main;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		// get user's monitor resolution
		/* JUSTIFICATION:
		this is needed so that when the game is opened on any computer and the
		window will be the same proportion on any resolution
		 */
		DisplayMode displayMode = Lwjgl3ApplicationConfiguration.getDisplayMode();
		// set windowed mode using the monitor's resolution
		config.setWindowedMode(displayMode.width - 50, displayMode.height - 50);
		config.setResizable(true);
		config.setForegroundFPS(60);
		// Don't worry about this for now
		//config.addIcon("icon_128.png", FileType.Internal); // icon for Mac
		//config.addIcon("icon_32.png", FileType.Internal); // icon for linux
		//config.addIcon("icon_16.png", FileType.Internal); // icon for windows
		config.setTitle("Heslington_Hustle");
		new Lwjgl3Application(new Main(), config);
	}
}
