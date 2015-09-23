/**
* Container class that contains the information of a tile and can be visually
* represented using javafx
* @author Ryan Voor
* @version 1.0
*/

import javafx.scene.image.Image;

public class TileContainer {

  private Tile tile;
  private AnimatedImage animatedImage;

  /**
  * constructor for the TileContainer class
  * @param image the image for this tile
  * @param tile the tile for this tile container
  */
  public TileContainer(AnimatedImage animatedImage, Tile tile) {
    this.animatedImage = animatedImage;
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

  /**
  * getter for the AnimatedImage field of this TileContainer
  * @return AnimatedImage the AnimatedImage of this TileContainer
  */
  public AnimatedImage getAnimatedImage() {
    return animatedImage;
  }

  /**
  * gets the calculated current frame of the AnimatedImage of this TileContainer
  * @return Image the currentFrame of the AnimatedImage of this TileContainer
  */
  public Image getCurrentFrame(double time) {
    return animatedImage.getCurrentFrame(time);
  }

  /**
  * sets the animatedImage of this TileContainer, returning the old one
  * @param animatedImage the new animatedImage
  * @return AnimatedImage the old animatedImage of this TileContainer
  */
  public AnimatedImage setAnimatedImage(AnimatedImage animatedImage) {
    AnimatedImage oldAnimatedImage = this.animatedImage;
    this.animatedImage = animatedImage;
    return oldAnimatedImage;
  }
}
