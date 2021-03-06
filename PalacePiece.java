/** Xiangqi piece that can only move in middle three columns and three top (or bottom) rows of the board
  * @author Escanord Le */
public abstract class PalacePiece extends ChessPiece implements FacingKingMove
{
  /*********** FIELDS ************/
  /******** CONSTRUCTORS *********/
  /* creates a palace piece
   * @param side side of the piece
   * @param row row of the piece
   * @param column column of the piece
   * @param chessBoard the chess board that the piece is on
   * @param label the label of the piece
   * @param icon the icon of the piece */
  public PalacePiece (String side, int row, int column, ChessBoard chessBoard, String label, Object icon)
  {
    super(side, row, column, chessBoard, label, icon);
  }
  /***** NON-STATIC METHODS ******/
  /** check if it is a legal move in a restricted area of the piece
    * @param toRow the row the piece is moved to
    * @param toColumn the column the piece is moved to
    * @return true if the piece can legally move */
  protected boolean isLegalPalaceMove (int toRow, int toColumn)
  {
    if ((getSide() == ChessGame.Side.NORTH || getSide() == ChessGame.Side.SOUTH) && toColumn >= 3 && toColumn <= 5)
    {
      if (getSide() == ChessGame.Side.NORTH) 
        return toRow <= 2 && (isLegalCaptureMove(toRow, toColumn) || isLegalNonCaptureMove(toRow, toColumn));
      return toRow >= 7 && (isLegalCaptureMove(toRow, toColumn) || isLegalNonCaptureMove(toRow, toColumn));
    }
    else if ((getSide() == ChessGame.Side.WEST || getSide() == ChessGame.Side.EAST) && toRow >= 3 && toRow <= 5)
    {
      if (getSide() == ChessGame.Side.WEST) 
        return toColumn <= 2 && (isLegalCaptureMove(toRow, toColumn) || isLegalNonCaptureMove(toRow, toColumn));
      return toColumn >=  7 && (isLegalCaptureMove(toRow, toColumn) || isLegalNonCaptureMove(toRow, toColumn));
    }
    
    return false;
  }
}