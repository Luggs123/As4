package pack_main;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;

public class ClsHandlers implements EventHandler<Event> {

	@Override
	public void handle(Event event) {
		Object source = event.getSource();
		/*** ClsMain ***/
		if (source == ClsMain.btnStartReplay) { // Open friends display.
			ClsMain.play();
			ClsMain.btnStartReplay.setDisable(true);
		}

		else {
			Platform.exit();
		}
	}
}