/**
* Container class that contains the information of a tile and can be visually
* represented using javafx
* @author Ryan Voor
* @version 1.0
*/

public class TileContainer {

  private Tile tile;
  private boolean hasCursor;
  private boolean isSelected;
  private boolean isWithinCurrentMoveRadius;

  /**
  * constructor for the TileContainer class
  * @param image the image for this tile
  * @param tile the tile for this tile container
  */
  public TileContainer(Tile tile) {
    this.tile = tile;
    this.hasCursor = false;
    this.isSelected = false;
    this.isWithinCurrentMoveRadius = false;
  }

  /**
  * getter for the tile of this TileContainer
  * @return Tile the tile of this TileContainer
  */
  public Tile getTile() {
    return this.tile;
  }

  /**
  * setter for the tile of this TileContainer, returns the previous tile
  * @param tile the tile to set this TileContainer to have
  * @return Tile the previous tile of this TileContainer
  */
  public Tile setTile(Tile tile) {
    Tile oldTile = this.tile;
    this.tile = tile;
    return oldTile;
  }

  /**
  * give's this TileContainer the cursor
  * @return boolean whether this TileContainer had the cursor previously
  */
  public boolean giveCursor() {
   boolean oldHasCursor = this.hasCursor;
   this.hasCursor = true;
   return oldHasCursor;
  }

  /**
  * remove's the cursor from this TileContainer
  * @return boolean whether this TileContainer had the cursor previously
  */
  public boolean removeCursor() {
   boolean oldHasCursor = this.hasCursor;
   this.hasCursor = false;
   return oldHasCursor;
  }

  /**
  * get's whether this TileContainer has the cursor
  * @return boolean whether this TileContainer has the cursor
  */
  public boolean hasCursor() {
    return this.hasCursor;
  }

  // SHOULD I PREVENT THIS METHOD FROM WORKING IF THIS TILECONTAINER DOES NOT HAVE THE CURSOR? FOR NOW I THINK NOT


  // RIGHT NOW MULTIPLE TILES CAN BE SELECTED AT ONCE AND I CAN STILL MOVE THE CURSOR WHILE THE CURRENT TILE IS SELECTED
  // actually that's fine, I just have to handle the different cases of combination of tile selection blah blah blah in the logic that fires when a tile is selected
  /**
  * selects this TileContainer, fires events that occur when a tile is selected
  * should only be called when the cursor is on this tile
  * @return boolean whether this TileContainer was previously selected
  */
  public boolean select() {
    boolean oldIsSelected = this.isSelected;
    this.isSelected = true;
    if (/* tile is occupied*/ false) {
      // do something
    }
    // etc. with the other states of the tiles

    // when making selections of (for example) where a unit will move after it has been selected I
    // suspect that I will need to write a separate method to handle that because I will need to
    // pass in parameters etc.
    return oldIsSelected;
  }

  /**
  * removes selection from this TileContainer
  * should only be called when the cursor is on this tile
  * @return boolean whether this TileContainer was previously selected
  */
  public boolean deselect() {
    boolean oldIsSelected = this.isSelected;
    this.isSelected = false;
    return oldIsSelected;
  }

  /**
  * returns whether this TileContainer is currently selected
  * @return boolean whether this TileContainer is currently selected
  */
  public boolean isSelected() {
    return this.isSelected;
  }

  /**
  * returns whether this TileContainer is within the current move radius
  * @return boolean whether this TileContainer is within the current move radius
  */
  public boolean isWithinCurrentMoveRadius() {
    return isWithinCurrentMoveRadius;
  }

  /**
  * sets the isWithinCurrentMoveRadius field for this TileContainer
  * @param boolean whether this TileContainer is within the current move radius
  * @return boolean whether this TileContainer was within the current move radius before
  */
  public boolean setIsWithinCurrentMoveRadius(boolean isWithinCurrentMoveRadius) {
    boolean oldIsWithinCurrentMoveRadius = this.isWithinCurrentMoveRadius;
    this.isWithinCurrentMoveRadius = isWithinCurrentMoveRadius;
    return oldIsWithinCurrentMoveRadius;
  }
}
