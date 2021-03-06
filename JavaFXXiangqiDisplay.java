/** The display of the Xiangqi chess board using JavaFX
  * @author Escanord Le */
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;

public class JavaFXXiangqiDisplay implements JavaFXChessBoardDisplay
{
  /*********** FIELDS ************/
  // stores the first color of the board
  private static Color firstBoardColor = Color.LIGHTGREY;
  // stores the second color of the board
  private static Color secondBoardColor = Color.DARKGREY;
  // stores the color of the first player's piece
  private static Color firstPlayerColor = Color.SEASHELL;
  // stores the color of the second player's piece
  private static Color secondPlayerColor = Color.GOLD;
  // stores the highlighted color
  private static Color highlightColor = Color.IVORY;
  // stores the first player of the game
  private ChessGame.Side firstPlayer;
  
  /******** CONSTRUCTORS *********/
  public JavaFXXiangqiDisplay (ChessGame.Side firstPlayer)
  {
    this.firstPlayer = firstPlayer;
  }
  /***** NON-STATIC METHODS ******/
  /* gets the first player of the game
   * @return the first player of the game */
  protected ChessGame.Side getFirstPlayer()
  {
    return this.firstPlayer;
  }
  /** how the empty square is displayed
    * @param button the button representing that square
    * @param row the row of that square
    * @param column the column of that square */
  public void displayEmptySquare (Button button, int row, int column)
  {
    // stores the fill of the square
    BackgroundFill fill;
    
    if (getFirstPlayer() == ChessGame.Side.NORTH || getFirstPlayer() == ChessGame.Side.SOUTH)
      fill = new BackgroundFill(column >= 3 && column <= 5 && (row <= 2 || row >= 7) ? secondBoardColor : firstBoardColor, CornerRadii.EMPTY, new Insets(2));
    else fill = new BackgroundFill(row >= 3 && row <= 5 && (column <= 2 || column >= 7) ? secondBoardColor : firstBoardColor, CornerRadii.EMPTY, new Insets(2));
    button.setBackground(new Background(fill));
    button.setText(null);
  }
  
  /** how the square with a particular piece on it is displayed
    * @param button the button representing that square
    * @param row the row of that square
    * @param column the column of that square 
    * @param piece the piece on that square */
  public void displayFilledSquare (Button button, int row, int column, ChessPiece piece)
  {
    BackgroundFill fill = new BackgroundFill(piece.getSide() == ChessGame.Side.NORTH || piece.getSide() == ChessGame.Side.WEST ? firstPlayerColor : secondPlayerColor, new CornerRadii(50, true), new Insets(5));
    button.setBackground(new Background(button.getBackground().getFills().get(0), fill));
    button.setText(piece.getLabel());
  }
  
  /** how the square with a particular piece on it is highlighted
    * @param highlight the status of being highlighted or not
    * @param button the button representing that square
    * @param row the row of that square
    * @param column the column of that square 
    * @param piece the piece on that square */
  public void highlightSquare (boolean highlight, Button button, int row, int column, ChessPiece piece)
  {
    if (highlight)
    {
      BackgroundFill fill = new BackgroundFill(highlightColor, CornerRadii.EMPTY, new Insets(2));
      button.setBackground(new Background(fill));
    }
    else
      displayEmptySquare(button, row, column);
    if (piece != null) displayFilledSquare(button, row, column, piece);
  }
}
