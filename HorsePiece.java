/** a horse piece for xiangqi chess game 
  * @author Escanord Le */
public class HorsePiece extends KnightPiece implements FacingKingMove
{
  /*********** FIELDS ************/
  /******** CONSTRUCTORS *********/
  /** creates a horse piece of xiangqi chess game
    * @param side the side of the piece
    * @param row row of the piece
    * @param column column of the piece
    * @param chessBoard the chess board that the piece is on
    * @param icon the icon of the piece */
  public HorsePiece (String side, int row, int column, ChessBoard chessBoard, Object icon)
  {
    super(side, row, column, chessBoard, "H", icon);
  }
  /***** NON-STATIC METHODS ******/
  /** returns the allowance to move the piece to [toRow, toColumn] position on the board
    * @param toRow the row of the destination
    * @param toColumn the column of the destination
    * @return true if the piece can move to the destination */
  @Override
  public boolean isLegalMove (int toRow, int toColumn)
  {
    if (!isFacingKingMove(this, getRow(), getColumn(), toRow, toColumn)) 
      return false;
    if (!super.isLegalMove(toRow, toColumn))
      return false;
    if (Math.abs(toColumn - getColumn()) == 1)
    {
      // stores the middle row between current row and destination's row
      int middleRow = (toRow + getRow()) / 2;
      
      return (!getChessBoard().hasPiece(middleRow, getColumn()));
    }
    else
    {
      // stores the middle column between current column and destination's column
      int middleColumn = (toColumn + getColumn()) / 2;
      
      return (!getChessBoard().hasPiece(getRow(), middleColumn));
    }
  }
}