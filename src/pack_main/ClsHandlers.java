package pack_main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

public class ClsHandlers implements EventHandler<Event> {

	@Override
	public void handle(Event event) {
		Object source = event.getSource();
		
		if (source == ClsMain.btnStartReplay) {
			ClsMain.play();
			ClsMain.btnStartReplay.setDisable(true);
		} else {
			Platform.exit();
		}
	}
}

class ClsActionHandlers implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		ClsMain.animSeqs.stop(); // Stop the sprites from running in the background.
		if (!ClsMain.btnStartReplay.getText().equals("Play Again")) { ClsMain.btnStartReplay.setText("Play Again"); }
		ClsMain.btnStartReplay.setDisable(false);
	}
}