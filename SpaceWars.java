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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SpaceWars extends Application {

  private String TITLE = "Space Wars";
  private final static int WIDTH_OF_TILE = 50;
  private final static int HEIGHT_OF_TILE = 50;
  private int WIDTH_OF_WINDOW;
  private int HEIGHT_OF_WINDOW;
  private final String PLACEHOLDER_IMAGE_LOCAL_URL_1 = "/images/advance_wars_mech.png";
  private final String PLACEHOLDER_IMAGE_LOCAL_URL_2 = "/images/advance_wars_variety_of_units.jpg";
  private final String PLACEHOLDER_IMAGE_LOCAL_URL_3 = "/images/trump_kissing.jpg";
  private final String PLACEHOLDER_IMAGE_LOCAL_URL_4 = "/images/obama_posing.jpg";
  private final String PLAINS_IMAGE_LOCAL_URL = "/images/plains.jpg";
  private Map currentMap;
  private Canvas canvas;
  private GraphicsContext gc;
  private VBox infoBar;

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

    // BEFORE I CAN MAKE THIS ADDITION TO THE GUI ACTUALLY DO ANYTHING I NEED TO MAKE IT SO I CAN ONLY SELECT ONE TILE AT A TIME
    HBox mapAndInfoBarContainer = new HBox();
    infoBar = new VBox();
    infoBar.getChildren().add(new Text("EXAMPLE TEXT"));
    mapAndInfoBarContainer.getChildren().addAll(canvas, infoBar);
    container.getChildren().add(mapAndInfoBarContainer);
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

        // NEW CURRENT TASK: MAKE SELECTING STUFF CHANGE THE BEHAVIOR OF THE CURSOR/DO DIFFERENT THINGS IF THEY SELECT A TILE THAT'S OCCUPIED V UNOCCUPIED ETC.
        // as a temporary measure i am going to make it so that way i cannot select a new tile when i already have a selection

        // SHOULD ALTER THE GUI TO HAVE A SIDEBAR OR SOMETHING LIKE THAT TO DISPLAY INFORMATION ABOUT THE CURRENT SELECTION/GENERAL INFORMATION IF NOTHING IS SELECTED

        // will need to figure out what these will look like with Ryan

        // draw the background
        gc.drawImage(tileContainer.getTerrainImage(), currentPixelWidth, currentPixelHeight);

        // draw the units
        if (tileContainer.getTile().isOccupied()) {
          gc.drawImage(tileContainer.getTile().getPrimaryOccupantImage().getCurrentFrame(time), currentPixelWidth, currentPixelHeight);
        }

        // draw the cursor
        if (tileContainer.hasCursor()) {
          gc.drawImage(new Image(PLACEHOLDER_IMAGE_LOCAL_URL_3, SpaceWars.getWidthOfTile(), SpaceWars.getHeightOfTile(), false, true), currentPixelWidth, currentPixelHeight);
        }

        // draw the selection
        if (tileContainer.isSelected()) {
          gc.setFill(new Color(0, 0, 1.0, 0.5));
          gc.fillRect(currentPixelWidth, currentPixelHeight, SpaceWars.getWidthOfTile(), SpaceWars.getHeightOfTile());
        }

        currentPixelHeight += SpaceWars.getHeightOfTile();
      }
    currentPixelWidth += SpaceWars.getWidthOfTile();
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
        if (e.getCode().equals(KeyCode.RIGHT)) {
          currentMap.moveCursor(Direction.RIGHT);
        } else if (e.getCode().equals(KeyCode.LEFT)) {
          currentMap.moveCursor(Direction.LEFT);
        } else if (e.getCode().equals(KeyCode.UP)) {
          currentMap.moveCursor(Direction.UP);
        } else if (e.getCode().equals(KeyCode.DOWN)) {
          currentMap.moveCursor(Direction.DOWN);
        } else if (e.getCode().equals(KeyCode.E)) {
          currentMap.activateCurrentCursorTile();
        }
      }
    };
  }

  /**
  * getter for the width of a tile
  * @return int the width of a tile
  */
  public static int getWidthOfTile() {
    return WIDTH_OF_TILE;
  }

  /**
  * getter for the height of a tile
  * @return int the height of a tile
  */
  public static int getHeightOfTile() {
    return HEIGHT_OF_TILE;
  }

  /**
  * calculates the width and height of the window based on current map and stores them
  */
  private void calculateWindowSize() {
    this.WIDTH_OF_WINDOW = SpaceWars.getWidthOfTile() * currentMap.getWidth();
    this.HEIGHT_OF_WINDOW = SpaceWars.getHeightOfTile() * currentMap.getHeight();
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
    int column = width / SpaceWars.getWidthOfTile();
    int row = height / SpaceWars.getHeightOfTile();
    return map.getTile(column, row);
  }
}
