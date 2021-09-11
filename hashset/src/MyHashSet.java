// --== CS400 File Header Information ==--
// Name: Joseph O'Connell
// Email: jpoconnell2@wisc.edu 
// Lecturer: Gary Dahl
import java.util.Hashtable;

public class MyHashSet<T> implements SetADT<T>{
  private Hashtable<T,T> myTable = new Hashtable<T, T>();
  
  @Override
  public boolean add(T el) throws IllegalArgumentException{
    if(this.contains(el))
      return false;
    myTable.put(el, el);
    return this.contains(el);
  }

  @Override
  public boolean contains(T el){
    return myTable.contains(el);
  }

  @Override
  public boolean remove(T el) {
    if(this.contains(el)) {
      myTable.remove(el);
      return true;
    }
    return false;
  }
  
  public static void main(String args[]) {
    MyHashSet<Integer> table = new MyHashSet<Integer>();
    
    System.out.println(table.add(1));         // true
    System.out.println(table.contains(1));    // true
    System.out.println(table.add(1));         // false
    System.out.println(table.contains(1));    // true
    System.out.println(table.remove(1));      // true
    System.out.println(table.contains(1));    // false;
    
    
  }
}
