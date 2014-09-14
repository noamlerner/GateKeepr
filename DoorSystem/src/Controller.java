import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller implements Initializable {
	@FXML
	Text welcomeText;
	Timeline timer;
	String[][] tenants;

	public void initialize(URL location, ResourceBundle resources) {
		String address = "building";
		try {
			File buildingID = new File("C:\\buildingID.txt");
			Scanner reader = new Scanner(buildingID);
			Global.BUILDING_ID = reader.nextLine();
			address = DBHandler.getAddress();
			reader.close();
			tenants = DBHandler.getTenants();
		} catch (Exception e) {
			e.printStackTrace();
		}
		welcomeText.setText("Welcome To " + address);
		timer = new Timeline(new KeyFrame(Duration.millis(500),
				new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						tenantsCheck();
					}
				}));
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
	}

	public void tenantsCheck() {
		Scannable scannable = TTIFiles.getScannable();
		if (scannable != null) {
			scannable.fullName = scannable.fullName.replace(" ", "");
			for (int i = 0; i < tenants.length; i++) {
				tenants[i][0] = tenants[i][0].replace(" ", "");
				if (scannable.fullName.equals(tenants[i][0])) {
					try {
						Parent root = (Parent) FXMLLoader.load(getClass()
								.getResource("DoorOpened.fxml"));
						Global.STAGE.getScene().setRoot(root);
					} catch (Exception e) {
						e.printStackTrace();
					}
					timer.stop();
				}
			}
		}
	}

	public void appointment() {
		try {
			timer.stop();
			Parent root = (Parent) FXMLLoader.load(getClass().getResource(
					"appointment1.fxml"));
			Global.STAGE.getScene().setRoot(root);

		} catch (Exception e) {

		}
	}

	
}
