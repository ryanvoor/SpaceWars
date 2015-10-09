/**
* class that represents the exception that is thrown when the current operation needs
* a current selection for the current Map but there is none
* @author Ryan Voor
* @version 1.0
*/
public class NoCurrentSelectionException extends Exception {

  /**
  * constructor for the NoCurrentSelectionException class
  * @param message the error message
  */
  public NoCurrentSelectionException(String message) {
    super(message);
  }
}
