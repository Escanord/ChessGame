import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * <p>Creates a chessboard in a window on the desktop.  The ChessBoard has a ChessBoardDisplay object that determines
 * how the individual squares of the chessboard should be drawn.</p>
 * 
 * <p>The chessboard uses a ChessGame object to determine how the game should be played.  The way the chessboard works
 * is as follows.  The player selects a piece by clicking on the board, and
 * and the chessboard calls the <tt>legalPieceToPlay</tt> method of the ChessGame object.
 * If the player is allowed to select the piece, the board highlights it, and the player can select another square on
 * the board.  The chessboard then calls the <tt>makeMove</tt> method of the ChessGame object.  The ChessGame is 
 * responsible for determining if the move is valid, and if it is to update the game and the chessboard
 * with the results of making that move.</p>
 * 
 * @author Harold Connamacher
 * @author Escanord Le
 */
public class SwingChessBoard implements ChessBoard {
  
  private JFrame board;                          // the game board
  private JButton[][] squares;                   // the squares of the board
  private SwingChessBoardDisplay boardDisplay;   // rules for how to draw the chess board
  private ChessGame gameRules;                   // stores the rules of the game played on the board
  private ChessPiece[][] pieces;                 // stores the chess piece on the board
  
  /**
   * Builds a board of the desired size, the display parameters, and the rules for the chess game.
   * @param boardDisplay  an object that determines how the squares on the chessboard should be drawn
   * @param chessGame  an object that determines when player selection is valid and to update the game with the result of a move
   */
  public SwingChessBoard(SwingChessBoardDisplay boardDisplay, ChessGame chessGame) {
    this.gameRules = chessGame;
    this.pieces = new ChessPiece[gameRules.getNumRows()][gameRules.getNumColumns()];
    // for Mac computers: this allows us to change a button background
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e) {
    }
    
    // initialize the board
    this.boardDisplay = boardDisplay;
    squares = new JButton[chessGame.getNumRows()][chessGame.getNumColumns()];
    
    // create the board visuals on the event dispatch thread
    try {
      SwingUtilities.invokeAndWait(new Runnable() {
        public void run() {
          board = new JFrame();
          
          // create a grid for the squares and the listener for the user clicks
          JPanel panel = new JPanel(new GridLayout(chessGame.getNumRows(), chessGame.getNumColumns()));
          ActionListener responder = new ChessAction();
          
          // create the squares
          for (int i = 0; i < chessGame.getNumRows(); i++) {
            for (int j = 0; j< chessGame.getNumColumns(); j++) {
              squares[i][j] = new JButton();
              squares[i][j].addActionListener(responder);
              boardDisplay.displayEmptySquare(squares[i][j], i, j);
              panel.add(squares[i][j]);
              ChessPiece piece = getPiece(i, j); 
              piece = null;
            }
          }
          board.add(panel);
          board.setSize(boardDisplay.getSquareSize() * chessGame.getNumColumns(), boardDisplay.getSquareSize() * chessGame.getNumRows());
          board.setVisible(true);
        }
      });
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Changes the rules of the game
   * @param newRules the new rules for the game
   */
  public void setGameRules(ChessGame newRules) {
    this.gameRules = newRules;
  }
  
  /** returns the game rule being played on this board
    * @return the ChessGame being played on this board */
  public ChessGame getGameRules()
  {
    return this.gameRules;
  }
  
  /** returns the piece at the given row and column
    * @param row the given row
    * @param column the given column */
  public ChessPiece getPiece (int row, int column)
  {
    return pieces[row][column];
  }
  
  /** returns if there is any piece at the given position
    * @param row the row of the given position
    * @param column the column of the given position
    * @return true if there is a piece at given position */
  public boolean hasPiece (int row, int column)
  {
    return pieces[row][column] != null;
  }
  
  /**
   *  Adds a piece to the board at the desired location.  Any piece currently
   *  at that location is lost.
   *  @param piece   the piece to add
   *  @param row     the row for the piece
   *  @param col     the column for the piece
   */
  public void addPiece(final ChessPiece piece, final int row, final int col) {
    // set the piece on the board, tell the piece where it is, and then use the display rules to display the square
    // run the display code on the event dispatch thread
    
    pieces[row][col] = piece;
    piece.setLocation(row, col);
    
    Runnable addPiece = new Runnable() {
      public void run() {
        boardDisplay.displayFilledSquare(squares[row][col], row, col, piece);
      }
    };
    
    // run the code to change the display on the event dispatch to avoid drawing errors
    if (SwingUtilities.isEventDispatchThread())
      addPiece.run();
    else {
      try {
        SwingUtilities.invokeAndWait(addPiece);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  
  /**
   *  Removes a piece from the board
   *  @param row  the row of the piece
   *  @param col  the column of the piece
   *  @return  the piece removed of null if there was no piece at that square
   */
  public ChessPiece removePiece(final int row, final int col) {
    // remove the piece from the board, use the display rules to show an empty square,
    // and run the display code on the event dispatch thread
    ChessPiece save = pieces[row][col];
    pieces[row][col] = null;
    
    Runnable removePiece = new Runnable() {
      public void run() {
        boardDisplay.displayEmptySquare(squares[row][col], row, col);
      }
    };    
    if (SwingUtilities.isEventDispatchThread())
      removePiece.run();
    else {
      try {
        SwingUtilities.invokeAndWait(removePiece);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    
    return save;
  }
  
  /** The code the responds when the user clicks on the game board */
  private class ChessAction implements ActionListener {
    private boolean firstPick = true;  // if true, we a selecting a piece
    private int pieceRow;              // remember row of selected piece
    private int pieceCol;              // remember column of selected piece
    
    /** 
     * What we do when the user chooses the piece to move.
     * @param row the row of the chosen piece
     * @param col the column of the chosen piece
     */
    private void processFirstSelection(int row, int col) {
      if (hasPiece(row, col) && 
          (getGameRules() == null || getGameRules().legalPieceToPlay(getPiece(row, col), row, col))) {
        /*
         * if this is the first pick and a square with a piece was picked,
         * remember the piece's location and highlight the square.
         */
        pieceRow = row;
        pieceCol = col;
        boardDisplay.highlightSquare(true, squares[row][col], row, col, getPiece(row, col));
        firstPick = false;
      }
    }
    
    /**
     * What we do when the user chooses the square to move the piece to.
     * @param row the row the piece will move to
     * @param col the column the piece will move to
     */
    private void processSecondSelection(int row, int col) {
      if (row == pieceRow && col == pieceCol)
        return;
      
      boolean moveMade = getGameRules().makeMove(getPiece(pieceRow, pieceCol), row, col);
      
      // if the move was made or if it was not made and the user select a new piece, then reset to choose a new move
      if (moveMade || getGameRules().canChangeSelection(getPiece(pieceRow, pieceCol), pieceRow, pieceCol)) {
        boardDisplay.highlightSquare(false, squares[pieceRow][pieceCol], pieceRow, pieceCol, getPiece(pieceRow, pieceCol));
        firstPick = true;
      }
    }
    
    /**
     *  Handle a button click.  The method alternates between selecting a piece
     *  and selecting any square.  After both are selected, the piece's 
     *  legalMove is called, and if the move is legal, the piece is moved.
     *  @param e   the event that triggered the method
     */
    public void actionPerformed(ActionEvent e) {
      JButton b = (JButton)e.getSource();
      int col = -1;
      int row = -1;
      
      // first find which button (board square) was clicked.
      for (int i = 0; i < squares.length; i++) {
        for (int j = 0; j < squares[i].length; j++) {
          if (squares[i][j] == b) {
            row = i;
            col = j;
          }
        }
      }
      
      if (firstPick) {
        processFirstSelection(row, col);
      }
      else {
        processSecondSelection(row, col);
      }
    }
  }
  
}


