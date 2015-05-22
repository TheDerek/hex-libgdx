package ml.derek.gdx.turnbased.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ml.derek.gdx.turnbased.Core;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		float scale = 1.5f;
		config.width *= scale;
		config.height *= scale;
		new LwjglApplication(new Core(), config);
	}
}
