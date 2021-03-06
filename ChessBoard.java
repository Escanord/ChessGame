/** the chess board for the game
  * @author Escanord Le */
public interface ChessBoard
{ 
  /***** NON-STATIC METHODS ******/
  /** returns the game rule being played on this board
    * @return the ChessGame being played on this board */
  ChessGame getGameRules();
  
  /** adds a chess piece to a given row and column on the board
    * @param piece the chess piece to be added
    * @param row the row to add the piece to
    * @param column the column to add the piece to */
  void addPiece (ChessPiece piece, int row, int column);
  
  /** removes the chess piece at the given row and column and returns it
    * @param row the given row of the piece to be removed
    * @param column the given column of the piece to be removed */
  ChessPiece removePiece (int row, int column);
  
  /** returns if there is any piece at the given row and column
    * @param row the given row to be checked
    * @param column the given column to be checked
    * @return true if there is any piece at the given row and column, false otherwise*/
  boolean hasPiece (int row, int column);
  
  /** returns the piece at the given row and column
    * @param row the given row
    * @param column the given column */
  ChessPiece getPiece (int row, int column);
  
  /** returns if there is any opponent's piece threatening current row and column
    * @param row the given row to be checked
    * @param column the given column to be checked
    * @param piece the given piece of current player
    * @return true if there is any opponent's piece can make a legal capture move to a given row and column */
  public default boolean squareThreatened (int row, int column, ChessPiece piece)
  {
    for (int i = 0; i < getGameRules().getNumRows(); i++) 
    {
      for (int j = 0; j < getGameRules().getNumColumns(); j++) 
      {
        if (hasPiece(i,j) && getPiece(i, j).getSide() != piece.getSide() && getPiece(i, j).isLegalMove(row, column))
          return true;
      }
    }
    
    return false;
  }
  /****** STATIC METHODS *********/
}