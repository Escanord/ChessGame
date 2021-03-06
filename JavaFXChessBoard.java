/** JavaFX implementation of ChessBoard
  * @author Escanord Le */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class JavaFXChessBoard extends Application implements ChessBoard
{
  /*********** FIELDS ************/
  // stores the rules of the game played on the board
  private ChessGame gameRules;  
  // stores the chess piece on the board
  private ChessPiece[][] pieces;  
  // stores the display implementation of the chess board
  JavaFXChessBoardDisplay display;
  // stores the square of the board
  Button[][] squares;             
  
  /***** NON-STATIC METHODS ******/
  /** returns the game rule being played on this board
    * @return the ChessGame being played on this board */
  public ChessGame getGameRules()
  {
    return this.gameRules;
  }
  
  /** adds a chess piece to a given row and column on the board
    * @param piece the chess piece to be added
    * @param row the row to add the piece to
    * @param column the column to add the piece to */
  public void addPiece (ChessPiece piece, int row, int column)
  {
    pieces[row][column] = piece;
    piece.setLocation(row, column);
    display.displayFilledSquare(squares[row][column], row, column, piece);
  }
  
  /** removes the chess piece at the given row and column and returns it
    * @param row the given row of the piece to be removed
    * @param column the given column of the piece to be removed */
  public ChessPiece removePiece (int row, int column)
  {
    // stores the removed piece
    ChessPiece piece = pieces[row][column];
    pieces[row][column] = null;
    display.displayEmptySquare(squares[row][column], row, column);
    
    return piece;
  }
  
  /** returns if there is any piece at the given row and column
    * @param row the given row to be checked
    * @param column the given column to be checked
    * @return true if there is any piece at the given row and column, false otherwise*/
  public boolean hasPiece (int row, int column)
  {
    return pieces[row][column] != null;
  }
  
  /** returns the piece at the given row and column
    * @param row the given row
    * @param column the given column */
  public ChessPiece getPiece (int row, int column)
  {
    return pieces[row][column];
  }
  
  /** inilization of the chess board
    * @param primaryStage the stage for the application */
  public void start(Stage primaryStage)
  {
    // stores the chess type of the game
    String gameType = getParameters().getRaw().get(0);
    // stores the board of the game
    GridPane board = new GridPane();
    // stores the scene of the stage
    Scene scene = new Scene(board);
    // stores the event handler of the board
    EventHandler<MouseEvent> eventHandler = new ChessAction();
    
    if (gameType.equals("chess"))
    {
      primaryStage.setWidth(320);
      primaryStage.setHeight(342);
      // inilizations of the chess game
      gameRules = new EuropeanChess("NORTH", "SOUTH", this);
      pieces = new ChessPiece[8][8];
      display = new JavaFXEuropeanChessDisplay();
      squares = new Button[8][8];
      
      /* Goal of the loop: creating button for each square on the board
       * Goal of each iteration : creating button for each row of the board */
      for (int i = 0; i < 8; ++i)
      {
        /* Goal of the loop: creating button for each row of the board
         * Goal of each iteration: creating botton for each square of the current row */
        for (int j = 0; j < 8; ++j)
        {
          squares[i][j] = new Button();
          squares[i][j].setOnMouseClicked(eventHandler);
          display.displayEmptySquare(squares[i][j], i, j);
          squares[i][j].setMinWidth(40);
          squares[i][j].setMinHeight(40);
          squares[i][j].setMaxWidth(40);
          squares[i][j].setMaxHeight(40);
          board.add(squares[i][j], j, i);
        }
      }
    }
    else
    {
      primaryStage.setWidth(360);
      primaryStage.setHeight(422);
      // inilizations of the chess game
      gameRules = new Xiangqi("NORTH", "SOUTH", this);
      pieces = new ChessPiece[10][9];
      display = new JavaFXXiangqiDisplay(ChessGame.Side.NORTH);
      squares = new Button[10][9];
      
      /* Goal of the loop: creating button for each square on the board
       * Goal of each iteration : creating button for each row of the board */
      for (int i = 0; i < 10; ++i)
      {
        /* Goal of the loop: creating button for each row of the board
         * Goal of each iteration: creating botton for each square of the current row */
        for (int j = 0; j < 9; ++j)
        {
          squares[i][j] = new Button();
          squares[i][j].setOnMouseClicked(eventHandler);
          display.displayEmptySquare(squares[i][j], i, j);
          squares[i][j].setMinWidth(40);
          squares[i][j].setMinHeight(40);
          squares[i][j].setMaxWidth(40);
          squares[i][j].setMaxHeight(40);
          board.add(squares[i][j], j, i);
        }
      }
    }

    gameRules.startGame(this);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  /* STATIC METHODS & NESTED TYPE */
  /** lauches the chess board and starts the game
    * @param args the chess game wanna be played */
  public static void main(String[] args)
  {
    Application.launch(args);
  }
  
  /** the event handler when users select a square on the chess board 
    * @author Escanord Le */
  private class ChessAction implements EventHandler<MouseEvent>
  {
    // stores id this is the first selection or not
    private boolean firstSelection = true;
    // stores the row of the selected piece
    private int row = -1;
    // stores the column of the selected piece
    private int column = -1;
    
    /** handle the first selection of the current player
      * @param row the row of the slected square
      * @param column the column of the selected square */
    private void processFirstPick (int row, int column)
    {
      if (!hasPiece(row, column)) return;
      if (getGameRules().legalPieceToPlay(getPiece(row, column), row, column))
      {
        display.highlightSquare(true, squares[row][column], row, column, getPiece(row, column));
        this.row = row;
        this.column = column;
        firstSelection = false;
      }
    }
    
    /** handle the second selection of the current player
      * @param row the selected row
      * @param column the selected column */
    private void processSecondPick (int row, int column)
    {
      if (row == this.row && column == this.column)
        return;
      boolean moveStatus = getGameRules().makeMove(getPiece(this.row, this.column), row, column);
      if (moveStatus || getGameRules().canChangeSelection(getPiece(this.row, this.column), this.row, this.column))
      {
        firstSelection = true;
        display.highlightSquare(false, squares[this.row][this.column], this.row, this.column, getPiece(this.row, this.column));
      }
    }
    
    /** handle the event of the square
      * @param event the occured event */
    public void handle (MouseEvent event)
    {
      // stores the button of the selected square
      Object button = event.getSource();
      // stores the row of the selected square
      int row = -1;
      // stores the column of the selected square
      int column = -1;
      
      /** Goal of the loop: check if there is a particular button on the board
        * Goal of the iteration: check if there is a particular button on the current row of the board */
      for (int i = 0; i < getGameRules().getNumRows(); ++i)
      {
        /** Goal of the loop: check if there is a particular button on the current row of the board
          * Goal of each iteration : check if there is a particular button at the current position */
        for (int j = 0; j < getGameRules().getNumColumns(); ++j)
        {
          if (squares[i][j] == button)
          {
            row = i;
            column = j;
          }
        }
      }
      
      if (firstSelection)
        processFirstPick(row, column);
      else
        processSecondPick(row ,column);
    }
  }
}