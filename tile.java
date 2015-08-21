/**
* class that represents a tile on the map
* @author Ryan Voor
* @version 1.0
*/
public class Tile {

  private Terrain terrain;

  // the unit that was there first
  private Unit primaryOccupant;
  // the unit that has moved into the tile more recently than the primaryOccupant
  private Unit secondaryOccupant;

  /**
  * Constructor that takes in a single parameter and initializes the occupant
  * field to be null by default
  * @param terrain the type of terrain for the tile
  */
  public Tile(Terrain terrain) {
    this(terrain, null);
  }

  /**
  * Constructor that takes in 2 parameters
  * @param terrain the type of terrain for the tile
  * @param occupant the type of unit occupying the tile
  */
  public Tile(Terrain terrain, Unit occupant) {
    this.terrain = terrain;
    this.occupant = occupant;
  }

  /**
  * getter for the Terrain of this tile
  * @return Terrain the Terrain of the tile
  */
  public Terrain getTerrain() {
    return terrain;
  }

  /**
  * setter for the Terrain of the tile
  * @param terrain the terrain to change the tile to
  * @return Terrain the previous Terrain of the tile
  */
  public Terrain setTerrain(Terrain terrain) {
    previousTerrain = this.terrain;
    this.terrain = terrain
    return previousTerrain;
  }

  /**
  * getter for the occupant of this tile
  * @return Unit the occupant of this tile
  */
  public Unit getOccupant() {
    return this.occupant;
  }

  /**
  * adds an occupant to this tile unless there are already two occupants
  * @param newOccupant the Unit to be added to this tile
  * @return Boolean whether the newOccupant was successfully added
  */
  public Boolean addOccupant(Unit newOccupant) {
    if primaryOccupant != null {
      this.primaryOccupant = newOccupant;
    } else if secondaryOccupant != null {
      this.secondaryOccupant = newOccupant;
    } else {
      return false;
    }
    return true;
  }

  /**
  * removes the given occupant from this tile
  * @param occupantToBeRemoved the occupant to be removed from this tile
  * @return Unit the unit that was removed
  * @throws NoOccupantException if the given unit was not occupying this tile
  */
  public Unit removeOccupant(Unit occupantToBeRemoved) throws NoOccupantException {
    if occupantToBeRemoved.equals(this.secondaryOccupant) {
      oldSecondaryOccupant = this.secondaryOccupant;
      this.secondaryOccupant = null;
      return oldSecondaryOccupant;
    } else if occupantToBeRemoved.equals(this.primaryOccupant) {
      oldPrimaryOccupant = this.primaryOccupant;
      this.primaryOccupant = null;
      return oldPrimaryOccupant;
    } else {
      throw new NoOccupantException("The specified unit was not in this tile");
    }
  }

  /**
  * returns whether this tile is contested
  * @return Boolean whether this tile is contested
  */
  public Boolean isContested() {
    return primaryOccupant != null && secondaryOccupant != null;
  }

}
