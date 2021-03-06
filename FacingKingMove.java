/** a overall piece for xiangqi chess game 
  * @author Escanord Le */
public interface FacingKingMove
{
  /***** NON-STATIC METHODS ******/
  /** returns the chess board of the piece
    * @return the chess board of the piece */
  ChessBoard getChessBoard();
  
  /** returns the allowance to move the piece to [toRow, toColumn] position on the board
    * @param piece the current piece that want to move
    * @param row the current row of the piece
    * @param column the current column of the piece
    * @param toRow the row of the destination
    * @param toColumn the column of the destination
    * @return true if the piece can move to the destination without making the case that king pieces face each other */
  public default boolean isFacingKingMove(ChessPiece piece, int row, int column, int toRow, int toColumn)
  {
    // stores the current piece of two continuous pieces on the current row or column
    ChessPiece currentPiece = null;
    // stores the previous piece of two continuous pieces on the current row or column
    ChessPiece previousPiece = null;
    
    if (toRow != row)
    {
      /* Goal of the loop: check if there is two continuous King Piece on the current row after moving current piece
       * Goal of each iteration: check the piece at the particular position */
      for (int i = 0; i < getChessBoard().getGameRules().getNumColumns(); ++i)
      {
        if (getChessBoard().hasPiece(row, i) && getChessBoard().getPiece(row, i) != piece)
        {
          previousPiece = currentPiece;
          currentPiece = getChessBoard().getPiece(row, i);
          if (previousPiece instanceof XiangqiKingPiece && currentPiece instanceof XiangqiKingPiece)
            return false;
        }
      }
    }
    
    currentPiece = null;
    previousPiece = null;
    if (toColumn != column)
    {
      /* Goal of the loop: check if there is two continuous King Piece on the current column after moving current piece
       * Goal of each iteration: check the piece at the particular position */
      for (int i = 0; i < getChessBoard().getGameRules().getNumRows(); ++i)
      {
        if (getChessBoard().hasPiece(i, column) && getChessBoard().getPiece(i, column) != piece)
        {
          previousPiece = currentPiece;
          currentPiece = getChessBoard().getPiece(i, column);
          if (previousPiece instanceof XiangqiKingPiece && currentPiece instanceof XiangqiKingPiece)
            return false;
        }
      }
    }
    
    return true;
  }
}