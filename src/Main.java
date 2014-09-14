

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class Main
  extends Application
{
  Scannable[] scannables;
  @FXML
  ListView<Scannable> namesList;
  ObservableList<Scannable> items;
  
  public void start(Stage stage)
    throws Exception
  {
    Parent root = (Parent)FXMLLoader.load(getClass().getResource("DoorKeepr.fxml"));
    Scene scene = new Scene(root);
    scene.getStylesheets().add("doorkeepr.css");
    stage.setScene(scene);
    stage.setFullScreen(true);
    stage.show();
    Global.STAGE = stage;
  }
  
  public static void main(String[] args)
  {
    launch(args);
  }
}