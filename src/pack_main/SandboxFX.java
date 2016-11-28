package pack_main;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import pack_pet.ClsPet;

public class SandboxFX extends Application implements pack_pet.InterfacePet {
	
	final static ImageView houseImageView = new ImageView(SPRITE_SHEET);
	final static SpriteAnimation houseAnimation = new SpriteAnimation(houseImageView, Duration.millis(1200), 40, 8, 0, 0, 410, 449);

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {
		primaryStage.setTitle("The Horse in Motion");
		VBox mainPane = new VBox();
		HBox menuButtons = new HBox(15);
		Group mainAnimation = new Group();
		
		// Initializing the pets and their animation objects.
		ClsPet dog = new ClsPet("Dog", Duration.millis(1500), 0, 0, 0, 0, 0, 0);
		ClsPet cat = new ClsPet("Cat", Duration.millis(1500), 0, 0, 0, 0, 0, 0);
		ClsPet mouse = new ClsPet("Mouse", Duration.millis(1500), 0, 0, 0, 0, 0, 0);

		// The house's animation.
		houseImageView.setViewport(new Rectangle2D(0, 0, 410, 449));
		houseAnimation.setCycleCount(Animation.INDEFINITE);
		
		// Add the panes to the main window.
		mainAnimation.getChildren().addAll(houseImageView);
		mainPane.getChildren().addAll(mainAnimation, menuButtons);

		Scene scene = new Scene(mainPane, 800, 600);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void play() {
		houseAnimation.play();
	}
}
