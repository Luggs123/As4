// Plays a sequence of frames from a sprite sheet in a Transition animation.
// Allows all the frames for a sprite to be stored in a single image so that multiple image calls are not necessary.

package pack_main;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {

	private final ImageView imageView;
	private final int frameCount;     // Number of frames in total.
	private final int frameColumns;   // Number of frames in a single column on the sprite sheet.
	private final int offsetX;        // Starting of the first frame on the X-axis.
	private final int offsetY;        // Ditto for Y.
	private final int width;          // Width of each frame.
	private final int height;         // Ditto for height.

	// Used to determine what the index of the last frame was.
	private int lastIndex;
	
	public SpriteAnimation(ImageView imageView, Duration duration, int frameCount, int frameColumns, int offsetX, int offsetY,
			int width, int height) {
		this.imageView = imageView;
		this.frameCount = frameCount;
		this.frameColumns = frameColumns;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width     = width;
		this.height    = height;
		setCycleDuration(duration);
		setInterpolator(Interpolator.LINEAR);
	}

	@Override
	protected void interpolate(double k) {
		final int index = (int) Math.min(Math.floor(k * this.frameCount), this.frameCount - 1);
		
		if (index != lastIndex) {
			final int x = (index % this.frameColumns) * width  + offsetX;
			final int y = (index / this.frameColumns) * height + offsetY;
			imageView.setViewport(new Rectangle2D(x, y, width, height));
			lastIndex = index;
		}
	}

	public ImageView getImageView() {
		return imageView;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}