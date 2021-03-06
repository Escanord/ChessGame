// @author Escanord Le (Duy Le)
// This class representing a normal chess piece
public abstract class ChessPiece
{
  /*********** FIELDS ************/
  // stores the side of the piece
  private ChessGame.Side side;
  
  // stores the row location of the piece
  private int row;
  
  // stores the column location of the piece
  private int column;
  
  // stores the label of the piece
  private String label;
  
  // stores the chess board the piece is on
  private ChessBoard chessBoard;
  
  // stores the icon of the piece
  private Object icon;
  
  /******** CONSTRUCTORS *********/
  // creates a piece with given side, row, column, and chess board
  public ChessPiece (String side, int row, int column, ChessBoard chessBoard, String label, Object icon)
  {
    this.side = ChessGame.Side.valueOf(side);
    this.row = row;
    this.column = column;
    this.chessBoard = chessBoard;
    this.label = label;
    this.icon = icon;
  }
  
  /***** NON-STATIC METHODS ******/
  // returns the side of the chess piece
  public ChessGame.Side getSide()
  {
    return this.side;
  }
  
  // returns the label of the chess piece
  public String getLabel()
  {
    return this.label;
  }
  
  // returns the icon of the chess piece
  public Object getIcon()
  {
    return this.icon;
  }
  
  // sets the location of the piece on the board
  public void setLocation (int row, int column)
  {
    this.row = row;
    this.column = column;
  }
  
  // returns the current row of the piece
  public int getRow()
  {
    return this.row;
  }
  
  // returns the current column of the piece
  public int getColumn()
  {
    return this.column;
  }
  
  // returns the allowance to move the piece to [toRow, toColumn] position on the board
  public abstract boolean isLegalMove (int toRow, int toColumn);
  
  // returns the chess board the piece is on
  public ChessBoard getChessBoard()
  {
    return this.chessBoard;
  }
  
  // returns the allowance to move the piece to [row, column] position having no peice on the board
  public boolean isLegalNonCaptureMove(int row, int column)
  {
    return (!this.getChessBoard().hasPiece(row, column));
  }
  
  // returns the allowance to move the piece to [row, column] position having a peice on the board
  public boolean isLegalCaptureMove(int row, int column)
  {
    return (this.getChessBoard().hasPiece(row, column) && this.getChessBoard().getPiece(row, column).getSide() != this.getSide());
  }
  
  // handling the work after the move of the piece
   public void moveDone()
   {
   }
  /****** STATIC METHODS *********/
  
}