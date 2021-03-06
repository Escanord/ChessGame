// @author Escanord Le (Duy Le)
// This interface representing a normal chess piece that moves straight
public interface MoveStraight
{
  /***** NON-STATIC METHODS ******/
  // returns the chess board the piece is on
  ChessBoard getChessBoard();
  
  // returns the row the piece is on
  int getRow();
  
  // returns the column the piece is on
  int getColumn();
  
  // retusn the side of the piece
  ChessGame.Side getSide();
  
  // returns the allowance to move the piece to [row, column] position having no peice on the board
  public boolean isLegalNonCaptureMove(int row, int column);
  
  // returns the allowance to move the piece to [row, column] position having a peice on the board
  public boolean isLegalCaptureMove(int row, int column);
  
  /** returns the number of piece on the moving path
    * @param row the row of the destination
    * @param column the column of the destination */
  default int numPiece (int row, int column)
  {
    // stores the result of the method
    int result = 0;
    
    if (this.getRow() == row)
    {
      // stores the starting column of the moving path
      int startCol = this.getColumn() > column ? column : this.getColumn();
      // stores the ending column of the moving path
      int endCol = this.getColumn() > column ? this.getColumn() : column;
      
      /* Goal of the loop: Check if there is another piece on the moving path of this piece
       * Goal of each iteration: Check if there is a piece at current column i of the moving path */
      for (int i = startCol + 1; i < endCol; ++i)
        if (this.getChessBoard().hasPiece(row, i)) ++result;
    }
    else if (this.getColumn() == column)
    {
      // stores the starting row of the moving path
      int startRow = this.getRow() > row ? row : this.getRow();
      // stores the ending row of the moving path
      int endRow = this.getRow() > row ? this.getRow() : row;
      
      /* Goal of the loop: Check if there is another piece on the moving path of this piece
       * Goal of each iteration: Check if there is a piece at current row i of the moving path */
      for (int i = startRow + 1; i < endRow; ++i)
        if (this.getChessBoard().hasPiece(i, column)) ++result;
    }
    
    else return 999;
    
    return result;
  }
  
  // returns the allowance for the piece to move either horizontally or vertically
  default boolean isLegalStraightMove (int row, int column)
  {
    if (numPiece(row, column) > 0) 
      return false;
    return (this.isLegalNonCaptureMove(row, column) || this.isLegalCaptureMove(row, column));
  }
}