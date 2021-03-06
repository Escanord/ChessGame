// @author Escanord Le
// This class representing a normal bishop piece
public class BishopPiece extends ChessPiece implements MoveDiagonally
{
  /*********** FIELDS ************/
  
  /******** CONSTRUCTORS *********/
  // creates a bishop piece
  public BishopPiece (String side, int row, int column, ChessBoard chessBoard, Object icon)
  {
    super(side, row, column, chessBoard, "B", icon);
  }
  
  /***** NON-STATIC METHODS ******/
  // returns if moving to [toRow, toColumn] is legal or not
  @Override
  public boolean isLegalMove (int toRow, int toColumn)
  {
    if (Math.abs(this.getRow() - toRow) == Math.abs(this.getColumn() - toColumn)) return isLegalDiagonalMove(toRow, toColumn);
    return false;
  }
  
  /****** STATIC METHODS *********/
}