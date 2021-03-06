/** a king piece for xiangqi chess game 
  * @author Escanord Le */
public class XiangqiKingPiece extends PalacePiece implements FacingKingMove
{
  /*********** FIELDS ************/
  /******** CONSTRUCTORS *********/
  /** creates a king piece of xiangqi chess game
    * @param side the side of the piece
    * @param row row of the piece
    * @param column column of the piece
    * @param chessBoard the chess board that the piece is on
    * @param icon the icon of the piece */
  public XiangqiKingPiece (String side, int row, int column, ChessBoard chessBoard, Object icon)
  {
    super(side, row, column, chessBoard, "X", icon);
  }
  /***** NON-STATIC METHODS ******/
  /** returns the allowance to move the piece to [toRow, toColumn] position on the board
    * @param toRow the row of the destination
    * @param toColumn the column of the destination
    * @return true if the piece can move to the destination without facing king piece of other side */
  private boolean isFacingKingMove(int toRow, int toColumn)
  {
    if (getSide() == ChessGame.Side.SOUTH)
    {
      for (int i = toRow - 1; i >= 0; --i)
      {
        if (getChessBoard().hasPiece(i, toColumn))
        {
          if (getChessBoard().getPiece(i, toColumn) instanceof XiangqiKingPiece) 
            return false;
          return true;
        }
      }
    }
    if (getSide() == ChessGame.Side.NORTH)
    {
      for (int i = toRow + 1; i <= 9; ++i)
      {
        if (getChessBoard().hasPiece(i, toColumn))
        {
          if (getChessBoard().getPiece(i, toColumn) instanceof XiangqiKingPiece) 
            return false;
          return true;
        }
      }
    }
    if (getSide() == ChessGame.Side.EAST)
    {
      for (int i = toColumn - 1; i >= 0; --i)
      {
        if (getChessBoard().hasPiece(toRow, i))
        {
          if (getChessBoard().getPiece(toRow, i) instanceof XiangqiKingPiece) 
            return false;
          return true;
        }
      }
    }
    if (getSide() == ChessGame.Side.WEST)
    {
      for (int i = toColumn + 1; i <= 9; ++i)
      {
        if (getChessBoard().hasPiece(toRow, i))
        {
          if (getChessBoard().getPiece(toRow, i) instanceof XiangqiKingPiece) 
            return false;
          return true;
        }
      }
    }
    
    return true;
  }
  
  /** returns the allowance to move the piece to [toRow, toColumn] position on the board
    * @param toRow the row of the destination
    * @param toColumn the column of the destination
    * @return true if the piece can move to the destination */
  public boolean isLegalMove(int toRow, int toColumn)
  {
    if (Math.abs(toRow - getRow()) + Math.abs(toColumn - getColumn()) != 1) 
      return false;
    return isLegalPalaceMove(toRow, toColumn) && isFacingKingMove(toRow, toColumn);
  }
}