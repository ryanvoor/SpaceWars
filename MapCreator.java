/**
* Static class that contains methods for creating specific maps
* @author Ryan Voor
* @version 1.0
*/
import javafx.scene.image.Image;

public final class MapCreator {

  /**
  * static method that creates test map 1
  * @return Map test map 1
  */
  public static Map testMap1() {
    Map testMap1 = new Map(15, 15);
    for (int i = 0; i < testMap1.getWidth(); i++) {
      for (int j = 0; j < testMap1.getHeight(); j++) {
        Image[] images = new Image[2];
        images[0] = new Image(SpaceWars.PLACEHOLDER_IMAGE_LOCAL_URL_1, SpaceWars.getWidthOfTile(), SpaceWars.getHeightOfTile(), false, true);
        images[1] = new Image(SpaceWars.PLACEHOLDER_IMAGE_LOCAL_URL_2, SpaceWars.getWidthOfTile(), SpaceWars.getHeightOfTile(), false, true);
        AnimatedImage animatedImage = new AnimatedImage(images, 0.5);

        testMap1.setTile(i, j, new TileContainer(new Tile(new Plains())));
      }
    }
    // starting cursor location
    testMap1.getTile(0, 0).giveCursor();

    // add the units to the Map
    testMap1.getTile(1, 1).getTile().addOccupant(new Unit(UnitType.SOLDIER));
    testMap1.getTile(10, 8).getTile().addOccupant(new Unit(UnitType.DRONE));

    return testMap1;
  }
}
