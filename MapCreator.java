/**
* Static class that contains methods for creating specific maps
* @author Ryan Voor
* @version 1.0
*/
import javafx.scene.image.Image;

public final class MapCreator {

  private final static int WIDTH_OF_TILE = 100;
  private final static int HEIGHT_OF_TILE = 100;
  private final static String PLACEHOLDER_IMAGE_LOCAL_URL_1 = "/images/advance_wars_mech.png";
  private final static String PLACEHOLDER_IMAGE_LOCAL_URL_2 = "/images/advance_wars_variety_of_units.jpg";
  private final static String PLACEHOLDER_TEST_IMAGE_LOCAL_URL = "/images/trump_kissing.jpg";

  /**
  * static method that creates test map 1
  * @return Map test map 1
  */
  public static Map testMap1() {
    Map testMap1 = new Map(15, 15);
    for (int i = 0; i < testMap1.getWidth(); i++) {
      for (int j = 0; j < testMap1.getHeight(); j++) {
        Image[] images = new Image[2];
        images[0] = new Image(PLACEHOLDER_IMAGE_LOCAL_URL_1, WIDTH_OF_TILE, HEIGHT_OF_TILE, false, true);
        images[1] = new Image(PLACEHOLDER_IMAGE_LOCAL_URL_2, WIDTH_OF_TILE, HEIGHT_OF_TILE, false, true);
        AnimatedImage animatedImage = new AnimatedImage(images, 0.5);

        testMap1.setTile(i, j, new TileContainer(animatedImage, new Tile(null)));
      }
    }
    return testMap1;
  }
}
