import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class RequestEntryController implements Initializable {

	String[][] tenants;
	@FXML
	VBox vBox;
	@FXML
	ScrollPane scrollPane;

	public void initialize(URL arg0, ResourceBundle arg1) {
		tenants = DBHandler.getTenants();
		Timeline timer = new Timeline(new KeyFrame(Duration.millis(50),
				new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						realInit();
					}
				}));
		timer.play();
	}

	public void realInit() {

		vBox.setPrefWidth(scrollPane.getWidth() - 15);
		for (int i = 0; i < tenants.length; i++) {
			Button but = new Button(tenants[i][0]);
			but.getStyleClass().add("tenantBut");
			but.setPrefWidth(scrollPane.getWidth() - 5);
			but.setPrefHeight(50);
			but.setCursor(Cursor.HAND);
			but.setId("" + i);
			but.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Button button = (Button) event.getSource();
					int id = Integer.valueOf(button.getId()).intValue();
					Global.tenantID = tenants[id][1];
					try {
						Parent root = (Parent) FXMLLoader.load(getClass()
								.getResource("phoneNum1.fxml"));
						Global.STAGE.getScene().setRoot(root);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			vBox.getChildren().add(but);
		}
		vBox.setPrefHeight(scrollPane.getHeight());
	}

}
