import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

public class AsteroidsApp extends GameApplication {

	public static void main(String[] args) {
        launch(args);
	}

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(800);
		settings.setHeight(600);
		settings.setTitle("Asteroids");
	}
}
