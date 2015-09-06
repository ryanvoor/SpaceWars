/**
* Class that represents a Map on which the game will be played
* @author Ryan Voor
* @version 1.0
*/
public class Map {
  private TileContainer[][] tiles;

  /**
  * public constructor for the Map class
  * @param width, number of tiles horizontally for this map
  * @param height, number of tiles vertically for this map
  */
  public Map(int width, int height) {
    tiles = new TileContainer[width][height];
  }

  /**
  * getter for the matrix of TileContainers that represents this Map
  * @return TileContainer[][] the matrix of tiles that represents this Map
  */
  public TileContainer[][] getTiles() {
    return tiles;
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
  * @return TileContainer the previous tile at this location
  */
  public TileContainer setTile(int width, int height, TileContainer tile) {
    TileContainer oldTile;
    if (this.tiles[width][height] != null) {
      oldTile = this.tiles[width][height];
    } else {
      oldTile = null;
    }
    this.tiles[width][height] = tile;
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
