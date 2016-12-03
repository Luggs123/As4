package pack_main;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PathTransitionBuilder;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;
import pack_pet.ClsPet;

public class ClsMain extends Application implements pack_pet.InterfacePet {
	
	// Primary animation objects.
	static Animation seq_1, seq_2, seq_3, seq_4, seq_5;
	static SequentialTransition mainSeq;
	static ParallelTransition animSeqs;
	
	// House's ImageView and SpriteAnimation are defined here since it won't be a ClsPet object.
	final static ImageView houseImageView = new ImageView(SPRITE_SHEET);
	final static SpriteAnimation houseAnimation = new SpriteAnimation(houseImageView, Duration.millis(1200), 40, 8, 0, 0, 410, 449);

	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Assignment 4");
		VBox mainPane = new VBox();
		HBox menuButtons = new HBox(15);
		Pane mainAnimation = new Pane();
		
		/*********************
		 * SPRITE ANIMATIONS *
		 *********************/
		// Initializing the pets and their animation objects.
		ClsPet dog = new ClsPet("Dog", Duration.millis(600), 3, 3, 0, 2245, SRITE_WIDTH, SRITE_WIDTH);
		dog.getPetAnim().setCycleCount(Animation.INDEFINITE);
		dog.getPetAnim().setAutoReverse(true);
		dog.getPetImage().setX(200 - 16);
		dog.getPetImage().setY(40 - 16);
		dog.getPetImage().setOpacity(0);
		
		ClsPet cat = new ClsPet("Cat", Duration.millis(400), 3, 3, 96, 2245, SRITE_WIDTH, SRITE_WIDTH);
		cat.getPetAnim().setCycleCount(Animation.INDEFINITE);
		cat.getPetAnim().setAutoReverse(true);
		cat.getPetImage().setX(800 - 16);
		cat.getPetImage().setY(40 - 16);
		cat.getPetImage().setOpacity(0);
		
		ClsPet mouse = new ClsPet("Mouse", Duration.millis(300), 3, 3, 192, 2245, SRITE_WIDTH, SRITE_WIDTH);
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
		Path mainPath = PathBuilder.create()
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
		// Create the arrows and their fading animations.
		ImageView arrows[] = new ImageView[9];
		ParallelTransition arrowsFade[] = new ParallelTransition[]{ new ParallelTransition(), new ParallelTransition(), new ParallelTransition() };
		
		for (int i = 0; i < arrows.length; i++) {
			arrows[i] = new ImageView(SPRITE_SHEET);
			arrows[i].setViewport(new Rectangle2D(322, 2262, 80, 61));
			arrows[i].setOpacity(0);
			
			FadeTransition arrowFade = new FadeTransition(Duration.millis(2000), arrows[i]);
			arrowFade.setCycleCount(1);
			arrowFade.setFromValue(0);
			arrowFade.setToValue(1);
			
			// The first arrow in each section will fade in parallel, followed by the second and third arrows.
			arrowsFade[i % 3].getChildren().add(arrowFade);
		
			// Determine the coordinates of each arrow.
			int x = 0, y = 0;
			
			switch (i) {
			
			// Second set of arrows.
			case 3:
			case 4:
			case 5: {
				x = 720;
				y = 100 + ((i - 3) * 100);
				arrows[i].setRotate(90);
			} break;
			
			// Third set of arrows.
			case 6:
			case 7:
			case 8: { 
				x = 600 - ((i - 6) * 110);
				y = 460;
				arrows[i].setRotate(180);
			} break;
			
			// First set of arrows.
			default: {
				x = 340 + (i * 110);
				y = 60;
			}
			}
			
			arrows[i].setX(x);
			arrows[i].setY(y);
		}
		
		seq_2 = new SequentialTransition(arrowsFade[0], arrowsFade[1], arrowsFade[2]);
		seq_2.setCycleCount(1);
                
		/**************
		 * SEQUENCE 3 *
		 **************/
		TranslateTransition dogTranslate = new TranslateTransition(Duration.millis(6000), dog.getPetImage());
		dogTranslate.setToX(600);

		TranslateTransition catTranslate = new TranslateTransition(Duration.millis(6000), cat.getPetImage());
		catTranslate.setToY(400);

		TranslateTransition mouseTranslate = new TranslateTransition(Duration.millis(6000), mouse.getPetImage());
		mouseTranslate.setToX(-600);

		seq_3 = new ParallelTransition(dogTranslate, catTranslate, mouseTranslate);
		
		/**************
		 * SEQUENCE 4 *
		 **************/
		// Fade out all the objects on-screen.
		FadeTransition dogFadeOut = new FadeTransition(Duration.millis(2500), dog.getPetImage());
		dogFadeOut.setCycleCount(1);
		dogFadeOut.setFromValue(1);
		dogFadeOut.setToValue(0);
		
		FadeTransition catFadeOut = new FadeTransition(Duration.millis(2500), cat.getPetImage());
		catFadeOut.setCycleCount(1);
		catFadeOut.setFromValue(1);
		catFadeOut.setToValue(0);
		
		FadeTransition mouseFadeOut = new FadeTransition(Duration.millis(2500), mouse.getPetImage());
		mouseFadeOut.setCycleCount(1);
		mouseFadeOut.setFromValue(1);
		mouseFadeOut.setToValue(0);
		
		FadeTransition houseFadeOut = new FadeTransition(Duration.millis(2500), houseImageView);
		houseFadeOut.setCycleCount(1);
		houseFadeOut.setFromValue(1);
		houseFadeOut.setToValue(0);
		
		seq_4 = new ParallelTransition(dogFadeOut, catFadeOut, mouseFadeOut, houseFadeOut);
		
		for (int i = 0; i < 9; i++) {
			FadeTransition arrowFadeOut = new FadeTransition(Duration.millis(2500), arrows[i]);
			arrowFadeOut.setCycleCount(1);
			arrowFadeOut.setFromValue(1);
			arrowFadeOut.setToValue(0);
			((ParallelTransition) seq_4).getChildren().add(arrowFadeOut);
		}
		
		/**************
		 * SEQUENCE 5 *
		 **************/
		// Display the ending label.
		Label lblNotice = new Label("WOW CRINGE");
		lblNotice.setOpacity(0);
		
		FadeTransition lblFade = new FadeTransition(Duration.millis(1000), lblNotice);
		lblFade.setCycleCount(1);
		lblFade.setFromValue(0);
		lblFade.setToValue(1);
		
		Path seq_5Path = PathBuilder.create()
				.elements(
						new MoveTo(500, 40),
						new ArcTo(350, 250, 0, 750, 200, true, true),
						new ArcTo(100, 150, 0, 250, 200, true, false),
						new ArcTo(100, 100, 0, 500, 400, true, false))
						.build();
		
		PathTransition labelAnim = PathTransitionBuilder.create()
				.duration(Duration.millis(2000))
				.path(seq_5Path)
				.node(lblNotice)
				.build();
		
		seq_5 = new ParallelTransition(lblFade, labelAnim);
		
		// Add all the sequences to the main animation sequence.
		mainSeq = new SequentialTransition(seq_1, seq_2, seq_3, seq_4, seq_5);
		
		// Add the panes to the main window.
		mainAnimation.getChildren().addAll(mainPath, dog.getPetImage(), cat.getPetImage(), mouse.getPetImage(), houseImageView, lblNotice);
		for (int i = 0; i < 9; i++) {
			mainAnimation.getChildren().add(arrows[i]);
		}
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
