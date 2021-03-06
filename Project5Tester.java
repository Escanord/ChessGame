/** JUnit test for project 5
  * @author Escanord Le */
import org.junit.*;
import static org.junit.Assert.*;

public class Project5Tester
{
  /** Test ChessGame class */
  @Test
  public void testChessGame()
  {
    /* Test constructor and getter methods */
    ChessGame game = new EuropeanChess("NORTH", "SOUTH", null);
    // test getNumRows
    assertEquals("test getNumRows", 8, game.getNumRows());
    // test getNumColumns
    assertEquals("test getNumColumns", 8, game.getNumColumns());
  }
  
  /** Test PalacePiece class */
  @Test
  public void testPalacePiece()
  {
    ChessBoard board = new SwingChessBoard(new SwingEuropeanChessDisplay(), new Xiangqi("NORTH", "SOUTH", null));
    PalacePiece piece = new XiangqiKingPiece("NORTH", 0, 3, board, null);
    // test isLegalPalaceMove method
    assertTrue("test isLegalPalaceMove method", piece.isLegalPalaceMove(0, 4));
    assertTrue("test isLegalPalaceMove method", piece.isLegalPalaceMove(1, 3));
    assertFalse("test isLegalPalaceMove method", piece.isLegalPalaceMove(0, 2));
    assertFalse("test isLegalPalaceMove method", piece.isLegalPalaceMove(1, 2));
    
    piece = new XiangqiKingPiece("SOUTH", 9, 5, board, null);
    assertTrue("test isLegalPalaceMove method", piece.isLegalPalaceMove(9, 4));
    assertTrue("test isLegalPalaceMove method", piece.isLegalPalaceMove(8, 5));
    assertFalse("test isLegalPalaceMove method", piece.isLegalPalaceMove(9, 6));
    assertFalse("test isLegalPalaceMove method", piece.isLegalPalaceMove(8, 6));
    
    board = new SwingChessBoard(new SwingEuropeanChessDisplay(), new Xiangqi("WEST", "EAST", null));
    piece = new XiangqiKingPiece("EAST", 5, 9, board, null);
    assertTrue("test isLegalPalaceMove method", piece.isLegalPalaceMove(4, 9));
    assertTrue("test isLegalPalaceMove method", piece.isLegalPalaceMove(5, 8));
    assertFalse("test isLegalPalaceMove method", piece.isLegalPalaceMove(6, 9));
    assertFalse("test isLegalPalaceMove method", piece.isLegalPalaceMove(6, 8));
    
    piece = new XiangqiKingPiece("WEST", 3, 0, board, null);
    assertTrue("test isLegalPalaceMove method", piece.isLegalPalaceMove(4, 0));
    assertTrue("test isLegalPalaceMove method", piece.isLegalPalaceMove(3, 1));
    assertFalse("test isLegalPalaceMove method", piece.isLegalPalaceMove(2, 0));
    assertFalse("test isLegalPalaceMove method", piece.isLegalPalaceMove(2, 1));
  }
  
  /** Test XiangqiKingPiece class */
  @Test
  public void testXiangqiKingPiece()
  {
    ChessBoard board = new SwingChessBoard(new SwingEuropeanChessDisplay(), new Xiangqi("NORTH", "SOUTH", null));
    XiangqiKingPiece piece1 = new XiangqiKingPiece("NORTH", 0, 4, board, null);
    XiangqiKingPiece piece2 = new XiangqiKingPiece("SOUTH", 9, 5, board, null);
    board.addPiece(piece1, 0, 4);
    board.addPiece(piece2, 9, 5);
    // test isLegalMove method
    assertTrue("test isLegalMove method", piece1.isLegalMove(0, 3));
    assertTrue("test isLegalMove method", piece1.isLegalMove(1, 4));
    assertFalse("test isLegalMove method", piece1.isLegalMove(1, 3));
    assertFalse("test isLegalMove method", piece2.isLegalMove(9, 4));
    assertFalse("test isLegalMove method", piece2.isLegalMove(5, 5));
    assertFalse("test isLegalMove method", piece2.isLegalMove(9, 8));
      // test isFacingKingMove
      {
        // test 0
        assertFalse("test isLegalMove method", piece2.isLegalMove(9, 4));
        // test 1, test first
        board.addPiece(new GuardPiece("NORTH", 1, 4, board, null), 1, 4);
        assertTrue("test isLegalMove method", piece2.isLegalMove(9, 4));
        // test many
        board.addPiece(new GuardPiece("SOUTH", 8, 4, board, null), 8, 4);
        board.addPiece(new SoldierPiece("SOUTH", 5, 4, board, null), 5, 4);
        assertTrue("test isLegalMove method", piece2.isLegalMove(9, 4));
        // test last
        board.removePiece(5, 4);
        assertTrue("test isLegalMove method", piece2.isLegalMove(9, 4));
        // test middle
        board.addPiece(new SoldierPiece("SOUTH", 5, 4, board, null), 5, 4);
        board.removePiece(8, 4);
        board.removePiece(1, 4);
        assertTrue("test isLegalMove method", piece2.isLegalMove(9, 4));
      }
  }
  
  /** Test GuardPiece class */
  @Test
  public void testGuardPiece()
  {
    ChessBoard board = new SwingChessBoard(new SwingEuropeanChessDisplay(), new Xiangqi("NORTH", "SOUTH", null));
    XiangqiKingPiece piece1 = new XiangqiKingPiece("NORTH", 0, 5, board, null);
    XiangqiKingPiece piece2 = new XiangqiKingPiece("SOUTH", 9, 5, board, null);
    GuardPiece piece3 = new GuardPiece("NORTH", 1, 4, board, null);
    GuardPiece piece4 = new GuardPiece("SOUTH", 7, 5, board, null);
    board.addPiece(piece1, 0, 5);
    board.addPiece(piece2, 9, 5);
    board.addPiece(piece3, 1, 4);
    board.addPiece(piece4, 7, 5);
    // test isLegalMove method
    assertTrue("Test isLegalMove method", piece3.isLegalMove(2, 5));
    assertTrue("Test isLegalMove method", piece3.isLegalMove(2, 3));
    assertTrue("Test isLegalMove method", piece3.isLegalMove(0, 3));
    assertFalse("Test isLegalMove method", piece3.isLegalMove(0, 5));
    assertFalse("Test isLegalMove method", piece3.isLegalMove(1, 3));
    assertFalse("Test isLegalMove method", piece3.isLegalMove(1, 5));
    assertFalse("Test isLegalMove method", piece3.isLegalMove(0, 4));
    assertFalse("Test isLegalMove method", piece4.isLegalMove(8, 4));
  }
  
  /** Test ElephantPiece class */
  @Test
  public void testElephantPiece()
  {
    ChessBoard board = new SwingChessBoard(new SwingEuropeanChessDisplay(), new Xiangqi("NORTH", "SOUTH", null));
    ElephantPiece piece = new ElephantPiece("NORTH", 4, 4, board, null);
    // test isLegalMove method
    assertTrue("test isLegalMove method", piece.isLegalMove(2, 2));
    assertTrue("test isLegalMove method", piece.isLegalMove(2, 6));
    assertFalse("test isLegalMove method", piece.isLegalMove(6, 6));
    assertFalse("test isLegalMove method", piece.isLegalMove(6, 2));
    assertFalse("test isLegalMove method", piece.isLegalMove(4, 5));
    assertFalse("test isLegalMove method", piece.isLegalMove(4, 1));
    assertFalse("test isLegalMove method", piece.isLegalMove(0, 4));
    assertFalse("test isLegalMove method", piece.isLegalMove(5, 4));
    
    piece = new ElephantPiece("SOUTH", 6, 6, board, null);
    assertFalse("test isLegalMove method", piece.isLegalMove(4, 4));
    
    piece = new ElephantPiece("WEST", 4, 4, board, null);
    assertFalse("test isLegalMove method", piece.isLegalMove(6, 6));
    
    piece = new ElephantPiece("EAST", 6, 6, board, null);
    assertFalse("test isLegalMove method", piece.isLegalMove(4, 4));
  }
  
  /** Test FacingKingMove interface */
  @Test
  public void testFacingKingMove()
  {
    ChessBoard board = new SwingChessBoard(new SwingEuropeanChessDisplay(), new Xiangqi("NORTH", "SOUTH", null));
    XiangqiKingPiece piece1 = new XiangqiKingPiece("NORTH", 0, 5, board, null);
    XiangqiKingPiece piece2 = new XiangqiKingPiece("SOUTH", 9, 5, board, null);
    GuardPiece piece3 = new GuardPiece("NORTH", 1, 4, board, null);
    GuardPiece piece4 = new GuardPiece("SOUTH", 7, 5, board, null);
    board.addPiece(piece1, 0, 5);
    board.addPiece(piece2, 9, 5);
    board.addPiece(piece3, 1, 4);
    board.addPiece(piece4, 7, 5);
    // test isFacingKingMove method
    assertFalse("test isFacingKingMove method", piece4.isFacingKingMove(piece4, 7, 5, 8, 4));
    assertTrue("test isFacingKingMove method", piece3.isFacingKingMove(piece3, 1, 4, 2, 5));
  }
  
  /** Test HorsePiece class */
  @Test 
  public void testHorsePiece()
  {
    ChessBoard board = new SwingChessBoard(new SwingEuropeanChessDisplay(), new Xiangqi("NORTH", "SOUTH", null));
    HorsePiece piece1 = new HorsePiece("NORTH", 4, 4, board, null);
    XiangqiKingPiece piece2 = new XiangqiKingPiece("NORTH", 4, 5, board, null);
    board.addPiece(piece2, 4, 5);
    // test isLegalMove method
    assertTrue("Test isLegalMove method", piece1.isLegalMove(3, 2));
    assertTrue("Test isLegalMove method", piece1.isLegalMove(5, 2));
    assertFalse("Test isLegalMove method", piece1.isLegalMove(3, 6));
    assertFalse("Test isLegalMove method", piece1.isLegalMove(5, 6));
    assertTrue("Test isLegalMove method", piece1.isLegalMove(2, 3));
    assertTrue("Test isLegalMove method", piece1.isLegalMove(2, 5));
    board.addPiece(new XiangqiKingPiece("NORTH", 3, 4, board, null), 3, 4);
    assertFalse("Test isLegalMove method", piece1.isLegalMove(2, 3));
    assertFalse("Test isLegalMove method", piece1.isLegalMove(2, 5));
    assertTrue("Test isLegalMove method", piece1.isLegalMove(6, 3));
    assertTrue("Test isLegalMove method", piece1.isLegalMove(6, 5));
    board.addPiece(new CannonPiece("NORTH", 5, 4, board, null), 5, 4);
    assertFalse("Test isLegalMove method", piece1.isLegalMove(6, 3));
    assertFalse("Test isLegalMove method", piece1.isLegalMove(6, 5));
  }
  
  /** Test CannonPiece class */
  @Test
  public void testCannonPiece()
  {
    ChessBoard board = new SwingChessBoard(new SwingEuropeanChessDisplay(), new Xiangqi("NORTH", "SOUTH", null));
    CannonPiece piece1 = new CannonPiece("NORTH", 2, 4, board, null);
    CannonPiece piece2 = new CannonPiece("NORTH", 5, 4, board, null);
    CannonPiece piece3 = new CannonPiece("SOUTH", 9, 4, board, null);
    board.addPiece(piece1, 2, 4);
    board.addPiece(piece2, 5, 4);
    board.addPiece(piece3, 9, 4);
    // test isLegalMove method
    assertTrue("test isLegalMove method", piece1.isLegalMove(4, 4));
    assertTrue("test isLegalMove method", piece1.isLegalMove(9, 4));
    assertFalse("test isLegalMove method", piece1.isLegalMove(5, 4));
    assertFalse("test isLegalMove method", piece1.isLegalMove(8, 4));
  }
  
  /** test SoldierPiece class */
  @Test
  public void testSoldierPiece()
  {
    ChessBoard board = new SwingChessBoard(new SwingEuropeanChessDisplay(), new Xiangqi("NORTH", "SOUTH", null));
    SoldierPiece piece1 = new SoldierPiece("NORTH", 2, 4, board, null);
    SoldierPiece piece2 = new SoldierPiece("NORTH", 5, 4, board, null);
    // test isLegalMove
    assertTrue("test isLegalMove method", piece1.isLegalMove(3, 4));
    assertFalse("test isLegalMove method", piece1.isLegalMove(1, 4));
    assertFalse("test isLegalMove method", piece1.isLegalMove(2, 5));
    assertFalse("test isLegalMove method", piece1.isLegalMove(2, 3));
    assertFalse("test isLegalMove method", piece1.isLegalMove(1, 5));
    assertFalse("test isLegalMove method", piece1.isLegalMove(9, 9));
    assertTrue("test isLegalMove method", piece2.isLegalMove(5, 3));
    assertTrue("test isLegalMove method", piece2.isLegalMove(5, 5));
    
    piece1 = new SoldierPiece("SOUTH", 2, 4, board, null);
    piece2 = new SoldierPiece("SOUTH", 5, 4, board, null);
    assertTrue("test isLegalMove method", piece2.isLegalMove(4, 4));
    assertFalse("test isLegalMove method", piece2.isLegalMove(6, 4));
    assertFalse("test isLegalMove method", piece2.isLegalMove(5, 5));
    assertFalse("test isLegalMove method", piece2.isLegalMove(5, 3));
    assertFalse("test isLegalMove method", piece2.isLegalMove(6, 5));
    assertFalse("test isLegalMove method", piece2.isLegalMove(9, 9));
    assertTrue("test isLegalMove method", piece1.isLegalMove(2, 3));
    assertTrue("test isLegalMove method", piece1.isLegalMove(2, 5));
    
    piece1 = new SoldierPiece("WEST", 4, 2, board, null);
    piece2 = new SoldierPiece("WEST", 4, 5, board, null);
    assertTrue("test isLegalMove method", piece1.isLegalMove(4, 3));
    assertFalse("test isLegalMove method", piece1.isLegalMove(4, 1));
    assertFalse("test isLegalMove method", piece1.isLegalMove(5, 2));
    assertFalse("test isLegalMove method", piece1.isLegalMove(3, 2));
    assertFalse("test isLegalMove method", piece1.isLegalMove(5, 1));
    assertFalse("test isLegalMove method", piece1.isLegalMove(9, 9));
    assertTrue("test isLegalMove method", piece2.isLegalMove(3, 5));
    assertTrue("test isLegalMove method", piece2.isLegalMove(5, 5));
    
    piece1 = new SoldierPiece("EAST", 4, 2, board, null);
    piece2 = new SoldierPiece("EAST", 4, 5, board, null);
    assertTrue("test isLegalMove method", piece2.isLegalMove(4, 4));
    assertFalse("test isLegalMove method", piece2.isLegalMove(4, 6));
    assertFalse("test isLegalMove method", piece2.isLegalMove(5, 5));
    assertFalse("test isLegalMove method", piece2.isLegalMove(3, 5));
    assertFalse("test isLegalMove method", piece2.isLegalMove(5, 6));
    assertFalse("test isLegalMove method", piece2.isLegalMove(9, 9));
    assertTrue("test isLegalMove method", piece1.isLegalMove(3, 2));
    assertTrue("test isLegalMove method", piece1.isLegalMove(5, 2));
  }
}