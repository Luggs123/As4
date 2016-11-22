package pack_main;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SandboxFX extends Application implements pack_pet.InterfacePet {

	private static final int COLUMNS = 8;
	private static final int COUNT = 40;
	private static final int OFFSET_X = 0;
	private static final int OFFSET_Y = 0;
	private static final int WIDTH = 410;
	private static final int HEIGHT = 449;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {
		primaryStage.setTitle("The Horse in Motion");

		final ImageView imageView = new ImageView(SPRITE_SHEET);
		imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));

		final Animation animation = new SpriteAnimation(imageView, Duration.millis(1200), COUNT, COLUMNS, OFFSET_X,
				OFFSET_Y, WIDTH, HEIGHT);
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();

		Group group = new Group();
		group.getChildren().addAll(imageView);

		Scene scene = new Scene(group, 800, 600);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
