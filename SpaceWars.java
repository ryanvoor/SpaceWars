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
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class SpaceWars extends Application {

  private String TITLE = "Space Wars";
  private final int WIDTH_OF_TILE = 100;
  private final int HEIGHT_OF_TILE = 100;
  private int WIDTH_OF_WINDOW;
  private int HEIGHT_OF_WINDOW;
  private final String PLACEHOLDER_IMAGE_LOCAL_URL_1 = "/images/advance_wars_mech.png";
  private final String PLACEHOLDER_IMAGE_LOCAL_URL_2 = "/images/advance_wars_variety_of_units.jpg";
  private final String PLACEHOLDER_IMAGE_LOCAL_URL_3 = "/images/trump_kissing.jpg";
  private final String PLACEHOLDER_IMAGE_LOCAL_URL_4 = "/images/obama_posing.jpg";
  private Map currentMap;
  private Canvas canvas;
  private GraphicsContext gc;

  /**
  * Primary controller method for the application
  * @param stage the stage for the window in which the application will run
  */
  @Override public void start(Stage stage) {

    // not actually using this at this point, just a proof of concept thing
    String mapSelection = getMapSelection();

    currentMap = MapCreator.testMap1();
    calculateWindowSize();

    canvas = new Canvas(WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW);
    gc = canvas.getGraphicsContext2D();

    Pane container = new Pane();
    container.getChildren().add(canvas);
    Scene scene = new Scene(container);

    scene.setOnKeyPressed(getSceneKeyPressedHandler());

    final long startNanoTime = System.nanoTime();

    new AnimationTimer() {
      public void handle(long currentNanoTime) {
        double t = (currentNanoTime - startNanoTime) / 1000000000.0;

        double x = 232 + 128 * Math.cos(t);
        double y = 232 + 128 * Math.sin(t);

        gc.clearRect(0, 0, WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW);
        writeCanvas(t);
      }
    }.start();

    showWindow(stage, scene);
  }

  /**
  * writes the current map to the canvas to be displayed
  * @param time the current time used to calculate frames of images
  */
  private void writeCanvas(double time) {
    int currentPixelWidth = 0;
    int currentPixelHeight = 0;
    for (int i = 0; i < currentMap.getWidth(); i++) {
      for (int j = 0; j < currentMap.getHeight(); j++) {
        TileContainer tileContainer = currentMap.getTile(i, j);

        // will need to change this so that it actually does something that makes sense with a cursor
        // because for the time being it is just a picture of Donald Trump that moves around
        if (tileContainer.isSelected()) {
          gc.drawImage(new Image(PLACEHOLDER_IMAGE_LOCAL_URL_4, WIDTH_OF_TILE, HEIGHT_OF_TILE, false, true), currentPixelWidth, currentPixelHeight);
        } else if (tileContainer.hasCursor()) {
          gc.drawImage(new Image(PLACEHOLDER_IMAGE_LOCAL_URL_3, WIDTH_OF_TILE, HEIGHT_OF_TILE, false, true), currentPixelWidth, currentPixelHeight);
        } else {
          gc.drawImage(tileContainer.getCurrentFrame(time), currentPixelWidth, currentPixelHeight);
        }

        currentPixelHeight += HEIGHT_OF_TILE;
      }
    currentPixelWidth += WIDTH_OF_TILE;
    currentPixelHeight = 0;
    }
  }

  /**
  * constructs then returns the EventHandler for key presses for the scene
  * @return EventHandler<KeyEvent> the EventHandler for general key presses
  */
  private EventHandler<KeyEvent> getSceneKeyPressedHandler() {
    return new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent e) {
        // I am getting ArrayIndexOutOfBounds exception at -1 when I input certain keys to try to move the cursor
        if (e.getCode().equals(KeyCode.RIGHT)) {
          currentMap.moveCursor(Direction.RIGHT);
        } else if (e.getCode().equals(KeyCode.LEFT)) {
          currentMap.moveCursor(Direction.LEFT);
        } else if (e.getCode().equals(KeyCode.UP)) {
          currentMap.moveCursor(Direction.UP);
        } else if (e.getCode().equals(KeyCode.DOWN)) {
          currentMap.moveCursor(Direction.DOWN);
        } else if (e.getCode().equals(KeyCode.E)) {
          currentMap.selectCurrentCursorTile();
        }
      }
    };
  }

  /**
  * calculates the width and height of the window based on current map and stores them
  */
  private void calculateWindowSize() {
    this.WIDTH_OF_WINDOW = WIDTH_OF_TILE * currentMap.getWidth();
    this.HEIGHT_OF_WINDOW = HEIGHT_OF_TILE * currentMap.getHeight();
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

  // not actually using this method anymore... consider getting rid of it unless you want to use a mouse as your input device?
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
