/**
* class that represents the terrain of type Mountain
* @author Ryan Voor
* @version 1.0
*/
import javafx.scene.image.Image;

public class Mountain implements Terrain {

  // USING THE IMAGE FOR PLAINS RATHER THAN IT'S OWN MOUNTAIN IMAGE FOR THE TIME BEING
  private final Image Image = new Image(SpaceWars.PLAINS_IMAGE_LOCAL_URL, SpaceWars.getWidthOfTile() / 2, SpaceWars.getHeightOfTile() / 2, false, true);

  @Override
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
