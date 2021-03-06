// @author Escanord Le (Duy Le)
// This interface representing a normal chess piece that moves diagonally
public interface MoveDiagonally
{
  /***** NON-STATIC METHODS ******/
  // returns the chess board the piece is on
  ChessBoard getChessBoard();
  
  // returns the row the piece is on
  int getRow();
  
  // returns the column the piece is on
  int getColumn();
  
  // returns the side of the piece
  ChessGame.Side getSide();
  
  // returns the allowance to move the piece to [row, column] position having no peice on the board
  public boolean isLegalNonCaptureMove(int row, int column);
  
  // returns the allowance to move the piece to [row, column] position having a peice on the board
  public boolean isLegalCaptureMove(int row, int column);
  
  // return if the diagonal moving path is legal or not
  default boolean isLegalDiagonalMove (int row, int column)
  {
    // moving path in the direction from top left to down right or reverse direction
    if ((this.getRow() - row) * (this.getColumn() - column) >= 0)
    {
      // stores the starting column of the moving path
      int startCol = this.getColumn() > column ? column : this.getColumn();
      // stores the starting row of the moving path
      int startRow = this.getRow() > row ? row : this.getRow();
      // stores the length of the moving path
      int pathLength = this.getColumn() - column > 0 ? this.getColumn() - column : column - this.getColumn();
      
      /* Goal of the loop: Check if there is another piece on the moving path of this piece
       * Goal of each iteration: Check if there is a piece at current row and current column of the moving path */
      for (int i = 1; i < pathLength; ++i)
        if (this.getChessBoard().hasPiece(startRow + i, startCol + i)) return false;
      return (this.isLegalNonCaptureMove(row, column) || this.isLegalCaptureMove(row, column));
    }
    // moving path in the direction from down left to top right or reverse direction
    else
    {
      // stores the starting column of the moving path
      int startCol = this.getColumn() > column ? column : this.getColumn();
      // stores the starting row of the moving path
      int startRow = this.getRow() < row ? row : this.getRow();
      // stores the length of the moving path
      int pathLength = this.getColumn() - column > 0 ? this.getColumn() - column : column - this.getColumn();
      
      /* Goal of the loop: Check if there is another piece on the moving path of this piece
       * Goal of each iteration: Check if there is a piece at current row and current column of the moving path */
      for (int i = 1;i < pathLength; ++i)
        if (this.getChessBoard().hasPiece(startRow - i, startCol + i)) return false;
      return (this.isLegalNonCaptureMove(row, column) || this.isLegalCaptureMove(row, column));
    }
  }
}