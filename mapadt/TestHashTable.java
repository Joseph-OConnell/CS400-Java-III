// --== CS400 File Header Information ==--
// Name: Joseph O'Connell
// Email: jpoconnell2@wisc.edu
// Team: FB
// Role: Test Engineer 2
// TA: Abhay Kumar
// Lecturer: Gary Dahl
// Notes to Grader: 
import java.util.NoSuchElementException;

public class TestHashTable {
  
  /**
   * Test ability to add primitive types to a HashTable.
   * Test ability to remove values
   * Test ability to get values
   * Check size
   * @return true if passes.  false if fails
   */
  public static boolean test1() {
    
    //create map and nodes
    HashTableMap<Integer, Integer> Map1 = new HashTableMap<Integer, Integer>();
    
    Map1.put(100, 12);
    Map1.put(102, 15);
    Map1.put(1444, 157);
    
    // testing put
    if(!Map1.put(14, 138))
      return false;
    
    //test size and put
    if(Map1.size() != 4)
      return false;
    // tests remove
    if(Map1.remove(14) != 138)
      return false;
    //tests size
    if(Map1.size() != 3)
      return false;
    // tests get
    if(Map1.get(1444) != 157)
      return false;
    
    return true;
  }
  
  /**
   * Test ability to add reference types to a HashTable.
   * Test ability to remove values
   * Test ability to get values
   * @return true if passes.  false if fails
   */
  public static boolean test2() {
    
    //create map and nodes
    HashTableMap<String, String> Map1 = new HashTableMap<String, String>();
    
    Map1.put("apple", "carrot");
    Map1.put("banana", "lettuce");
    Map1.put("pear", "squash");
    
    //testing put
    if(!Map1.put("peach", "kale"))
      return false;
    
    //test size and put
    if(Map1.size() != 4)
      return false;
    // tests remove
    if(!Map1.remove("pear").equals("squash"))
      return false;
    //tests size
    if(Map1.size() != 3)
      return false;
    // tests get
    if(!Map1.get("banana").equals("lettuce"))
      return false;
    
    return true;    
  }
  
  /**
   * Test ability to grow and rehash the array
   * @return true if passes.  false if fails
   */
  public static boolean test3() {
HashTableMap<Integer, Integer> Map1 = new HashTableMap<Integer, Integer>();
    
    Map1.put(100, 12);
    Map1.put(102, 15);
    Map1.put(1444, 157);
    Map1.put(14, 138);
    Map1.put(107, 12);
    Map1.put(103, 15);
    Map1.put(1448, 157);
    
    if(Map1.getCapacity() != 10)
      return false;
    
    Map1.put(13, 138);
    
    if(Map1.getCapacity() != 20) {
      return false;
    }
    
    return true;
    
  }
  
  /**
   * Tests containsKey()
   * Tries to get a key that does not exist
   * Tries to add a key that already exists
   * @return true if passes.  false if fails
   */
  public static boolean test4() {
  //create map and nodes
    HashTableMap<Integer, Integer> Map1 = new HashTableMap<Integer, Integer>();
    
    Map1.put(100, 12);
    Map1.put(102, 15);
    Map1.put(1444, 157);
    Map1.put(14, 138);
    
    //testing get() on a key that does not exist
    int r = 0;
    try{
      Map1.get(12);
    }
    catch(NoSuchElementException e) {
      r = 2;
    }
    if(r != 2)
      return false;
    
    //try to add a key that already exists
    if(Map1.put(14, 154))
      return false;
    
    //test size for bad put fcn
    if(Map1.size() != 4)
      return false;
    
    return true;
  }
  /**
   * tests contain key again
   * Test the clear function
   * @return true if passes.  false if fails
   */
  public static boolean test5() {
    
  //create map and nodes
    HashTableMap<Integer, Integer> Map1 = new HashTableMap<Integer, Integer>();
    
    Map1.put(100, 12);
    Map1.put(102, 15);
    Map1.put(1444, 157);
    Map1.put(14, 138);    
    
    // tests clear
    Map1.clear();
    
    
    //tests size
    if(Map1.size() != 0)
      return false;
    
    //testing for keys that should not be present
    int r = 0;
    try{
      Map1.get(14);
    }
    catch(NoSuchElementException e) {
      r = 2;
    }
    if(r != 2)
      return false;
    
    //contains key
    if(Map1.containsKey(102))
      return false;
    return true;
    
  }
  
  
  
  
  public static void main(String[] args) {
    if(test1())
      System.out.println("test 1 passed.  Integer Map Successful");
    else
      System.out.println("test 1 failed.  Integer Map Failed");
    
    if(test2())
      System.out.println("test 2 passed.  String Map Successful");
    else
      System.out.println("test 2 failed.  String Map Failed");
    
    if(test3())
      System.out.println("test 3 passed.  Array Grows and rehashes properly");
    else
      System.out.println("test 3 failed.  Array does not grow or rehash properly");
    
    if(test4())
      System.out.println("test 4 passed.  Returns valid output when trying to add 2 of same key"
          + " or when trying to find nonexistent key");
    else
      System.out.println("test 4 failed.  Returns bad output when trying to add 2 of same key"
          + " or when trying to find nonexistent key");
    
    if(test5())
      System.out.println("test 5 passed.  The Clear function works");
    else
      System.out.println("test 5 failed  The Clear function does not work");
  }
}
