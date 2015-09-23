/**
* class that represents the terrain of type Mountain 
* @author Ryan Voor
* @version 1.0
*/
public class Mountain implements Terrain {

  /**
  * returns whether the given unit can occupy this terrain
  * @param unit the unit in question
  * @return Boolean whether the given unit can occupy this terrain
  */
  public Boolean canBeOccupiedBy(Unit unit) {
    // temp
    return false;
  }

  /**
  * calculates the defensive bonus to a given unit for this terrain, returns
  * negative numbers for penalties and positive numbers for bonuses
  * @param unit the unit in question
  * @return int the amount of defensive bonus/penalty to the given unit if it
  * occupied a tile with this terrain
  */
  public int defensiveStatsFor(Unit occupant) {
    // temp
    return 0;
  }

  /**
  * returns whether the given unit can move over this terrain (not necessarily stop on it)
  * @param unit the unit in question
  * @return Boolean whether the given unit can traverse this terrain
  */
  public Boolean canBeTraversedBy(Unit unit) {
    // temp
    return false;
  }

  /**
  * calculates the defensive bonus to a given unit for this terrain, returns
  * negative numbers for penalties and positive numbers for bonuses
  * @param unit the unit in question
  * @return int the amount of movement bonus/penalty to the given unit if it
  * occupied a tile with this terrain
  */
  public int movementStatsFor(Unit unit) {
    // temp
    return 0;
  }

}
