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


  private final static AnimatedImage defaultCurrentSelectionUnitImage = new AnimatedImage(new Image[]{
    new Image(SpaceWars.QUESTION_MARK_IMAGE_LOCAL_URL, SpaceWars.getWidthOfTile(), SpaceWars.getHeightOfTile(), false, true)
  }, 1);
  private final static Image defaultCurrentSelectionTerrainImage =
    new Image(SpaceWars.QUESTION_MARK_IMAGE_LOCAL_URL,
      SpaceWars.getWidthOfTile(),
        SpaceWars.getHeightOfTile(), false, true);
  private AnimatedImage currentSelectionUnitImage;
  private Image currentSelectionTerrainImage;
  private Tile currentSelectedTile;

  /**
  * constructor for the InfoBar class
  * sets the children to null by default
  */
  public InfoBar() {
    this.currentSelectionUnitImage = InfoBar.defaultCurrentSelectionUnitImage;
    this.currentSelectionTerrainImage = InfoBar.defaultCurrentSelectionTerrainImage;
    this.currentSelectedTile = null;
  }

  /**
  * updates the children in the InfoBar to their current values
  * should get called every frame by writeCanvas
  * @param time the current time
  */
  public void updateChildren(double time) {
    ArrayList<Node> children = new ArrayList<Node>();
    if (this.currentSelectionUnitImage != null) {
      children.add(new ImageView(this.currentSelectionUnitImage.getCurrentFrame(time)));
    }
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
  * @param tile the Tile to set the current selected tile field to be
  * @return Tile the previous currentlySelectedTile
  */
  public Tile setCurrentSelectedTile(Tile tile) {
    Tile oldCurrentSelectedTile = this.currentSelectedTile;
    // FOR NOW I AM IGNORING SECONDARY OCCUPANTS AND CONTESTING

    if (tile == null) {
      this.setCurrentSelectionUnitImage(InfoBar.defaultCurrentSelectionUnitImage);
      this.setCurrentSelectionTerrainImage(InfoBar.defaultCurrentSelectionTerrainImage);
    } else {
      if (tile.isOccupied()) {
        this.setCurrentSelectionUnitImage(tile.getPrimaryOccupant().getImage());
      }
      this.setCurrentSelectionTerrainImage(tile.getTerrain().getImage());
    }
    this.currentSelectedTile = tile;
    return oldCurrentSelectedTile;
  }

}
