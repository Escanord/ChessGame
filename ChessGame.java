/**
 * An interface that encodes specific rules for a version of chess.
 * 
 * @author Harold Connamacher
 * @author Escanord Le
 */
public abstract class ChessGame {
  /*********** FIELDS ************/
  // stores the side of first player
  private ChessGame.Side firstPlayer;
  // stores the side of second player
  private ChessGame.Side secondPlayer;
  // stores the chess board
  private ChessBoard board;
  // stores the current player of the game
  private ChessGame.Side currentPlayer;
  // stores the number of rows of the board
  private final int numRows;
  // stores the number of columns of the board
  private final int numColumns;
  
  
  /** creates a chess game with given row and columns 
    * @param numRows the number of rows of the chess game
    * @param numColumns the number of columns of the chess game 
    * @param firstPlayer the string indicating the first player of the game
    * @param secondPlayer the string indicating the second player of the game
    * @param board the board of the game */
  public ChessGame (int numRows, int numColumns, String firstPlayer, String secondPlayer, ChessBoard board)
  {
    this.numRows = numRows;
    this.numColumns = numColumns;
    this.firstPlayer = ChessGame.Side.valueOf(firstPlayer);
    this.secondPlayer = ChessGame.Side.valueOf(secondPlayer);
    this.board = board;
    this.currentPlayer = this.getFirstPlayer();
  }
  
  /** The "side" or "team" or "player" the piece belongs to.  
    * The "players" are named by the compass positions around the board
    */
  public enum Side {NORTH, SOUTH, EAST, WEST};
  
  /***** NON-STATIC METHODS ******/
  /** returns the first player of the game
    * @return the first player of the game */
  protected ChessGame.Side getFirstPlayer()
  {
    return this.firstPlayer;
  }
  
  /** returns the second player of the game
    * @return the second player of the game */
  protected ChessGame.Side getSecondPlayer()
  {
    return this.secondPlayer;
  }
  
  /** returns the player of current turn
    * @return the player of current turn */
  protected ChessGame.Side getCurrentPlayer()
  {
    return this.currentPlayer;
  }
  
  /** returns the chess board
    * @return the chess board */
  public ChessBoard getChessBoard()
  {
    return this.board;
  }
  
  /** sets the current player of the game */
  protected void setCurrentPlayer()
  {
    if (this.getCurrentPlayer() == this.firstPlayer) this.currentPlayer = this.getSecondPlayer();
    else this.currentPlayer = this.getFirstPlayer();
  }
  
  /** determines if the piece is legal to play or not
    * @param piece the given piece to be checked
    * @param row the row of the given piece
    * @param column the column of the given piece 
    * @return true if the piece is legal to be played */
  public boolean legalPieceToPlay(ChessPiece piece, int row, int column)
  {
    if (piece.getSide() != this.getCurrentPlayer()) return false;
    
    /* Goal of the loop: Checking all rows in the board if there is any block the piece can move to
     * Goal of each iteration : Checking all block of i-th row if there is any block the piece can move to */
    for (int i = 0; i < this.getNumRows(); ++i)
    {
      /* Goal of the loop: Checking all block of i-th row if there is any block the piece can move to
       * Goal of each iteration: Checking if current j-th block of i-th row the piece can move to */
      for (int j = 0; j < this.getNumColumns(); ++j)
        if (piece.isLegalMove(i, j)) return true;
    }
    
    return false;
  }
  
  /** Moves a piece to a new position.
    * @param piece     the piece to move
    * @param toRow     the row of the square the piece is moving to
    * @param toColumn  the column of the square the piece is moving to
    * @return true if the move was made, false if the move was not made
    */
  public abstract boolean makeMove(ChessPiece piece, int toRow, int toColumn);
  
  /**
   * Returns whether a user can choose a different piece from the one selected or if they have to move the selected piece.
   * If this method returns false, then the <tt>legalPieceToPlay</tt> method must not return true if that piece has no
   * legal moves.  Otherwise the game could freeze with a player not permitted to change selection of a piece with no legal moves.
   * @param piece   the piece the user selected
   * @param row     the row of the square the piece is on
   * @param column  the column of the square the piece is on
   * @return true if the player can change the piece they selected and false if they cannot and must move that piece
   */
  public boolean canChangeSelection(ChessPiece piece, int row, int column) {
    return true;
  }
  
  /** returns the number of rows of this chess game
    * @return the number of rows of this chess game */
  public int getNumRows()
  {
    return this.numRows;
  }
  
  /** returns the number of columns of this chess game
    * @return the number of columns of this chess game */
  int getNumColumns()
  {
    return this.numColumns;
  }
  
  /** places all piece of this chess game onto the given board and begins some neccessary initialization for the chess game
    * @param board the given board of the chess game */
  public abstract void startGame (ChessBoard board);
  
}