// @author Escanord Le (Duy Le)
// This class representing a normal queen piece
public class QueenPiece extends ChessPiece implements MoveStraight, MoveDiagonally
{
  /*********** FIELDS ************/
  
  /******** CONSTRUCTORS *********/
  // creates a queen piece with given side, row, column, and chess board
  public QueenPiece (String side, int row, int column, ChessBoard chessBoard, Object icon)
  {
    super(side, row, column, chessBoard, "Q", icon);
  }
  
  /***** NON-STATIC METHODS ******/
  // returns the allowance to move the piece to [toRow, toColumn] position on the board
  public boolean isLegalMove (int toRow, int toColumn)
  {
    if (this.getRow() == toRow || this.getColumn() == toColumn) return isLegalStraightMove(toRow, toColumn);
    if (Math.abs(this.getRow() - toRow) == Math.abs(this.getColumn() - toColumn)) return isLegalDiagonalMove(toRow, toColumn);
    return false;
  }
  
  /****** STATIC METHODS *********/
}