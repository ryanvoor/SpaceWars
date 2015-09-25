/**
* Class that represents a Map on which the game will be played
* @author Ryan Voor
* @version 1.0
*/
// SHOULD GIVE MAPS A STARTING CURSOR LOCATION

public class Map {

  private TileContainer[][] tiles;
  // need to actually set these variables
  private int currentCursorWidth;
  private int currentCursorHeight;

  /**
  * public constructor for the Map class
  * @param width, number of tiles horizontally for this map
  * @param height, number of tiles vertically for this map
  */
  public Map(int width, int height) {
    this.tiles = new TileContainer[width][height];
    this.currentCursorWidth = 0;
    this.currentCursorHeight = 0;
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

  /**
  * moves the cursor of this map in the specified direction
  * @param direction the direction to move the cursor
  */
  public void moveCursor(Direction direction) {
    if (direction.equals(Direction.RIGHT)) {
      if (currentCursorWidth != this.getWidth() - 1) {
        this.getTile(currentCursorWidth, currentCursorHeight).removeCursor();
        this.currentCursorWidth += 1;
        this.getTile(currentCursorWidth, currentCursorHeight).giveCursor();
      }
    } else if (direction.equals(Direction.LEFT)) {
      if (currentCursorWidth != 0) {
        this.getTile(currentCursorWidth, currentCursorHeight).removeCursor();
        this.currentCursorWidth -= 1;
        this.getTile(currentCursorWidth, currentCursorHeight).giveCursor();
      }
    } else if (direction.equals(Direction.UP)) {
      if (currentCursorHeight != 0) {
        this.getTile(currentCursorWidth, currentCursorHeight).removeCursor();
        this.currentCursorHeight -= 1;
        this.getTile(currentCursorWidth, currentCursorHeight).giveCursor();
      }
    } else {
      if (currentCursorHeight != this.getHeight() - 1) {
        this.getTile(currentCursorWidth, currentCursorHeight).removeCursor();
        this.currentCursorHeight += 1;
        this.getTile(currentCursorWidth, currentCursorHeight).giveCursor();
      }
    }
  }

  /**
  * gets the TileContainer of this map that currently has the cursor
  * @return TileContainer the TileContainer of this map that currently has the cursor
  */
  public TileContainer getCurrentCursorTile() {
    return this.getTile(this.currentCursorWidth, this.currentCursorHeight);
  }

  /**
  * selects the TileContainer that currently has the cursor
  * @return boolean whether the current cursor Tile was already selected
  */
  private boolean selectCurrentCursorTile() {
    boolean currentCursorWasSelected = this.getCurrentCursorTile().isSelected();
    this.getCurrentCursorTile().select();
    return currentCursorWasSelected;
  }

  /**
  * deselects the TileContainer that currently has the cursor
  * @return boolean whether the current cursor Tile was already selected
  */
  private boolean deselectCurrentCursorTile() {
    boolean currentCursorWasSelected = this.getCurrentCursorTile().isSelected();
    this.getCurrentCursorTile().deselect();
    return currentCursorWasSelected;
  }

  /**
  * if the current cursor tile is selected, deselects it, otherwise selects it
  * @return whether the current cursor tile was selected
  */
  public boolean activateCurrentCursorTile() {
    if (this.getCurrentCursorTile().isSelected()) {
      return this.deselectCurrentCursorTile();
    } else {
      return this.selectCurrentCursorTile();
    }
  }
}
