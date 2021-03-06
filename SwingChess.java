/** @author Escanord Le
  * This class launching an european chess game */
public class SwingChess
{
  /*********** FIELDS ************/
  
  /****** STATIC METHODS *********/
  /* main method launching the chess game
   * @param args the input from the user */
  public static void main (String[] args)
  {
    // stores the chess board of the game
    SwingChessBoard chessBoard = new SwingChessBoard(new SwingEuropeanChessDisplay(), new EuropeanChess("NORTH", "SOUTH", null));
    // stores the ruling object of the game
    EuropeanChess europeanChess = new EuropeanChess("NORTH", "SOUTH", chessBoard);
    chessBoard.setGameRules(europeanChess);
    europeanChess.startGame(chessBoard);
  }
}