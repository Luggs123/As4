package pack_pet;

import javafx.scene.image.Image;
import pack_main.ClsMain;

public interface InterfacePet {
	final Image SPRITE_SHEET = new Image(ClsMain.resourceLoader("spritesheet.png"));
	
	final String EMP_STR = "";
	final String NEWLINE = System.getProperty("line.separator");
	
	final int SRITE_WIDTH = 32;
	final int BTN_WIDTH = 256;
}