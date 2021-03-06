// @author Escanord Le (Duy Le)
// This class representing a normal knight piece
public class KnightPiece extends ChessPiece
{
  /*********** FIELDS ************/
  
  /******** CONSTRUCTORS *********/
  // creates a rook piece with given side, row, column, and chess board
  public KnightPiece (String side, int row, int column, ChessBoard chessBoard, Object icon)
  {
    super(side, row, column, chessBoard, "N", icon);
  }
  
  /** creates a knight piece of xiangqi chess game
    * @param side the side of the piece
    * @param row row of the piece
    * @param column column of the piece
    * @param chessBoard the chess board that the piece is on
    * @param icon the icon of the piece */
  protected KnightPiece (String side, int row, int column, ChessBoard chessBoard, String label, Object icon)
  {
    super(side, row, column, chessBoard, label, icon);
  }
  
  /***** NON-STATIC METHODS ******/
  // returns the allowance to move the piece to [toRow, toColumn] position on the board
  @Override
  public boolean isLegalMove (int toRow, int toColumn)
  {
    if (Math.abs(this.getRow() - toRow) == 2 && Math.abs(this.getColumn() - toColumn) == 1) 
    {
      return (this.isLegalNonCaptureMove(toRow, toColumn) || this.isLegalCaptureMove(toRow, toColumn));
    }
    if (Math.abs(this.getRow() - toRow) == 1 && Math.abs(this.getColumn() - toColumn) == 2) 
    {
      return (this.isLegalNonCaptureMove(toRow, toColumn) || this.isLegalCaptureMove(toRow, toColumn));
    }
    return false;
  }
  
  /****** STATIC METHODS *********/
}