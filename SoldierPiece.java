/** a soldier piece for xiangqi chess game 
  * @author Escanord Le */
public class SoldierPiece extends ChessPiece implements FacingKingMove
{
  /*********** FIELDS ************/
  /******** CONSTRUCTORS *********/
  /** creates a soldier piece of xiangqi chess game
    * @param side the side of the piece
    * @param row row of the piece
    * @param column column of the piece
    * @param chessBoard the chess board that the piece is on
    * @param icon the icon of the piece */
  public SoldierPiece (String side, int row, int column, ChessBoard chessBoard, Object icon)
  {
    super(side, row, column, chessBoard, "S", icon);
  }
  /***** NON-STATIC METHODS ******/
  /** returns the allowance to move the piece to [toRow, toColumn] position on the board
    * @param toRow the row of the destination
    * @param toColumn the column of the destination
    * @return true if the piece can move to the destination */
  public boolean isLegalMove(int toRow, int toColumn)
  {
    if (!isFacingKingMove(this, getRow(), getColumn(), toRow, toColumn)) 
      return false;
    if (getSide() == ChessGame.Side.NORTH)
    {
      if (getRow() <= 4) 
        return (toRow - getRow() == 1 && toColumn == getColumn());
      else 
        return (toRow - getRow() + Math.abs(toColumn - getColumn()) == 1);
    }
    if (getSide() == ChessGame.Side.SOUTH)
    {
      if (getRow() > 4) 
        return (getRow() - toRow == 1 && toColumn == getColumn());
      else 
        return (getRow() - toRow + Math.abs(toColumn - getColumn()) == 1);
    }
    if (getSide() == ChessGame.Side.WEST)
    {
      if (getColumn() <= 4) 
        return (toColumn - getColumn() == 1 && toRow == getRow());
      else 
        return (toColumn - getColumn() + Math.abs(toRow - getRow()) == 1);
    }
    if (getColumn() > 4) 
      return (getColumn() - toColumn == 1 && toRow == getRow());
    else 
      return (getColumn() - toColumn + Math.abs(toRow - getRow()) == 1);
  }
}