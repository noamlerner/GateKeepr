import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PhoneNumCtrl implements Initializable {
	@FXML
	TextField phoneNumField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void submit() {
		String num = phoneNumField.getText();
		if (num.length() < 10) {
			invalidPhoneNum();
		} else {
			System.out.println(num);
			num = num.replace("-", "");
			num = num.replace(" ", "");
			DBHandler.requestAppointment(String.valueOf(Global.tenantID), num,
					Global.FIRST_NAME, Global.LAST_NAME);
			Global.tenantID = null;
			Global.appointments = null;
			Global.FIRST_NAME = null;
			Global.LAST_NAME = null;
			try {
				Parent root = (Parent) FXMLLoader.load(getClass().getResource(
						"DoorKeepr.fxml"));
				Global.STAGE.getScene().setRoot(root);
			} catch (Exception e) {

			}
		}

	}

	public void back() {
		try {
			Parent root = (Parent) FXMLLoader.load(getClass().getResource(
					"requestEntry1.fxml"));
			Global.STAGE.getScene().setRoot(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void invalidPhoneNum() {
		Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(Global.STAGE);
		VBox dialogVbox = new VBox(20);
		dialogVbox.getChildren().add(
				new Text("Please enter a valid phone number."));
		Scene dialogScene = new Scene(dialogVbox, 200, 100);
		dialog.setScene(dialogScene);
		dialog.show();
	}

}
