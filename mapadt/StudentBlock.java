// --== CS400 File Header Information ==--
// Name: Joseph O'Connell
// Email: jpoconnell2@wisc.edu
// Team: FB
// Role: Test Engineer 2
// TA: Abhay Kumar
// Lecturer: Gary Dahl
// Notes to Grader: 
// --== CS400 File Header Information ==--
// Name: Mark Stout
// Email: mnstout@wisc.edu
// Team: FB
// TA: Abhay Kumar
// Lecturer: Florian
// Notes to Grader: <This is an object used in the group project to hold information of each person>
import java.util.Scanner;


/*
 * This object is used to store the name, information, and the hash location (for program use). These objects 
 * are also used is the hash table as each location.
 * 
 * @Written by Mark Stout
 */
public class StudentBlock {
  String name;
  String firstName;
  String lastName;
  String information;

  /*
   * Constructor that uses a full name and information to create a studentBlock object. Also uses a call
   * to the hashFunction method to find the hash value.
   * 
   * @Written by Mark Stout
   */
  public StudentBlock(String name, String information) {
    this.name = name;
    Scanner scanner = new Scanner(name);
    firstName = scanner.next();
    lastName = scanner.next();
    this.information = information;
    scanner.close();
  }
  
  //Returns the first name of the object
  public String getFirst() {
    return firstName;
  }
  
  //Returns the last name of the object
  public String getLast() {
    return lastName;
  }
  
  //Returns the user information in the object
  public String getInfo() {
    return information;
  }
  
  //Used to alter the information in the object (Changing the email)
  public void changeInfo(String newInfo) {
    this.information = newInfo;
  }
}
