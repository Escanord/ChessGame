/** an elepphant piece for xiangqi chess game 
  * @author Escanord Le */
public class ElephantPiece extends ChessPiece implements MoveDiagonally, FacingKingMove
{
  /*********** FIELDS ************/
  /******** CONSTRUCTORS *********/
  /** creates an elephant piece of xiangqi chess game
    * @param side the side of the piece
    * @param row row of the piece
    * @param column column of the piece
    * @param chessBoard the chess board that the piece is on
    * @param icon the icon of the piece */
  public ElephantPiece (String side, int row, int column, ChessBoard chessBoard, Object icon)
  {
    super(side, row, column, chessBoard, "E", icon);
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
    if (Math.abs(toRow - getRow()) != 2 || Math.abs(toColumn - getColumn()) != 2) 
      return false;
    if ((getSide() == ChessGame.Side.NORTH && toRow > 4) || (getSide() == ChessGame.Side.SOUTH && toRow <= 4))
      return false;
    if ((getSide() == ChessGame.Side.WEST && toColumn > 4) || (getSide() == ChessGame.Side.EAST && toColumn <= 4))
      return false;
    return isLegalDiagonalMove(toRow, toColumn);
  }
}