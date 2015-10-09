/**
* class that represents a unit in a player's army
* @author Ryan Voor
* @version 1.0
*/
import javafx.scene.image.Image;

public class Unit {

  private UnitType type;
  private AnimatedImage animatedImage;
  private int moveDistance;

  /**
  * constructor for this Unit class, takes in an image
  * @param image the image of this Unit
  */
  public Unit(UnitType type) {
    this.type = type;
    if (this.type == UnitType.SOLDIER) {
      this.animatedImage = UnitType.getSoldierAnimatedImage();
      this.moveDistance = 5;
    } else if (this.type == UnitType.DRONE) {
      this.animatedImage = UnitType.getDroneAnimatedImage();
      this.moveDistance = 3;
    }
  }

  /**
  * getter for the image field of this Unit
  * @return Image this unit's image
  */
  public AnimatedImage getImage() {
    return this.animatedImage;
  }

  /**
  * getter for the move distance of this Unit
  * @return int the move distance of this Unit
  */
  public int getMoveDistance() {
    return this.moveDistance;
  }
}
