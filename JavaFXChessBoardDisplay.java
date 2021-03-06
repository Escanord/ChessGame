/** The display of the chess board using JavaFX
  * @author Escanord Le */
import javafx.scene.control.Button;

public interface JavaFXChessBoardDisplay
{
  /** how the empty square is displayed
    * @param button the button representing that square
    * @param row the row of that square
    * @param column the column of that square */
  void displayEmptySquare (Button button, int row, int column);
  
  /** how the square with a particular piece on it is displayed
    * @param button the button representing that square
    * @param row the row of that square
    * @param column the column of that square 
    * @param piece the piece on that square */
  void displayFilledSquare (Button button, int row, int column, ChessPiece piece);
  
  /** how the square with a particular piece on it is highlighted
    * @param highlight the status of being highlighted or not
    * @param button the button representing that square
    * @param row the row of that square
    * @param column the column of that square 
    * @param piece the piece on that square */
  void highlightSquare (boolean highlight, Button button, int row, int column, ChessPiece piece);
}
