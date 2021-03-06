// @author Escanord Le (Duy Le)
// This class representing a normal chess piece that needs to keep track of the having moved status
public abstract class FirstMovePiece extends ChessPiece
{
  /*********** FIELDS ************/
  // stores the status of having moved or not
  private boolean hasMoved;
  
  /******** CONSTRUCTORS *********/
  // creates a piece with given side, row, column, and chess board
  public FirstMovePiece (String side, int row, int column, ChessBoard chessBoard, String label, Object icon)
  {
    super(side, row, column, chessBoard, label, icon);
    this.hasMoved = false;
  }
  
  /***** NON-STATIC METHODS ******/
  // sets the status of first move
  protected void setHasMoved()
  {
    this.hasMoved = true;
  }
  
  // gets the status of first move
  protected boolean getHasMoved()
  {
    return this.hasMoved;
  }
}