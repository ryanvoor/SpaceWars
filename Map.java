/**
* Class that represents a Map on which the game will be played
* @author Ryan Voor
* @version 1.0
*/
// SHOULD GIVE MAPS A STARTING CURSOR LOCATION THAT ISN'T JUST (0,0) FOR EVERY MAP

public class Map {

  private TileContainer[][] tiles;
  private int currentCursorWidth;
  private int currentCursorHeight;
  private Integer currentSelectionWidth;
  private Integer currentSelectionHeight;

  /**
  * public constructor for the Map class
  * @param width, number of tiles horizontally for this map
  * @param height, number of tiles vertically for this map
  */
  public Map(int width, int height) {
    this.tiles = new TileContainer[width][height];
    this.currentCursorWidth = 0;
    this.currentCursorHeight = 0;
    this.currentSelectionWidth = null;
    this.currentSelectionHeight = null;
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

  // CURRENT ISSUE, YOU CAN MOVE THE CURSOR OUTSIDE THE MOVE RADIUS WHEN YOU HAVE A UNIT SELECTED, ALSO, THE MOVE RADIUS IS WRONG
  // ALSO THERE IS SOMETHING WEIRD HAPPENNING WHEN I SELECT THE DRONE UNIT AS OPPOSED TO THE SOLDIER UNIT...
  /**
  * moves the cursor of this map in the specified direction
  * @param direction the direction to move the cursor
  */
  public void moveCursor(Direction direction) {
    if (!this.getCurrentCursorTile().isSelected()) {
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
    } else if (this.getCurrentCursorTile().getTile().isOccupied()) {
      TileContainer tileToBeMovedTo;
      if (direction.equals(Direction.RIGHT)) {
        if (currentCursorWidth != this.getWidth() - 1) {
          tileToBeMovedTo = this.getTile(currentCursorWidth + 1, currentCursorHeight);
          if (tileToBeMovedTo.isWithinCurrentMoveRadius()) {
            this.getCurrentCursorTile().removeCursor();
            this.currentCursorWidth += 1;
            this.getCurrentCursorTile().giveCursor();
          }
        }
      } else if (direction.equals(Direction.LEFT)) {
        if (currentCursorWidth != 0) {
          tileToBeMovedTo = this.getTile(currentCursorWidth - 1, currentCursorHeight);
          if (tileToBeMovedTo.isWithinCurrentMoveRadius()) {
            this.getCurrentCursorTile().removeCursor();
            this.currentCursorWidth -= 1;
            this.getCurrentCursorTile().giveCursor();
          }
        }
      } else if (direction.equals(Direction.UP)) {
        if (currentCursorHeight != 0) {
          tileToBeMovedTo = this.getTile(currentCursorWidth, currentCursorHeight - 1);
          if (tileToBeMovedTo.isWithinCurrentMoveRadius()) {
            this.getCurrentCursorTile().removeCursor();
            this.currentCursorHeight -= 1;
            this.getCurrentCursorTile().giveCursor();
          }
        }
      } else {
        if (currentCursorHeight != this.getHeight() - 1) {
          tileToBeMovedTo = this.getTile(currentCursorWidth, currentCursorHeight + 1);
          if (tileToBeMovedTo.isWithinCurrentMoveRadius()) {
            this.getCurrentCursorTile().removeCursor();
            this.currentCursorHeight += 1;
            this.getCurrentCursorTile().giveCursor();
          }
        }
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
    this.currentSelectionWidth = this.currentCursorWidth;
    this.currentSelectionHeight = this.currentCursorHeight;
    if (this.getCurrentCursorTile().getTile().isOccupied()) {
      try {
       this.calculateCurrentMoveRadius();
      } catch (NoCurrentSelectionException e) {
        System.out.println(e);
      }
    }
    return currentCursorWasSelected;
  }

  /**
  * deselects the TileContainer that currently has the cursor
  * @return boolean whether the current cursor Tile was already selected
  */
  private boolean deselectCurrentCursorTile() {
    boolean currentCursorWasSelected = this.getCurrentCursorTile().isSelected();
    if (this.getCurrentCursorTile().getTile().isOccupied()) {
      this.clearCurrentMoveRadius();
    }
    this.getCurrentCursorTile().deselect();
    this.currentSelectionWidth = null;
    this.currentSelectionHeight = null;
    return currentCursorWasSelected;
  }

  /**
  * if the current cursor tile is selected, deselects it, if this Map does not already have a selected Tile, selects it
  * @return whether the current cursor tile was selected
  */
  public boolean activateCurrentCursorTile() {
    if (this.getCurrentCursorTile().isSelected()) {
      return this.deselectCurrentCursorTile();
    } else if (this.hasASelection()) {
      return false;
    } else {
      return this.selectCurrentCursorTile();
    }
  }

  /**
  * calculates (using the currentSelectionWidth field) whether this Map has a currently selected Tile
  * @return boolean whether a Tile on this Map is selected
  */
  public boolean hasASelection() {
    return currentSelectionWidth != null;
  }

  /**
  * gets the currently selected Tile for this Map, returns null if there is no currently selected Tile
  * @return TileContainer the currently selected TileContainer, null if there is no selected Tile
  */
  public TileContainer getCurrentSelectedTile() {
    if (this.hasASelection()) {
      return this.getTile(this.currentSelectionWidth, this.currentSelectionHeight);
    } else {
      return null;
    }
  }

  /**
  * calculates the current move radius based on the currently selected tile,
  * the unit occupying that tile, and the surrounding terrain
  */
  private void calculateCurrentMoveRadius() throws NoCurrentSelectionException{
    // FOR NOW THIS IS NOT TAKING TERRAIN INTO ACCOUNT
    // ALSO IGNORING SECONDARY OCCUPANTS
    if (!this.hasASelection()) {
      throw new NoCurrentSelectionException("calculateCurrentMoveRadius() was called when the Map did not have a current selection");
    }
    int moveDistance = this.getCurrentSelectedTile().getTile().getPrimaryOccupant().getMoveDistance();
    for (int i = 0; i < this.getWidth(); i++) {
      for (int j = 0; j < this.getHeight(); j++) {
        if (this.calculateDistanceBetween(i, j, this.currentSelectionWidth, this.currentSelectionHeight) <= moveDistance) {
          this.getTile(i, j).setIsWithinCurrentMoveRadius(true);
        }
      }
    }
  }

  /**
  * clears the current move radius
  */
  private void clearCurrentMoveRadius() {
    for (int i = 0; i < this.getWidth(); i++) {
      for (int j = 0; j < this.getHeight(); j++) {
        this.getTile(i, j).setIsWithinCurrentMoveRadius(false);
      }
    }
  }

  /**
  * calculates the distance between two tiles given those two tiles' x and y positions in the Map
  * @param firstX the first tile's X position
  * @param firstY the first Tile's Y position
  * @param secondX the second tile's X position
  * @param secondY the second tile's Y position
  * @return int the distance between the two Tiles in this Map
  */
  private int calculateDistanceBetween(int firstX, int firstY, int secondX, int secondY) {
    return Math.abs(firstX - secondX) + Math.abs(firstY - secondY);
  }
}
