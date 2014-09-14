import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppointmentController implements Initializable {
	@FXML
	Text firstName;
	@FXML
	Text lastName;
	Scannable scannable;
	@FXML
	ImageView idImage;
	Timer timer;
	@FXML
	Button correctBtn;
	@FXML
	Button rescanBtn;

	public void initialize(URL location, ResourceBundle resources) {
		timer = new Timer(true);
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						getID();
					}
				});

			}
		}, 1000, 100);
	}

	public void getID() {
		scannable = TTIFiles.getScannable();
		if (scannable != null) {
			timer.cancel();
			firstName.setText(scannable.getInfo()[Scannable.FIRST_NAME]);
			lastName.setText(scannable.getInfo()[Scannable.LAST_NAME]);
			Global.FIRST_NAME = scannable.getInfo()[Scannable.FIRST_NAME];
			Global.LAST_NAME = scannable.getInfo()[Scannable.LAST_NAME];
			idImage.setImage(scannable.getFace());
			correctBtn.setDisable(false);
			rescanBtn.setDisable(false);
		}
	}

	public void rescan() {
		firstName.setText("Please");
		lastName.setText("Wait");
		correctBtn.setDisable(true);
		rescanBtn.setDisable(true);
		idImage.setImage(null);
		timer = new Timer(true);
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						getID();
					}
				});

			}
		}, 1000, 100);
	}

	public void correctInfo() {
		Appointment[] appointments = DBHandler.checkAppointment(
				Global.FIRST_NAME, Global.LAST_NAME);
		if (appointments.length > 0) {
			Global.appointments = appointments;
			try {
				Parent root = (Parent) FXMLLoader.load(getClass().getResource(
						"DoorOpened.fxml"));
				Global.STAGE.getScene().setRoot(root);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			notFound();
		}
	}

	public void notFound() {
		try {
			Parent root = (Parent) FXMLLoader.load(getClass().getResource(
					"requestEntry1.fxml"));
			Global.STAGE.getScene().setRoot(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
