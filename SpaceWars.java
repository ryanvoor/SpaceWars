/**
* class that controls the events for a game of SpaceWars
* @author Ryan Voor
* @version 1.0
*/
import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

public class SpaceWars extends Application {

  String TITLE = "Space Wars";

  int WIDTH_OF_TILE = 100;
  int HEIGHT_OF_TILE = 100;
  String PLACEHOLDER_IMAGE_LOCAL_URL = "/images/advance_wars_mech.png";
  Map currentMap;

  /**
  * Primary controller method for the application
  * @param stage the stage for the window in which the application will run
  */
  @Override public void start(Stage stage) {

    // not actually using this at this point, just a proof of concept thing I guess
    String mapSelection = getMapSelection();

    currentMap = Map.getTestMap1();
    Parent container = (Parent) getGrid(currentMap);

    Scene scene = new Scene(container);

    showWindow(stage, scene);
  }

  /**
  * method that creates a Node to be put into the stage of the application
  * @return Node the container of the grid of Tiles of the Map to be played on
  */
  private Node getGrid(Map map) {
    HBox container = new HBox();
    for (int i = 0; i < map.getWidth(); i++) {
      VBox column = new VBox();
      for (int j = 0; j < map.getHeight(); j++) {

        TileContainer tileContainer = new TileContainer(new Image(PLACEHOLDER_IMAGE_LOCAL_URL), map.getTile(i, j).getTile());

        tileContainer.setOnMouseClicked(getTileMouseClickedEvent());
        tileContainer.setFitWidth(WIDTH_OF_TILE);
        tileContainer.setFitHeight(HEIGHT_OF_TILE);

        column.getChildren().add(tileContainer);
      }
      container.getChildren().add(column);
    }
    return container;
  }

  /**
  * asks the user for the map to be played on
  * @return String the name of the map to be played on
  */
  private String getMapSelection() {
    // lots of temporary stuff in this method
    // will completely overhaul this once I start making a menu screen

    System.out.print("Enter 1 for first map: ");
    Boolean succeeded = false;
    int input = 0;
    while (!succeeded) {
      try {
        input = Integer.parseInt(System.console().readLine());
        if (input != 0) {
          succeeded = true;
        } else {
          throw new Exception();
        }
      } catch (Exception e) {
        System.out.print("Please, enter 1 for first map: ");
      }
    }

    String selectedMap = "";
    switch (input) {
      case 1: {
        selectedMap = "testMap1";
      }
    }

    return selectedMap;
  }

  /**
  * private helper method that sets the title of the stage, and sets the scene
  * @param stage the stage for the window in which the application runs
  * @param scene the scene to be put into the stage/window
  */
  private void showWindow(Stage stage, Scene scene) {
    stage.setTitle(TITLE);
    stage.setScene(scene);
    stage.sizeToScene();
    stage.show();
  }

  /**
  * gets the EventHandler for an individual tile on a map
  * @return EventHandler the method that will run when a Tile get's clicked on
  */
  private EventHandler getTileMouseClickedEvent() {
    return (e -> {
      // temp
      System.out.println(getTileFromPixelCoordinates((int) ((MouseEvent) e).getX(), (int) ((MouseEvent) e).getY(), currentMap).getTile().getTerrain());
    });
  }

  /**
  * private helper method that finds the tile on the input map that contains
  * the input coordinates's pixel
  * @param width the x-coordinate (horizontal) (starts in top-right)
  * @param height the y-coordinate (vertical) (starts in top-left)
  * @param map the Map to search through
  * @return TileContainer the tile container that contains the pixel with this coordinate
  */
  private TileContainer getTileFromPixelCoordinates(int width, int height, Map map) {
    int column = width / WIDTH_OF_TILE;
    int row = height / HEIGHT_OF_TILE;
    return map.getTile(column, row);
  }
}
