package pack_main;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PathTransitionBuilder;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;
import pack_pet.ClsPet;

public class SandboxFX extends Application implements pack_pet.InterfacePet {
	
	// Primary animation objects.
	static ParallelTransition seq_1, seq_2, seq_3, seq_4, seq_5;
	static SequentialTransition mainSeq;
	static ParallelTransition animSeqs;
	
	// House's ImageView and SpriteAnimation are defined here since it won't be a ClsPet object.
	final static ImageView houseImageView = new ImageView(SPRITE_SHEET);
	final static SpriteAnimation houseAnimation = new SpriteAnimation(houseImageView, Duration.millis(1200), 40, 8, 0, 0, 410, 449);

	public void start(Stage primaryStage) {
		primaryStage.setTitle("The Horse in Motion");
		VBox mainPane = new VBox();
		HBox menuButtons = new HBox(15);
		Group mainAnimation = new Group();
		
		/***
		 * SPRITE ANIMATIONS
		 */
		// Initializing the pets and their animation objects.
		ClsPet dog = new ClsPet("Dog", Duration.millis(1500), 40, 8, 0, 0, 410, 449);
		dog.getPetAnim().setCycleCount(Animation.INDEFINITE);
		dog.getPetImage().setTranslateX(40);
		
		ClsPet cat = new ClsPet("Cat", Duration.millis(1500), 40, 8, 0, 0, 410, 449);
		cat.getPetAnim().setCycleCount(Animation.INDEFINITE);
		cat.getPetImage().setTranslateX(550);
		
		ClsPet mouse = new ClsPet("Mouse", Duration.millis(1500), 40, 8, 0, 0, 410, 449);
		mouse.getPetAnim().setCycleCount(Animation.INDEFINITE);
		mouse.getPetImage().setTranslateX(550);
		mouse.getPetImage().setTranslateY(300);

		// The house's animation.
		houseImageView.setViewport(new Rectangle2D(0, 0, 410, 449));
		houseAnimation.setCycleCount(Animation.INDEFINITE);
		houseImageView.setTranslateX(40);
		houseImageView.setTranslateY(300);
		
		animSeqs = new ParallelTransition();
		
		animSeqs.getChildren().addAll(dog.getPetAnim(), cat.getPetAnim(), mouse.getPetAnim(), houseAnimation);
		
		// Generate the path.
		PathTransition pathTransition = new PathTransition();
		Path path = PathBuilder.create()
				.elements(
						new MoveTo(450, 449),
						new LineTo(450, 449),
						new LineTo(550, 300),
						new LineTo(50, 400),
						new LineTo(50, 50))
						.build();
		
		/***
		 * SEQUENCE 1
		 */
		FadeTransition houseFade = new FadeTransition(Duration.millis(1000), houseImageView);
		houseFade.setCycleCount(0);
		houseFade.setFromValue(0);
		houseFade.setToValue(1);
		
		ScaleTransition houseScale = new ScaleTransition(Duration.millis(1500), houseImageView);
		houseScale.setCycleCount(0);
		houseScale.setFromX(0);
		houseScale.setFromY(0);
		houseScale.setToX(0.5);
		houseScale.setToY(0.5);
		
		seq_1 = new ParallelTransition(houseScale);
		
		mainSeq = new SequentialTransition(seq_1/*, seq_2, seq_3, seq_4, seq_5*/);
		
		// Add the panes to the main window.
		mainAnimation.getChildren().addAll(path, dog.getPetImage(), cat.getPetImage(), mouse.getPetImage(), houseImageView);
		mainPane.getChildren().addAll(mainAnimation, menuButtons);

		Scene scene = new Scene(mainPane, 1000, 800);

		primaryStage.setScene(scene);
		primaryStage.show();
		play();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void play() {
		animSeqs.play();
		mainSeq.play();
	}
}
