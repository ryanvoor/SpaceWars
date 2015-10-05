/**
* interface that when implemented will refer to a specific type of terrain for a tile
* @author Ryan Voor
* @version 1.0
*/
import javafx.scene.image.Image;

interface Terrain {

  /**
  * returns whether the given unit can occupy this terrain
  * @param unit the unit in question
  * @return Boolean whether the given unit can occupy this terrain
  */
  public Boolean canBeOccupiedBy(Unit unit);

  /**
  * calculates the defensive bonus to a given unit for this terrain, returns
  * negative numbers for penalties and positive numbers for bonuses
  * @param unit the unit in question
  * @return int the amount of defensive bonus/penalty to the given unit if it
  * occupied a tile with this terrain
  */
  public int defensiveStatsFor(Unit occupant);

  /**
  * returns whether the given unit can move over this terrain (not necessarily stop on it)
  * @param unit the unit in question
  * @return Boolean whether the given unit can traverse this terrain
  */
  public Boolean canBeTraversedBy(Unit unit);

  /**
  * calculates the defensive bonus to a given unit for this terrain, returns
  * negative numbers for penalties and positive numbers for bonuses
  * @param unit the unit in question
  * @return int the amount of movement bonus/penalty to the given unit if it
  * occupied a tile with this terrain
  */
  public int movementStatsFor(Unit unit);

  /**
  * getter for the background image of this Terrain
  * @return Image the background image of this Terrain
  */
  public Image getImage();

}
