package sgAsteroids;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.Map;
import java.util.Random;


public class AsteroidsApp extends GameApplication {

	private Entity player;

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(800);
		settings.setHeight(600);
		settings.setTitle("Asteroids");
		settings.setVersion("0.1");
		settings.setConfigClass(Config.class);
	}


	@Override
	protected void initInput() {
		Input input = FXGL.getInput();


		input.addAction(new UserAction("Direction") {

			@Override
			protected void onAction() {
				Input input = FXGL.getInput();
				Point2D cursorPointInWorld = input.getMousePositionWorld();
				int coordinates = (int) cursorPointInWorld.getX();
				FXGL.getGameState().setValue("pixelsMoved", coordinates);
			}
		}, KeyCode.F);

		int speed = 1;

		input.addAction(new UserAction("Move Right") {
			@Override
			protected void onAction() {
				player.translateX(speed); // move right 5 pixels
				//player.rotateBy(5);

				FXGL.getGameState().increment("pixelsMoved", +5);
			}
		}, KeyCode.D);

		input.addAction(new UserAction("Move Left") {
			@Override
			protected void onAction() {
				player.translateX(speed * -1); // move left 5 pixels
				//player.rotateBy(355);
				FXGL.getGameState().increment("pixelsMoved", +5);
			}
		}, KeyCode.A);

		input.addAction(new UserAction("Move Up") {
			@Override
			protected void onAction() {
				player.translateY(speed * -1); // move up 5 pixels
				FXGL.getGameState().increment("pixelsMoved", +5);
			}
		}, KeyCode.W);

		input.addAction(new UserAction("Move Down") {
			@Override
			protected void onAction() {
				player.translateY(speed); // move down 5 pixels
				FXGL.getGameState().increment("pixelsMoved", +5);
			}
		}, KeyCode.S);

		input.addAction(new UserAction("Play Sound") {
			@Override
			protected void onActionBegin() {
				FXGL.play("laser.wav");
			}
		}, KeyCode.SPACE);
	}

	@Override
	protected void initGame() {

		player = FXGL.entityBuilder()
				.type(EntityType.PLAYER)
				.bbox(new HitBox("PLAYER_BODY", BoundingShape.box(32,32)))
				.at(300, 300)
				.view("spaceship.png")
				.with(new CollidableComponent(true))
				.with(new AnimationComponent())
				.buildAndAttach();

		FXGL.entityBuilder()
				.type(EntityType.ASTEROID)
				.at(500, 200)
				.viewWithBBox(new Circle(15, Color.DARKGRAY))
				.with(new CollidableComponent(true))
				.buildAndAttach();
		
		// set background
		FXGL.entityBuilder()
				.at(0, 0)
				.zIndex(-1) // behind everything
				.view(new Rectangle(800, 600, Color.BLACK))
				.buildAndAttach();



		// create random asteroids
		createRandomAsteroids();
/*
		PhysicsComponent physics = new PhysicsComponent();

		physics.setBodyType(BodyType.DYNAMIC);

// these are direct jbox2d objects, so we don't actually introduce new API
		FixtureDef fd = new FixtureDef();
		fd.setDensity(0.7f);
		fd.setRestitution(0.3f);
		physics.setFixtureDef(fd);


		player.addComponent(physics);

		FXGL.getGameWorld().addEntity(player);

 */
	}


	private void createRandomAsteroids() {

		FXGL.getGameWorld().addEntityFactory(new MyAsteroidFactory());

		int numOfAsteroids = 10;
		for(int i = 0; i < numOfAsteroids; i++) {
			int rx = new Random().nextInt(800);
			int ry = new Random().nextInt(600);
			SpawnData sd = new SpawnData(rx, ry);
			FXGL.spawn("asteroid", sd);
		}
	}

	@Override
	protected void initPhysics() {
		FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.ASTEROID) {

			// order of types is the same as passed into the constructor
			@Override
			protected void onCollisionBegin(Entity player, Entity asteroid) {
				asteroid.removeFromWorld();
			}
		});
	}

	@Override
	protected void initUI() {
		Text textPixels = new Text();
		textPixels.setTranslateX(50); // x = 50
		textPixels.setTranslateY(100); // y = 100

		textPixels.textProperty().bind(FXGL.getGameState().intProperty("pixelsMoved").asString());



		FXGL.getGameScene().addUINode(textPixels); // add to the scene graph

		Texture userTexture = FXGL.getAssetLoader().loadTexture("user.png");

		userTexture.setTranslateX(50);
		userTexture.setTranslateY(450);

		FXGL.getGameScene().addUINode(userTexture);
	}

	@Override
	protected void initGameVars(Map<String, Object> vars) {
		vars.put("pixelsMoved", 0);
	}



	public static void main(String[] args) {
		launch(args);
	}

}
