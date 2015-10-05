/**
* class that represents the terrain of type Plains
* @author Ryan Voor
* @version 1.0
*/
import javafx.scene.image.Image;

public class Plains implements Terrain {

  private final Image Image = new Image(SpaceWars.PLAINS_IMAGE_LOCAL_URL, SpaceWars.getWidthOfTile(), SpaceWars.getHeightOfTile(), false, true);

  public Image getImage() {
    return this.Image;
  }

  @Override
  public Boolean canBeOccupiedBy(Unit unit) {
    // temp
    return false;
  }

  @Override
  public int defensiveStatsFor(Unit occupant) {
    // temp
    return 0;
  }

  @Override
  public Boolean canBeTraversedBy(Unit unit) {
    // temp
    return false;
  }

  @Override
  public int movementStatsFor(Unit unit) {
    // temp
    return 0;
  }

}
