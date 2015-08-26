/**
* Static class that contains methods for creating specific maps
* @author Ryan Voor
* @version 1.0
*/
public final class MapCreator {

  /**
  * static method that creates test map 1
  * @return Map test map 1
  */
  public static Map testMap1() {
    Map testMap1 = new Map(15, 15);
    for (int i = 0; i < testMap1.getWidth(); i++) {
      for (int j = 0; j < testMap1.getHeight(); j++) {
        testMap1.setTile(i, j, new Tile(null));
      }
    }
    return testMap1;
  }
}
