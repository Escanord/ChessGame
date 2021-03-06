// @author Escanord Le (Duy Le)
// This class representing a normal king piece
public class KingPiece extends FirstMovePiece
{
  /*********** FIELDS ************/
  
  /******** CONSTRUCTORS *********/
  // creates a king piece with given side, row, column, and chess board
  public KingPiece (String side, int row, int column, ChessBoard chessBoard, Object icon)
  {
    super(side, row, column, chessBoard, "K", icon);
  }
  
  /***** NON-STATIC METHODS ******/
  // returns the allowance to perform castle move
  public boolean isLegalCastleMove (int toRow, int toColumn)
  {
    if (!this.getHasMoved() && this.getRow() == toRow && Math.abs(this.getColumn() - toColumn) == 2)
    {
      // stores the corner column of the side this king piece is moving to
      int cornerColumn = this.getColumn() > toColumn ? 0 : (this.getChessBoard().getGameRules().getNumColumns() - 1);
      // stores the piece on the corner
      ChessPiece piece = this.getChessBoard().getPiece(toRow, cornerColumn);
      
      if (piece instanceof RookPiece && piece.getSide() == this.getSide())
      {
        // typecasting the piece to RookPiece object
        RookPiece rookPiece = (RookPiece) (piece);
        if (rookPiece.getHasMoved()) return false;
        // stores the starting column of the path
        int startColumn = cornerColumn > this.getColumn() ? this.getColumn() : cornerColumn;
        // stores the ending column of the path
        int endColumn = cornerColumn > this.getColumn() ? cornerColumn : this.getColumn();
        
        /* Goal of the loop: check if there is any piece between the king and the rook or any square being threatened
         * Goal of each iteration: check if there is any piece at current square or this square being threatened*/
        for (int i = startColumn + 1; i < endColumn; ++i)
          if (this.getChessBoard().hasPiece(toRow, i) || this.getChessBoard().squareThreatened(toRow, i, this)) return false;
        return true;
      }
      return false;
    }
    return false;
  }
  
  // returns the allowance to move the piece to [toRow, toColumn] position on the board
  @Override
  public boolean isLegalMove (int toRow, int toColumn)
  {
    if ((Math.abs(this.getRow() - toRow) + Math.abs(this.getColumn() - toColumn) == 1) || (Math.abs(this.getRow() - toRow) == 1 && Math.abs(this.getColumn() - toColumn) == 1))
    {
      return (this.isLegalNonCaptureMove(toRow, toColumn) || this.isLegalCaptureMove(toRow, toColumn));
    }
    return isLegalCastleMove(toRow, toColumn);
  }
  
  // handling the work after the move of the piece
  @Override
   public void moveDone()
   {
     this.setHasMoved();
     // stores the corner column of the side closer to this king piece 
     int cornerColumn = this.getColumn() < (this.getChessBoard().getGameRules().getNumColumns() / 2) ? 0 : (this.getChessBoard().getGameRules().getNumColumns() - 1);
     // stores and removes the piece on the corner
     ChessPiece piece = this.getChessBoard().removePiece(this.getRow(), cornerColumn);
     // stores the column rook piece is moved to
     int toColumn = this.getColumn() < (this.getChessBoard().getGameRules().getNumColumns() / 2) ? this.getColumn() + 1 : this.getColumn() - 1;
     
     this.getChessBoard().addPiece(piece, this.getRow(), toColumn); 
   }
   
  /****** STATIC METHODS *********/
}