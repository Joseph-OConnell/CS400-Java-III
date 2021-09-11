// --== CS400 File Header Information ==--
// Name: Joseph O'Connell
// Email: jpoconnell2@wisc.edu
// Team: FB
// Role: Test Engineer 2
// TA: Abhay Kumar
// Lecturer: Gary Dahl
// Notes to Grader: 

public class HashNode<KeyType, ValueType> {
  public KeyType key;
  public ValueType value;
  
  /**
   * Constructor to create key value pairs to be used in the linked list
   * @param keyType
   * @param value ValueType
   */
  public HashNode(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
  }
}
