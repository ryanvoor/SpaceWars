/**
* Static class that contains methods for creating specific maps
* @author Ryan Voor
* @version 1.0
*/
import javafx.scene.image.Image;

public final class MapCreator {

  private final static String PLACEHOLDER_IMAGE_LOCAL_URL_1 = "/images/advance_wars_mech.png";
  private final static String PLACEHOLDER_IMAGE_LOCAL_URL_2 = "/images/advance_wars_variety_of_units.jpg";
  private final static String PLACEHOLDER_IMAGE_LOCAL_URL_3 = "/images/trump_kissing.jpg";
  private final static String PLACEHOLDER_IMAGE_LOCAL_URL_4 = "/images/obama_posing.jpg";
  private final static String PLACEHOLDER_IMAGE_LOCAL_URL_5 = "/images/spellstutter_sprite.jpg";
  private final static String PLAINS_IMAGE_LOCAL_URL = "/images/plains.jpg";

  /**
  * static method that creates test map 1
  * @return Map test map 1
  */
  public static Map testMap1() {
    Map testMap1 = new Map(15, 15);
    for (int i = 0; i < testMap1.getWidth(); i++) {
      for (int j = 0; j < testMap1.getHeight(); j++) {
        Image[] images = new Image[2];
        images[0] = new Image(PLACEHOLDER_IMAGE_LOCAL_URL_1, SpaceWars.getWidthOfTile(), SpaceWars.getHeightOfTile(), false, true);
        images[1] = new Image(PLACEHOLDER_IMAGE_LOCAL_URL_2, SpaceWars.getWidthOfTile(), SpaceWars.getHeightOfTile(), false, true);
        AnimatedImage animatedImage = new AnimatedImage(images, 0.5);

        testMap1.setTile(i, j, new TileContainer(animatedImage, new Tile(null), PLAINS_IMAGE_LOCAL_URL));
      }
    }
    // starting cursor location
    testMap1.getTile(0, 0).giveCursor();
    Image[] frames = new Image[]{
      new Image(PLACEHOLDER_IMAGE_LOCAL_URL_1, SpaceWars.getWidthOfTile() / 2, SpaceWars.getHeightOfTile() / 2, false, true),
        new Image(PLACEHOLDER_IMAGE_LOCAL_URL_2, SpaceWars.getWidthOfTile() / 2, SpaceWars.getHeightOfTile() / 2, false, true)};
    testMap1.getTile(1, 1).getTile().addOccupant(new Unit(new AnimatedImage(frames, 0.5)));
    return testMap1;
  }
  /**
    need to write a helper method for this class that gets the image for the TileContainer based on what it's terrain is.
  */
}
