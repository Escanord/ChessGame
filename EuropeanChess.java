/** implements the game play of European chess game
  * @author Escanord Le (Duy Le) */
public class EuropeanChess extends ChessGame
{
  /*********** FIELDS ************/
  
  /******** CONSTRUCTORS *********/
  /* construct a european chess class with given two players and a chess board
   * @param firstPlayer the first player of the game
   * @param secondPlayer the second player of the game
   * @param board the chess board of this game play */
  public EuropeanChess (String firstPlayer, String secondPlayer, ChessBoard board)
  {
    super(8, 8, firstPlayer, secondPlayer, board);
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
    if (!piece.isLegalMove(toRow, toColumn)) return false;
    
    // stores the status of calling moveDone() method
    boolean callMoveDone = false;
    if (piece instanceof PawnPiece || (piece instanceof KingPiece && ((KingPiece)piece).isLegalCastleMove(toRow, toColumn))) callMoveDone = true;
    this.getChessBoard().removePiece(piece.getRow(), piece.getColumn());
    piece.setLocation(toRow, toColumn);
    if (this.getChessBoard().hasPiece(toRow, toColumn)) this.getChessBoard().removePiece(toRow, toColumn);
    this.getChessBoard().addPiece(piece, toRow, toColumn);
    if (callMoveDone) piece.moveDone();
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
      board.addPiece(new RookPiece("NORTH", 0, 7, board, null), 0, 7);
      board.addPiece(new KnightPiece("NORTH", 0, 1, board, null), 0, 1);
      board.addPiece(new KnightPiece("NORTH", 0, 6, board, null), 0, 6);
      board.addPiece(new BishopPiece("NORTH", 0, 2, board, null), 0, 2);
      board.addPiece(new BishopPiece("NORTH", 0, 5, board, null), 0, 5);
      board.addPiece(new KingPiece("NORTH", 0, 3, board, null), 0, 3);
      board.addPiece(new QueenPiece("NORTH", 0, 4, board, null), 0, 4);
      /** Goal of the loop: add pawn piece to the game 
        * Goal of each iteration: add pawn to particular square */
      for (int i = 0; i < 8; ++i)
        board.addPiece(new PawnPiece("NORTH", 1, i, board, null), 1, i);
      
      board.addPiece(new RookPiece("SOUTH", 7, 0, board, null), 7, 0);
      board.addPiece(new RookPiece("SOUTH", 7, 7, board, null), 7, 7);
      board.addPiece(new KnightPiece("SOUTH", 7, 1, board, null), 7, 1);
      board.addPiece(new KnightPiece("SOUTH", 7, 6, board, null), 7, 6);
      board.addPiece(new BishopPiece("SOUTH", 7, 2, board, null), 7, 2);
      board.addPiece(new BishopPiece("SOUTH", 7, 5, board, null), 7, 5);
      board.addPiece(new KingPiece("SOUTH", 7, 4, board, null), 7, 4);
      board.addPiece(new QueenPiece("SOUTH", 7, 3, board, null), 7, 3);
      /** Goal of the loop: add pawn piece to the game 
        * Goal of each iteration: add pawn to particular square */
      for (int i = 0; i < 8; ++i)
        board.addPiece(new PawnPiece("SOUTH", 6, i, board, null), 6, i);
    }
    else
    {
      board.addPiece(new RookPiece("WEST", 0, 0, board, null), 0, 0);
      board.addPiece(new RookPiece("WEST", 7, 0, board, null), 7, 0);
      board.addPiece(new KnightPiece("WEST", 1, 0, board, null), 1, 0);
      board.addPiece(new KnightPiece("WEST", 6, 0, board, null), 6, 0);
      board.addPiece(new BishopPiece("WEST", 2, 0, board, null), 2, 0);
      board.addPiece(new BishopPiece("WEST", 5, 0, board, null), 5, 0);
      board.addPiece(new KingPiece("WEST", 3, 0, board, null), 3, 0);
      board.addPiece(new QueenPiece("WEST", 4, 0, board, null), 4, 0);
      /** Goal of the loop: add pawn piece to the game 
        * Goal of each iteration: add pawn to particular square */
      for (int i = 0; i < 8; ++i)
        board.addPiece(new PawnPiece("WEST", i, 1, board, null), i, 1);
      
      board.addPiece(new RookPiece("EAST", 0, 7, board, null), 0, 7);
      board.addPiece(new RookPiece("EAST", 7, 7, board, null), 7, 7);
      board.addPiece(new KnightPiece("EAST", 1, 7, board, null), 1, 7);
      board.addPiece(new KnightPiece("EAST", 6, 7, board, null), 6, 7);
      board.addPiece(new BishopPiece("EAST", 2, 7, board, null), 2, 7);
      board.addPiece(new BishopPiece("EAST", 5, 7, board, null), 5, 7);
      board.addPiece(new KingPiece("EAST", 4, 7, board, null), 4, 7);
      board.addPiece(new QueenPiece("EAST", 3, 7, board, null), 3, 7);
      /** Goal of the loop: add pawn piece to the game 
        * Goal of each iteration: add pawn to particular square */
      for (int i = 0; i < 8; ++i)
        board.addPiece(new PawnPiece("EAST", i, 6, board, null), i, 6);
    }
  }
}