package pack_pet;

import javafx.scene.image.ImageView;
import javafx.util.Duration;
import pack_main.SpriteAnimation;

public class ClsPet implements InterfacePet {

	String name = EMP_STR;
	ImageView petImage = new ImageView(SPRITE_SHEET);
	SpriteAnimation petAnim = null;

	public ClsPet(String name, Duration duration, int frameCount, int frameColumns,
			int offsetX, int offsetY, int width, int height) {
		this.name = name;
		this.petAnim = new SpriteAnimation(this.petImage, duration, frameCount, frameColumns, offsetX,
				offsetY, width, height);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageView getPetImage() {
		return petImage;
	}

	public void setPetImage(ImageView petImage) {
		this.petImage = petImage;
	}
}
