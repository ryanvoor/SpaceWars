/**
* Container class that contains the information of a tile and can be visually
* represented using javafx
* @author Ryan Voor
* @version 1.0
*/

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class TileContainer extends ImageView {

  Tile tile;

  /**
  * constructor for the TileContainer class, takes in 2 parameters
  * @param image the image for this tile
  * @param tile the tile for this tile container
  */
  public TileContainer(Image image, Tile tile) {
    super(image);
    this.tile = tile;
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

}
