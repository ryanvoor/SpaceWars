/**
* class that represents a unit in a player's army
* @author Ryan Voor
* @version 1.0
*/
import javafx.scene.image.Image;

public class Unit {

  private AnimatedImage animatedImage;

  /**
  * constructor for this Unit class, takes in an image
  * @param image the image of this Unit
  */
  public Unit(UnitType type) {
    if (type == UnitType.SOLDIER) {
      this.animatedImage = UnitType.getSoldierAnimatedImage();
    } else if (type == UnitType.DRONE) {
      this.animatedImage = UnitType.getDroneAnimatedImage();
    }
  }

  /**
  * getter for the image field of this Unit
  * @return Image this unit's image
  */
  public AnimatedImage getImage() {
    return this.animatedImage;
  }
}
