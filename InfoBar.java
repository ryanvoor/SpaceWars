/**
* class that represents the info bar that is displayed beside the map
* will show information about the current tile selection etc.
* @author Ryan Voor
* @version 1.0
*/
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class InfoBar extends VBox {

  AnimatedImage currentSelectionUnitImage;
  Image currentSelectionTerrainImage;
  Tile currentlySelectedTile;

  /**
  * constructor for the InfoBar class
  * sets the children to null by default
  */
  public InfoBar() {
    this.currentSelectionUnitImage = null;
    this.currentSelectionTerrainImage = null;
    this.currentlySelectedTile = null;
  }

  /**
  * updates the children in the InfoBar to their current values
  */
  public void updateChildren(double time) {
    ArrayList<Node> children = new ArrayList<Node>();
    children.add(new ImageView(this.currentSelectionUnitImage.getCurrentFrame(time)));
    children.add((new ImageView(this.currentSelectionTerrainImage)));
    this.getChildren().setAll(children);
  }

  /**
  * setter for the currentSelectionUnitImage field
  * @param newImage the image to set the currentSelectionUnitImage field to be
  * @return AnimatedImage the previous currentSelectionUnitImage
  */
  public AnimatedImage setCurrentSelectionUnitImage(AnimatedImage newImage) {
    AnimatedImage oldCurrentSelectionUnitImage = this.currentSelectionUnitImage;
    this.currentSelectionUnitImage = newImage;
    return oldCurrentSelectionUnitImage;
  }

  /**
  * setter for the currentSelectionTerrainImage field
  * @param newImage the image to set the currentSelectionTerrainImage field to be
  * @return Image
  */
  public Image setCurrentSelectionTerrainImage(Image newImage) {
    Image oldCurrentSelectionTerrainImage = this.currentSelectionTerrainImage;
    this.currentSelectionTerrainImage = newImage;
    return oldCurrentSelectionTerrainImage;
  }

  /**
  * @param tile the Tile to set the
  * @return Tile the previous currentlySelectedTile
  */
  public Tile setCurrentlySelectedTile(Tile tile) {
    Tile oldCurrentlySelectedTile = this.currentlySelectedTile;
    // FOR NOW I AM IGNORING SECONDARY OCCUPANTS AND CONTESTING
    this.setCurrentSelectionUnitImage(tile.getPrimaryOccupant().getImage());
    this.setCurrentSelectionTerrainImage(tile.getTerrain().getImage());
    this.currentlySelectedTile = tile;
    return oldCurrentlySelectedTile;
  }

}
