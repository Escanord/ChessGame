/** a cannon piece for xiangqi chess game 
  * @author Escanord Le */
public class CannonPiece extends ChessPiece implements MoveStraight, FacingKingMove
{
  /*********** FIELDS ************/
  /******** CONSTRUCTORS *********/
  /** creates a cannon piece of xiangqi chess game
    * @param side the side of the piece
    * @param row row of the piece
    * @param column column of the piece
    * @param chessBoard the chess board that the piece is on
    * @param icon the icon of the piece */
  public CannonPiece (String side, int row, int column, ChessBoard chessBoard, Object icon)
  {
    super(side, row, column, chessBoard, "C", icon);
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
    if (isLegalNonCaptureMove(toRow, toColumn) && numPiece(toRow, toColumn) == 0)
      return true;
    if (isLegalCaptureMove(toRow, toColumn) && numPiece(toRow, toColumn) == 1)
      return true;
    return false;
  }
}