package pack_main;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
	static Animation seq_1, seq_2, seq_3, seq_4, seq_5;
	static SequentialTransition mainSeq;
	static ParallelTransition animSeqs;
	
	// House's ImageView and SpriteAnimation are defined here since it won't be a ClsPet object.
	final static ImageView houseImageView = new ImageView(SPRITE_SHEET);
	final static SpriteAnimation houseAnimation = new SpriteAnimation(houseImageView, Duration.millis(1200), 40, 8, 0, 0, 410, 449);

	public void start(Stage primaryStage) {
		primaryStage.setTitle("The Horse in Motion");
		VBox mainPane = new VBox();
		HBox menuButtons = new HBox(15);
		Pane mainAnimation = new Pane();
		
		/*********************
		 * SPRITE ANIMATIONS *
		 *********************/
		// Initializing the pets and their animation objects.
		ClsPet dog = new ClsPet("Dog", Duration.millis(1200), 3, 3, 0, 2245, SRITE_WIDTH, SRITE_WIDTH);
		dog.getPetAnim().setCycleCount(Animation.INDEFINITE);
		dog.getPetAnim().setAutoReverse(true);
		dog.getPetImage().setX(200 - 16);
		dog.getPetImage().setY(40 - 16);
		dog.getPetImage().setScaleX(1);
		dog.getPetImage().setScaleY(1);
		dog.getPetImage().setOpacity(0);
		
		ClsPet cat = new ClsPet("Cat", Duration.millis(800), 3, 3, 96, 2245, SRITE_WIDTH, SRITE_WIDTH);
		cat.getPetAnim().setCycleCount(Animation.INDEFINITE);
		cat.getPetAnim().setAutoReverse(true);
		cat.getPetImage().setX(800 - 16);
		cat.getPetImage().setY(40 - 16);
		cat.getPetImage().setOpacity(0);
		
		ClsPet mouse = new ClsPet("Mouse", Duration.millis(600), 3, 3, 192, 2245, SRITE_WIDTH, SRITE_WIDTH);
		mouse.getPetAnim().setCycleCount(Animation.INDEFINITE);
		mouse.getPetAnim().setAutoReverse(true);
		mouse.getPetImage().setX(800 - 16);
		mouse.getPetImage().setY(450 - 16);
		mouse.getPetImage().setOpacity(0);

		// The house's animation object.
		houseImageView.setViewport(new Rectangle2D(0, 0, 410, 449));
		houseAnimation.setCycleCount(Animation.INDEFINITE);
		houseImageView.setX(200 - 205);
		houseImageView.setY(450 - 224.5);
		houseImageView.setOpacity(0);
		
		animSeqs = new ParallelTransition(dog.getPetAnim(), cat.getPetAnim(), mouse.getPetAnim(), houseAnimation);
		
		// Generate the path.
		Path path = PathBuilder.create()
				.elements(
						new MoveTo(200, 40),
						new LineTo(800, 40),
						new LineTo(800, 450),
						new LineTo(200, 450),
						new LineTo(200, 40))
						.build();
		
		/**************
		 * SEQUENCE 1 *
		 **************/
		// Fading and scaling the sprites.
		FadeTransition dogFade = new FadeTransition(Duration.millis(3000), dog.getPetImage());
		dogFade.setCycleCount(1);
		dogFade.setFromValue(0);
		dogFade.setToValue(1);
		
		ScaleTransition dogScale = new ScaleTransition(Duration.millis(3000), dog.getPetImage());
		dogScale.setCycleCount(1);
		dogScale.setFromX(0);
		dogScale.setFromY(0);
		dogScale.setToX(2);
		dogScale.setToY(2);
		
		// Add the fading and scaling transition per-pet to their own sequence.
		ParallelTransition dog_seq = new ParallelTransition(dogFade, dogScale);
		
		FadeTransition catFade = new FadeTransition(Duration.millis(3000), cat.getPetImage());
		catFade.setCycleCount(1);
		catFade.setFromValue(0);
		catFade.setToValue(1);
		
		ScaleTransition catScale = new ScaleTransition(Duration.millis(3000), cat.getPetImage());
		catScale.setCycleCount(1);
		catScale.setFromX(0);
		catScale.setFromY(0);
		catScale.setToX(1.5);
		catScale.setToY(1.5);
		
		ParallelTransition cat_seq = new ParallelTransition(catFade, catScale);
		
		FadeTransition mouseFade = new FadeTransition(Duration.millis(3000), mouse.getPetImage());
		mouseFade.setCycleCount(1);
		mouseFade.setFromValue(0);
		mouseFade.setToValue(1);
		
		ScaleTransition mouseScale = new ScaleTransition(Duration.millis(3000), mouse.getPetImage());
		mouseScale.setCycleCount(1);
		mouseScale.setFromX(0);
		mouseScale.setFromY(0);
		mouseScale.setToX(1);
		mouseScale.setToY(1);
		
		ParallelTransition mouse_seq = new ParallelTransition(mouseFade, mouseScale);
		
		FadeTransition houseFade = new FadeTransition(Duration.millis(3000), houseImageView);
		houseFade.setCycleCount(1);
		houseFade.setFromValue(0);
		houseFade.setToValue(1);
		
		ScaleTransition houseScale = new ScaleTransition(Duration.millis(3000), houseImageView);
		houseScale.setCycleCount(0);
		houseScale.setFromX(0);
		houseScale.setFromY(0);
		houseScale.setToX(0.4);
		houseScale.setToY(0.4);
		
		ParallelTransition house_seq = new ParallelTransition(houseFade, houseScale);
		
		// Add the respective pet sequences to sequence 1.
		seq_1 = new SequentialTransition(dog_seq, cat_seq, mouse_seq, house_seq);
		seq_1.setCycleCount(1);
		
		/**************
		 * SEQUENCE 2 *
		 **************/
		// Fade in the arrows.
		ImageView arrow1a = getArrow();
		arrow1a.setX(340);
		arrow1a.setY(60);
		FadeTransition arrow1aFade = getArrowFade(arrow1a);
		
		ImageView arrow2a = getArrow();
		arrow2a.setX(450);
		arrow2a.setY(60);
		FadeTransition arrow2aFade = getArrowFade(arrow2a);
		
		ImageView arrow3a = getArrow();
		arrow3a.setX(560);
		arrow3a.setY(60);
		FadeTransition arrow3aFade = getArrowFade(arrow3a);
		
		ImageView arrow1b = getArrow();
		arrow1b.setX(720);
		arrow1b.setY(100);
		arrow1b.setRotate(90);
		FadeTransition arrow1bFade = getArrowFade(arrow1b);
		
		ImageView arrow2b = getArrow();
		arrow2b.setX(720);
		arrow2b.setY(200);
		arrow2b.setRotate(90);
		FadeTransition arrow2bFade = getArrowFade(arrow2b);
		
		ImageView arrow3b = getArrow();
		arrow3b.setX(720);
		arrow3b.setY(300);
		arrow3b.setRotate(90);
		FadeTransition arrow3bFade = getArrowFade(arrow3b);
		
		ImageView arrow1c = getArrow();
		arrow1c.setX(600);
		arrow1c.setY(460);
		arrow1c.setRotate(180);
		FadeTransition arrow1cFade = getArrowFade(arrow1c);
		
		ImageView arrow2c = getArrow();
		arrow2c.setX(490);
		arrow2c.setY(460);
		arrow2c.setRotate(180);
		FadeTransition arrow2cFade = getArrowFade(arrow2c);
		
		ImageView arrow3c = getArrow();
		arrow3c.setX(380);
		arrow3c.setY(460);
		arrow3c.setRotate(180);
		FadeTransition arrow3cFade = getArrowFade(arrow3c);
		
		ParallelTransition arrow1 = new ParallelTransition(arrow1aFade, arrow1bFade, arrow1cFade);
		ParallelTransition arrow2 = new ParallelTransition(arrow2aFade, arrow2bFade, arrow2cFade);
		ParallelTransition arrow3 = new ParallelTransition(arrow3aFade, arrow3bFade, arrow3cFade);
		
		seq_2 = new SequentialTransition(arrow1, arrow2, arrow3);
		seq_2.setCycleCount(1);
                
                /**************
		 * SEQUENCE 3 *
		 **************/
                TranslateTransition dogTranslate = new TranslateTransition(Duration.millis(5000),dog.getPetImage());
                dogTranslate.setToX(600);
                
                TranslateTransition catTranslate = new TranslateTransition(Duration.millis(5000),cat.getPetImage());
                catTranslate.setToY(400);
                
                TranslateTransition mouseTranslate = new TranslateTransition(Duration.millis(5000),mouse.getPetImage());
                mouseTranslate.setToX(-600);
                        
                seq_3 = new ParallelTransition(dogTranslate,catTranslate,mouseTranslate);
                
		mainSeq = new SequentialTransition(seq_1, seq_2, seq_3/*, seq_4, seq_5*/);
		
		// Add the panes to the main window.
		mainAnimation.getChildren().addAll(path, dog.getPetImage(), cat.getPetImage(), mouse.getPetImage(), houseImageView, arrow1a, arrow2a, arrow3a, arrow1b, arrow2b, arrow3b, arrow1c, arrow2c, arrow3c);
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
	
	private static ImageView getArrow() {
		ImageView arrow = new ImageView(SPRITE_SHEET);
		arrow.setViewport(new Rectangle2D(322, 2262, 80, 61));
		arrow.setOpacity(0);
		
		return arrow;
	}
	
	private static FadeTransition getArrowFade(ImageView arrow) {
		FadeTransition arrowFade = new FadeTransition(Duration.millis(2000), arrow);
		arrowFade.setCycleCount(1);
		arrowFade.setFromValue(0);
		arrowFade.setToValue(1);
		
		return arrowFade;
	}
}
