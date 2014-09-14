import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DoorUnlockedCtrl implements Initializable {
	boolean moveOn = false;
	Timeline timer;
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (Global.appointments != null) {
			for (int i = 0; i < Global.appointments.length; i++) {
				DBHandler.changeStatus(Global.appointments[i].getID());
			}
		}
		timer = new Timeline(new KeyFrame(Duration.seconds(5),
				new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						moveOn();
					}
				}));
		timer.play();
	}

	public void moveOn() {
		try {
			timer.stop();
			Global.tenantID = null;
			Global.appointments = null;
			Global.FIRST_NAME = null;
			Global.LAST_NAME = null;
			Parent root = (Parent) FXMLLoader.load(getClass().getResource(
					"DoorKeepr.fxml"));
			Global.STAGE.getScene().setRoot(root);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
