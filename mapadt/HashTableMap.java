// --== CS400 File Header Information ==--
// Name: Joseph O'Connell
// Email: jpoconnell2@wisc.edu
// Team: FB
// Role: Test Engineer 2
// TA: Abhay Kumar
// Lecturer: Gary Dahl
// Notes to Grader: 
import java.util.NoSuchElementException;
import java.util.LinkedList;

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  private LinkedList<HashNode<KeyType, ValueType>>[] map;
  private int size;

  // used in the grow() as a temp storage array
  private LinkedList<HashNode<KeyType, ValueType>>[] tempMap;

  @SuppressWarnings("unchecked")
  /**
   * constructor with specified number of slots
   * 
   * @param capacity number of slots for hashcodes
   */
  public HashTableMap(int capacity) {
    map = new LinkedList[capacity];
    size = 0;
  }


  @SuppressWarnings("unchecked")
  /**
   * default constructor with 10 slots as default capacity
   */
  public HashTableMap() { // with default capacity = 10
    map = new LinkedList[10];
    size = 0;
  }

  /**
   * Double and rehash when greater than or equal to 80%
   */
  @SuppressWarnings("unchecked")
  private void grow() {
    tempMap = map;
    map = new LinkedList[(2 * map.length)];
    size = 0;
    for (int i = 0; i < tempMap.length; i++) {
      if (tempMap[i] != null) {
        for (int j = 0; j < tempMap[i].size(); j++) {
          put(tempMap[i].get(j).key, tempMap[i].get(j).value);
        }
      }
    }
  }
  /**
   * 
   * @return double value of the load capacity ratio
   */
  private double loadCapacity() {

    return ((double) size) / ((double) map.length);
  }

  /**
   * calls the load capacity fcn
   * @return true if hash table needs to be expanded. false if it is fine.
   */
  private boolean checkLoadCapacity() {
    if (loadCapacity() >= .8) {
      return true;
    }
    return false;
  }

  @Override
  /**
   * Adds a key value pair to the map
   * @return true if the value is successfully added.  False if not
   */
  public boolean put(KeyType key, ValueType value) {
    // because the array can grow, we have to use a nested for loop to find duplicates

    // first hash the key value and mod by capacity
    int index = Math.abs(key.hashCode() % map.length);

    // search the map for index. If null, create new HashNode and add to linked list at pt
    if (map[index] == null) {
      map[index] = new LinkedList<HashNode<KeyType, ValueType>>();
      map[index].add(new HashNode<KeyType, ValueType>(key, value));
      this.size++;
      if (checkLoadCapacity()) {
        grow();
      }
      return true;
    }
    // there is already a linked list at the point
    int listSize = map[index].size();
    for (int i = 0; i < listSize; i++) {
      if (map[index].get(i).key.equals(key)) {
        return false;
      }
    }
    // key is not already in linked list at this point. Add to list
    map[index].add(new HashNode<KeyType, ValueType>(key, value));
    this.size++;
    if (checkLoadCapacity()) {
      grow();
    }
    return true;

  }

  @Override
  /**
   * Searchs through the map to find the associated value with the argument key.
   * Throws an error if there is no key in the map.  
   * @throws NoSuchElementException when the key is not in the map
   * @return ValueType value of the key that we were searching
   */
  public ValueType get(KeyType key) throws NoSuchElementException {
    if (!containsKey(key)) {
      throw new NoSuchElementException("Key is not in HashMap");
    }
    // key is in HashMap at this point
    // first hash the key value and mod by capacity
    int index = Math.abs(key.hashCode() % map.length);

    // iterate through linkedList at index
    int listSize = map[index].size();
    for (int i = 0; i < listSize; i++) {
      if (map[index].get(i).key.equals(key)) {
        return map[index].get(i).value;
      }
    }
    // return this just in case
    return null;
  }

  @Override
  /**
   * returns private int size
   * @return size  int value of the number of K,V pairs in the map
   */
  public int size() {
    return size;
  }

  @Override
  /**
   * looks for a key and returns true or false
   * @return true when the key is in the map, false if it is not
   */
  public boolean containsKey(KeyType key) {
    // first hash the key value and mod by capacity
    int index = Math.abs(key.hashCode() % map.length);

    // search the map for index. If null return false. key is not there.
    if (map[index] == null) {
      return false;
    }
    // if we reach this point there is a linked list at the index
    // step through all points and return true for match. False for no match
    int listSize = map[index].size();
    for (int i = 0; i < listSize; i++) {
      if (map[index].get(i).key.equals(key)) {
        return true;
      }
    }
    return false;
  }

  @Override
  /**
   * Removes a K,V pair based on the argument key
   * @return the value associated with the argument key
   */
  public ValueType remove(KeyType key) {
    // first hash the key value and mod by capacity
    int index = Math.abs(key.hashCode() % map.length);
    // search the map for index. If null return null. key is not there.
    if (map[index] == null) {
      return null;
    }
    // if we reach this point there is a linked list at the index
    // step through all points and return true for match. False for no match
    int listSize = map[index].size();
    for (int i = 0; i < listSize; i++) {
      if (map[index].get(i).key.equals(key)) {
        HashNode<KeyType, ValueType> tempHashNode = map[index].get(i);
        map[index].remove(i);
        this.size--;
        return tempHashNode.value;
      }
    }
    return null;
  }

  @Override
  /**
   * removes all key value pairs from the map
   */
  public void clear() {
    for (int i = 0; i < map.length; i++) {
      map[i] = null;
    }
    size = 0;
  }

  /**
   * Used purely for testing
   * 
   * @return int number of slots in the map
   */
  public int getCapacity() {
    return map.length;
  }



}
