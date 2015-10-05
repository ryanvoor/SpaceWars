/**
* enum that holds the different types of Units
* @author Ryan Voor
* @version 1.0
*/
import javafx.scene.image.Image;

public enum UnitType {
  SOLDIER, DRONE;

  /**
  * gets the AnimatedImage for a soldier unit
  * @return AnimatedImage the image of a soldier
  */
  public static AnimatedImage getSoldierAnimatedImage() {
    return new AnimatedImage(
      new Image[]{
        new Image(SpaceWars.PLACEHOLDER_IMAGE_LOCAL_URL_1, SpaceWars.getWidthOfTile() / 2, SpaceWars.getHeightOfTile() / 2, false, true),
        new Image(SpaceWars.PLACEHOLDER_IMAGE_LOCAL_URL_2, SpaceWars.getWidthOfTile() / 2, SpaceWars.getHeightOfTile() / 2, false, true)
      }, 0.5
    );
  }

  /**
  * gets the AnimatedImage for a drone unit
  * @return AnimatedImage the image of a drone
  */
  public static AnimatedImage getDroneAnimatedImage() {
    return new AnimatedImage(
      new Image[]{
        new Image(SpaceWars.PLACEHOLDER_IMAGE_LOCAL_URL_4, SpaceWars.getWidthOfTile() / 2, SpaceWars.getHeightOfTile() / 2, false, true),
        new Image(SpaceWars.PLACEHOLDER_IMAGE_LOCAL_URL_5, SpaceWars.getWidthOfTile() / 2, SpaceWars.getHeightOfTile() / 2, false, true)
      }, 0.5
    );
  }
}
