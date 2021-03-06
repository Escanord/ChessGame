// @author Escanord Le (Duy Le)
// This class representing a normal rook piece
public class RookPiece extends FirstMovePiece implements MoveStraight, FacingKingMove
{
  /*********** FIELDS ************/
  
  /******** CONSTRUCTORS *********/
  // creates a rook piece with given side, row, column, and chess board
  public RookPiece (String side, int row, int column, ChessBoard chessBoard, Object icon)
  {
    super(side, row, column, chessBoard, "R", icon);
  }
  
  /***** NON-STATIC METHODS ******/
  // returns the allowance to move the piece to [toRow, toColumn] position on the board
  @Override
  public boolean isLegalMove (int toRow, int toColumn)
  {
    if (getChessBoard().getGameRules() instanceof Xiangqi && !isFacingKingMove(this, getRow(), getColumn(), toRow, toColumn)) 
      return false;
    return isLegalStraightMove(toRow, toColumn);
  }
  
  // handling the work after the move of the piece
   public void moveDone()
   {
     this.setHasMoved();
   }
  
  /****** STATIC METHODS *********/
}