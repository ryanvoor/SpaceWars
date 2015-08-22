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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SpaceWars extends Application {

  int WIDTH_OF_WINDOW = 1300;
  int HEIGHT_OF_WINDOW = 800;

  @Override public void start(Stage stage) {

    Canvas canvas = new Canvas(WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    // experiment with passing in true for background_loading
    gc.drawImage(new Image("/images/advance_wars_mech.png"), 100, 100);

    // gifs do not animate just by drawing the image
    gc.drawImage(new Image("/images/advance_wars_units.gif"), 500, 500);

    Scene scene = new Scene(new Group(canvas));

    stage.setTitle("Space Wars");
    stage.setScene(scene);
    stage.sizeToScene();
    stage.show();
  }
}
