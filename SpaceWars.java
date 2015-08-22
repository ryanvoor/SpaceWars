/**
* class that controls the events for a game of SpaceWars
* @author Ryan Voor
* @version 1.0
*/
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SpaceWars extends Application {

  @Override public void start(Stage stage) {

    Scene scene = new Scene(new Group(new Text(25, 25, "Hello World!")));

    stage.setTitle("Space Wars");
    stage.setScene(scene);
    stage.sizeToScene();
    stage.show();
  }
}
