/**
* exception that is thrown if there is no occupant in a tile and someone/thing
* thought that there was
* @author Ryan Voor
* @version 1.0
*/
public class NoOccupantException extends Exception {

  /**
  * constructor for the NoOccupantException class
  * @param message the error message
  */
  public NoOccupantException(String message) {
    super(message);
  }

}
