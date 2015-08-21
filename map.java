/**
* Class that represents a Map on which the game will be played
* @author Ryan Voor
* @version 1.0
*/
public class Map {
  private Tile[][] tiles;

  /**
  * public constructor for the Map class
  * @param height, number of tiles vertically for this map
  * @param width, number of tiles horizontally for this map
  */
  public map(int height, int width) {
    tiles = new Tile[height][width];
  }

  /**
  * getter for each tile in the matrix of tiles
  * @param height the height of the tile
  * @param width the width of the tile
  */
  public Tile getTile(int height, int width) {
    return tiles[height][width];
  }

}
