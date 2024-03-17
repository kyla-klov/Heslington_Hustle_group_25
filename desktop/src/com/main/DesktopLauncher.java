package com.main;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.Graphics.DisplayMode;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

import static com.badlogic.gdx.Gdx.graphics;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		// get user's monitor resolution
		DisplayMode displayMode = Lwjgl3ApplicationConfiguration.getDisplayMode();
		long primaryMonitor = GLFW.glfwGetPrimaryMonitor();
		GLFWVidMode vidMode = GLFW.glfwGetVideoMode(primaryMonitor);
		// set windowed mode using the monitor's resolution
		int windowX = vidMode.width();
		int windowY = displayMode.height - 90;
		config.setWindowedMode(windowX, windowY);
		config.setWindowPosition(0, 40);
		config.setResizable(true);
		config.setForegroundFPS(60);
		config.setWindowIcon(Files.FileType.Internal, "icon/icon_16.png"); // icon for windows
		config.setWindowIcon(Files.FileType.Internal, "icon/icon_32.png"); // icon for windows/linux
		config.setWindowIcon(Files.FileType.Internal, "icon/icon_128.png"); // icon for MacOS
		// config.setDecorated(false); //this can be used to remove the window
		config.setTitle("Heslington_Hustle");
		new Lwjgl3Application(new Main(), config);
	}
}
