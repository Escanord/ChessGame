/** implements the game play of Xiangqi chess game
  * @author Escanord Le (Duy Le) */
import java.lang.reflect.*;
public class Xiangqi extends ChessGame
{
  /*********** FIELDS ************/
  
  /******** CONSTRUCTORS *********/
  /* construct a xiangqi chess class with given two players and a chess board
   * @param firstPlayer the first player of the game
   * @param secondPlayer the second player of the game
   * @param board the chess board of this game play */
  public Xiangqi (String firstPlayer, String secondPlayer, ChessBoard board)
  {
    super((firstPlayer.equals("NORTH") || firstPlayer.equals("SOUTH") ? 10 : 9), (firstPlayer.equals("NORTH") || firstPlayer.equals("SOUTH") ? 9 : 10), firstPlayer, secondPlayer, board);
  }
  
  /***** NON-STATIC METHODS ******/
  /** Move a piece to a new position
    * @param piece the given piece to be moved
    * @param toRow the row that the piece would be moved to
    * @param toColumn the column that the piece would be moved to 
    * @return true if the piece can be moved to the given position */
  @Override
  public boolean makeMove(ChessPiece piece, int toRow, int toColumn)
  {
    // stores the interface of the piece
    FacingKingMove checkPiece = (FacingKingMove)(piece);
    if (!piece.isLegalMove(toRow, toColumn) || !checkPiece.isFacingKingMove(piece, piece.getRow(), piece.getColumn(), toRow, toColumn)) return false;
    this.getChessBoard().removePiece(piece.getRow(), piece.getColumn());
    piece.setLocation(toRow, toColumn);
    if (this.getChessBoard().hasPiece(toRow, toColumn)) this.getChessBoard().removePiece(toRow, toColumn);
    this.getChessBoard().addPiece(piece, toRow, toColumn);
    this.setCurrentPlayer();
    
    return true;
  }
  
  /** places all needed pieces onto the given board 
    * @param board the board of the game */
  public void startGame (ChessBoard board)
  { 
    if (getFirstPlayer() == ChessGame.Side.NORTH || getFirstPlayer() == ChessGame.Side.SOUTH)
    {
      board.addPiece(new RookPiece("NORTH", 0, 0, board, null), 0, 0);
      board.addPiece(new RookPiece("NORTH", 0, 8, board, null), 0, 8);
      board.addPiece(new HorsePiece("NORTH", 0, 1, board, null), 0, 1);
      board.addPiece(new HorsePiece("NORTH", 0, 7, board, null), 0, 7);
      board.addPiece(new ElephantPiece("NORTH", 0, 2, board, null), 0, 2);
      board.addPiece(new ElephantPiece("NORTH", 0, 6, board, null), 0, 6);
      board.addPiece(new XiangqiKingPiece("NORTH", 0, 4, board, null), 0, 4);
      board.addPiece(new GuardPiece("NORTH", 0, 3, board, null), 0, 3);
      board.addPiece(new GuardPiece("NORTH", 0, 5, board, null), 0, 5);
      board.addPiece(new CannonPiece("NORTH", 2, 1, board, null), 2, 1);
      board.addPiece(new CannonPiece("NORTH", 2, 7, board, null), 2, 7);
      /** Goal of the loop: add soldier piece to the game 
        * Goal of each iteration: add soldier to particular square */
      for (int i = 0; i < 9; ++i)
      {
        if (i % 2 == 0) 
          board.addPiece(new SoldierPiece("NORTH", 3, i, board, null), 3, i);
      }
      
      board.addPiece(new RookPiece("SOUTH", 9, 0, board, null), 9, 0);
      board.addPiece(new RookPiece("SOUTH", 9, 8, board, null), 9, 8);
      board.addPiece(new HorsePiece("SOUTH", 9, 1, board, null), 9, 1);
      board.addPiece(new HorsePiece("SOUTH", 9, 7, board, null), 9, 7);
      board.addPiece(new ElephantPiece("SOUTH", 9, 2, board, null), 9, 2);
      board.addPiece(new ElephantPiece("SOUTH", 9, 6, board, null), 9, 6);
      board.addPiece(new XiangqiKingPiece("SOUTH", 9, 4, board, null), 9, 4);
      board.addPiece(new GuardPiece("SOUTH", 9, 3, board, null), 9, 3);
      board.addPiece(new GuardPiece("SOUTH", 9, 5, board, null), 9, 5);
      board.addPiece(new CannonPiece("SOUTH", 7, 1, board, null), 7, 1);
      board.addPiece(new CannonPiece("SOUTH", 7, 7, board, null), 7, 7);
      /** Goal of the loop: add soldier piece to the game 
        * Goal of each iteration: add soldier to particular square */
      for (int i = 0; i < 9; ++i)
      {
        if (i % 2 == 0) 
          board.addPiece(new SoldierPiece("SOUTH", 6, i, board, null), 6, i);
      }
    }
    else
    {
      board.addPiece(new RookPiece("WEST", 0, 0, board, null), 0, 0);
      board.addPiece(new RookPiece("WEST", 8, 0, board, null), 8, 0);
      board.addPiece(new HorsePiece("WEST", 1, 0, board, null), 1, 0);
      board.addPiece(new HorsePiece("WEST", 7, 0, board, null), 7, 0);
      board.addPiece(new ElephantPiece("WEST", 2, 0, board, null), 2, 0);
      board.addPiece(new ElephantPiece("WEST", 6, 0, board, null), 6, 0);
      board.addPiece(new XiangqiKingPiece("WEST", 4, 0, board, null), 4, 0);
      board.addPiece(new GuardPiece("WEST", 3, 0, board, null), 3, 0);
      board.addPiece(new GuardPiece("WEST", 5, 0, board, null), 5, 0);
      board.addPiece(new CannonPiece("WEST", 1, 2, board, null), 1, 2);
      board.addPiece(new CannonPiece("WEST", 7, 2, board, null), 7, 2);
      /** Goal of the loop: add soldier piece to the game 
        * Goal of each iteration: add soldier to particular square */
      for (int i = 0; i < 9; ++i)
      {
        if (i % 2 == 0) 
          board.addPiece(new SoldierPiece("WEST", i, 3, board, null), i, 3);
      }
      
      board.addPiece(new RookPiece("EAST", 0, 9, board, null), 0, 9);
      board.addPiece(new RookPiece("EAST", 8, 9, board, null), 8, 9);
      board.addPiece(new HorsePiece("EAST", 1, 9, board, null), 1, 9);
      board.addPiece(new HorsePiece("EAST", 7, 9, board, null), 7, 9);
      board.addPiece(new ElephantPiece("EAST", 2, 9, board, null), 2, 9);
      board.addPiece(new ElephantPiece("EAST", 6, 9, board, null), 6, 9);
      board.addPiece(new XiangqiKingPiece("EAST", 4, 9, board, null), 4, 9);
      board.addPiece(new GuardPiece("EAST", 3, 9, board, null), 3, 9);
      board.addPiece(new GuardPiece("EAST", 5, 9, board, null), 5, 9);
      board.addPiece(new CannonPiece("EAST", 1, 7, board, null), 1, 7);
      board.addPiece(new CannonPiece("EAST", 7, 7, board, null), 7, 7);
      /** Goal of the loop: add soldier piece to the game 
        * Goal of each iteration: add soldier to particular square */
      for (int i = 0; i < 9; ++i)
      {
        if (i % 2 == 0) 
          board.addPiece(new SoldierPiece("EAST", i, 6, board, null), i, 6);
      }
    }
  }
}