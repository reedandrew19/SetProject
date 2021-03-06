import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class TableTest extends TestCase {
  
  /**
   * A test method.
   * (Replace "X" with a name describing the test.  You may write as
   * many "testSomething" methods in this class as you wish, and each
   * one will be called when running JUnit over this class.)
   */
  public void testEmptyTable()
  {
    Table table = new Table();

    assertEquals(null, table.getCard(0));
    assertEquals(0, table.numCards());
  }
  
  public void testTableOne()
  {
    Table table = new Table();
    Card card = new Card(1, 1, 1, 1);
    table.add(card);
    
    assertEquals(true, table.getCard(0).equals(card));
    assertEquals(1, table.numCards());
    assertEquals(null, table.getCard(1));
    assertEquals(null, table.getCard(4));
  }
  
  public void testTableTwoCards()
  {
    Table table = new Table();
    Card card1 = new Card(1, 1, 1, 1);
    Card card2 = new Card(1, 1, 1, 2);
    table.add(card1);
    table.add(card2);
    
    assertEquals(true, table.getCard(0).equals(card2));
    assertEquals(true, table.getCard(1).equals(card1));
    assertEquals(null, table.getCard(2));
    assertEquals(null, table.getCard(33));
    assertEquals(2, table.numCards());
  }
  
  public void testTableManyCards()
  {
    Table table = new Table();
    Card card1 = new Card(1, 1, 1, 1);
    Card card2 = new Card(1, 1, 1, 2);
    Card card3 = new Card(1, 1, 1, 3);
    Card card4 = new Card(1, 1, 2, 1);
    Card card5 = new Card(1, 1, 2, 2);
    Card card6 = new Card(1, 1, 2, 3);
    
    table.add(card1);
    table.add(card2);
    table.add(card3);
    table.add(card4);
    table.add(card5);
    table.add(card6);
    
    assertEquals(6, table.numCards());
    assertEquals(true, table.getCard(0).equals(card6));
    assertEquals(true, table.getCard(1).equals(card5));
    assertEquals(true, table.getCard(2).equals(card4));
    assertEquals(true, table.getCard(3).equals(card3));
    assertEquals(true, table.getCard(4).equals(card2));
    assertEquals(true, table.getCard(5).equals(card1));
  }
  
  public void testNumSetsNone()
  {
    Table table = new Table();
   
    assertEquals(0, table.numSets());
    table.add(new Card(1, 1, 1, 1));
    
    assertEquals(0, table.numSets());
    table.add(new Card(1, 1, 1, 2));
    
    assertEquals(0, table.numSets());
    table.add(new Card(2, 1, 1, 2));
    
    assertEquals(0, table.numSets());
    table.add(new Card(3, 2, 2, 1));
    assertEquals(0, table.numSets());
    table.add(new Card(3, 2, 3, 2));
    assertEquals(0, table.numSets());
  }
  
  public void testNumSetsOne()
  {
    Table table = new Table();
    table.add(new Card(1, 1, 1, 1));
    table.add(new Card(1, 1, 1, 2));
    table.add(new Card(1, 1, 1, 3));
    assertEquals(1, table.numSets());
    
    table.add(new Card(3, 1, 1, 1));
    assertEquals(1, table.numSets());
    table.add(new Card(3, 1, 1, 2));
    assertEquals(1, table.numSets());
  }
  
  public void testNumSetsTwo()
  {
    Table table = new Table();
    table.add(new Card(1, 1, 1, 1));
    table.add(new Card(1, 1, 1, 2));
    table.add(new Card(1, 1, 1, 3));
    table.add(new Card(3, 1, 1, 1));
    table.add(new Card(2, 1, 1, 1));
    
    assertEquals(2, table.numSets());
    table.add(new Card(1, 1, 2, 3));
    table.add(new Card(1, 1, 3, 3));
    assertEquals(3, table.numSets());
  }
  
  public void testRemoveSetNotSet()
  {
    Table table = new Table();
    Card card1 = new Card(1, 1, 1, 1);
    Card card2 = new Card(1, 1, 1, 2);
    Card card3 = new Card(2, 1, 1, 1);
   
    table.add(card1);
    table.add(card2);
    table.add(card3);
   
    assertEquals(3, table.numCards());
    
    table.removeSet(card1, card2, card3);
   
    assertEquals(3, table.numCards());
  }
  
  public void testRemoveSetAtTail()
  {
    Table table = new Table();
    
    Card card1 = new Card(1, 1, 1, 1);
    Card card2 = new Card(1, 1, 1, 2);
    Card card3 = new Card(1, 1, 1, 3);
    Card card4 = new Card(2, 2, 2, 2);
    
    table.add(card1);
    table.add(card2);
    table.add(card3);
    table.add(card4);
    
    assertEquals(4, table.numCards());
    
    table.removeSet(card1, card2, card3);
    
    assertEquals(1, table.numCards());
    assertEquals(true, table.getCard(0).equals(card4));
  }
  
  public void testRemoveSetAtHead()
  {
    Table table = new Table();
    Card card1 = new Card(2, 2, 2, 2);
    Card card2 = new Card(1, 1, 1, 1);
    Card card3 = new Card(1, 1, 1, 2);
    Card card4 = new Card(1, 1, 1, 3);
    
    table.add(card1);
    table.add(card2);
    table.add(card3);
    table.add(card4);
    
    assertEquals(4, table.numCards());
    
    table.removeSet(card2, card3, card4);
    
    assertEquals(1, table.numCards());
    assertTrue(table.getCard(0).equals(card1));
  }
  
  public void testRemoveSetNotInTable()
  {
    Table table = new Table();
    Card card1 = new Card(1, 1, 1, 1);
    Card card2 = new Card(1, 1, 1, 2);
    Card card3 = new Card(1, 1, 1, 3);
    Card card4 = new Card(2, 2, 2, 2);
   
    assertEquals(0, table.numCards());
    
    table.removeSet(card1, card2, card3);
   
    assertEquals(0, table.numCards());
   
    table.add(card1);
    assertEquals(1, table.numCards());
    table.removeSet(card1, card2, card3);
   
    assertEquals(1, table.numCards());
   
    table.add(card2);
    assertEquals(2, table.numCards());
    table.removeSet(card1, card2, card3);
   
    table.add(card4);
    assertEquals(3, table.numCards());
    table.removeSet(card1, card2, card3);
   
    assertEquals(3, table.numCards());
  }
  
  public void testRemoveSetTailHeadAndMiddle()
  {
    Table table = new Table();
    Card card1 = new Card(1, 1, 1, 1);
    Card card2 = new Card(1, 1, 1, 2);
    Card card3 = new Card(1, 1, 1, 3);
    Card card4 = new Card(2, 2, 2, 2);
    Card card5 = new Card(2, 1, 2, 2);
   
    table.add(card1);
    table.add(card4);
    table.add(card2);
    table.add(card5);
    table.add(card3);
   
    assertEquals(5, table.numCards());
   
    table.removeSet(card1, card2, card3);
   
    assertEquals(2, table.numCards());
    assertEquals(true, table.getCard(0).equals(card5));
    assertEquals(true, table.getCard(1).equals(card4));
  }
  
  public void testRemoveSetThreeCardTable()
  {
    Table table = new Table();
    Card card1 = new Card(1, 1, 1, 1);
    Card card2 = new Card(1, 1, 1, 2);
    Card card3 = new Card(1, 1, 1, 3);
    
    table.add(card1);
    table.add(card2);
    table.add(card3);
    
    assertEquals(3, table.numCards());
    
    table.removeSet(card1, card2, card3);
    assertEquals(0, table.numCards());
  }
  
  public void testRemoveSetAllCardsMiddle()
  {
    Table table = new Table();
    Card card1 = new Card(1, 1, 1, 1);
    Card card2 = new Card(1, 1, 1, 2);
    Card card3 = new Card(1, 1, 1, 3);
    Card card4 = new Card(2, 2, 2, 2);
    Card card5 = new Card(2, 1, 2, 2);
   
    table.add(card5);
    table.add(card1);
    table.add(card2);
    table.add(card3);
    table.add(card4);
   
    assertEquals(5, table.numCards());
   
    table.removeSet(card1, card2, card3);
   
    assertEquals(2, table.numCards());
    assertEquals(true, table.getCard(0).equals(card4));
    assertEquals(true, table.getCard(1).equals(card5));
  }
  
  public void testRemoveSetAllSeperatedMiddle()
  {
    Table table = new Table();
    Card card1 = new Card(1, 1, 1, 1);
    Card card2 = new Card(1, 1, 1, 2);
    Card card3 = new Card(1, 1, 1, 3);
    Card card4 = new Card(2, 2, 2, 2);
    Card card5 = new Card(2, 1, 2, 2);
    Card card6 = new Card(2, 3, 2, 3);
    Card card7 = new Card(3, 1, 3, 2);
    
    table.add(card7);
    table.add(card1);
    table.add(card6);
    table.add(card2);
    table.add(card5);
    table.add(card3);
    table.add(card4);
    
    assertEquals(7, table.numCards());
    
    table.removeSet(card1, card2, card3);
    
    assertEquals(4, table.numCards());
    assertTrue(table.getCard(0).equals(card4));
    assertTrue(table.getCard(1).equals(card5));
    assertTrue(table.getCard(2).equals(card6));
    assertTrue(table.getCard(3).equals(card7));
  }
}
