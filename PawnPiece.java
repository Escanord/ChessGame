// @author Escanord Le (Duy Le)
// This class representing a normal pawn piece

import javax.swing.JOptionPane;
public class PawnPiece extends FirstMovePiece implements MoveStraight
{
  /*********** FIELDS ************/
  
  /******** CONSTRUCTORS *********/
  // creates a rook piece with given side, row, column, and chess board
  public PawnPiece (String side, int row, int column, ChessBoard chessBoard, Object icon)
  {
    super(side, row, column, chessBoard, "P", icon);
  }
  
  /***** NON-STATIC METHODS ******/
  // returns the allowance to move the piece to [toRow, toColumn] position on the board
  public boolean isLegalMove (int toRow, int toColumn)
  {
    if (this.getSide() == ChessGame.Side.NORTH)
    {
      if (toRow - this.getRow() == 1 && Math.abs(this.getColumn() - toColumn) == 1) return isLegalCaptureMove(toRow, toColumn);
      if (toRow - this.getRow() == 1 && this.getColumn() == toColumn) return isLegalNonCaptureMove(toRow, toColumn);
      if (toRow - this.getRow() == 2 && this.getColumn() == toColumn && !this.getHasMoved()) return isLegalStraightMove(toRow, toColumn);
    }
    else if (this.getSide() == ChessGame.Side.SOUTH)
    {
      if (toRow - this.getRow() == -1 && Math.abs(toColumn - this.getColumn()) == 1) return isLegalCaptureMove(toRow, toColumn);
      if (toRow - this.getRow() == -1 && this.getColumn() == toColumn) return isLegalNonCaptureMove(toRow, toColumn);
      if (toRow - this.getRow() == -2 && this.getColumn() == toColumn && !this.getHasMoved()) return isLegalStraightMove(toRow, toColumn);
    }
    return false;
  }
  
  // handling the work after the move of the piece
  @Override
   public void moveDone()
   {
     this.setHasMoved();
     if ((this.getSide() == ChessGame.Side.NORTH && this.getRow() == 7) || (this.getSide() == ChessGame.Side.SOUTH && this.getRow() == 0))
     {
       // stores the pieces that pawn piece can transform to
       String[] possibilities = new String[] {"Bishop", "Knight", "Queen", "Rook"};
       // stores the piece that the pawn will transform to
       String desiredPiece = (String)JOptionPane.showInputDialog(null, "Please select the piece you want the pawn to transform to:", "Which piece do you want?", JOptionPane.PLAIN_MESSAGE, null, possibilities, "Bishop");
       this.getChessBoard().removePiece(this.getRow(), this.getColumn());
       if (desiredPiece.equals("Bishop")) this.getChessBoard().addPiece(new BishopPiece(this.getSide().toString(), this.getRow(), this.getColumn(), this.getChessBoard(), null), this.getRow(), this.getColumn());
       if (desiredPiece.equals("Knight")) this.getChessBoard().addPiece(new KnightPiece(this.getSide().toString(), this.getRow(), this.getColumn(), this.getChessBoard(), null), this.getRow(), this.getColumn());
       if (desiredPiece.equals("Queen")) this.getChessBoard().addPiece(new QueenPiece(this.getSide().toString(), this.getRow(), this.getColumn(), this.getChessBoard(), null), this.getRow(), this.getColumn());
       if (desiredPiece.equals("Rook")) this.getChessBoard().addPiece(new RookPiece(this.getSide().toString(), this.getRow(), this.getColumn(), this.getChessBoard(), null), this.getRow(), this.getColumn());
     }
   }
  /****** STATIC METHODS *********/
}