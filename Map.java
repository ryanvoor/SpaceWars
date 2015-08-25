/**
* Class that represents a Map on which the game will be played
* @author Ryan Voor
* @version 1.0
*/
public class Map {
  private TileContainer[][] tiles;

  /**
  todo: implement iterable for this object
  */

  /**
  * public constructor for the Map class
  * @param width, number of tiles horizontally for this map
  * @param height, number of tiles vertically for this map
  */
  public Map(int width, int height) {
    tiles = new TileContainer[width][height];
  }

  // probably need to outsource this to a static class specifically for map creation
  // lots of temporary stuff in this method
  /**
  * static method that creates test map 1
  * @return Map test map 1
  */
  public static Map getTestMap1() {
    Map testMap1 = new Map(15, 15);
    for (int i = 0; i < testMap1.getWidth(); i++) {
      for (int j = 0; j < testMap1.getHeight(); j++) {
        testMap1.setTile(i, j, new Tile(null));
      }
    }
    return testMap1;
  }

  /**
  * getter for each tile in the matrix of tiles
  * @param width the width of the tile
  * @param height the height of the tile
  */
  public TileContainer getTile(int width, int height) {
    return tiles[width][height];
  }

  /**
  * Sets the tile at the specified location in this map
  * @param width the horizontal coordinate (starts in top-left)
  * @param height the vertical coordinate (start in top-left)
  * @param tile the tile to be put at this location in this map
  * @return Tile the previous tile at this location
  */
  public Tile setTile(int width, int height, Tile tile) {
    Tile oldTile;
    if (this.tiles[width][height] != null) {
      oldTile = this.tiles[width][height].getTile();
    } else {
      oldTile = null;
    }
    this.tiles[width][height] = new TileContainer(tile);
    return oldTile;
  }

  /**
  * getter for the width of this map
  * @return int the width of this map
  */
  public int getWidth() {
    return tiles.length;
  }

  /**
  * getter for the height of this map
  * @return int the Height of this map
  */
  public int getHeight() {
    return tiles[0].length;
  }

}
