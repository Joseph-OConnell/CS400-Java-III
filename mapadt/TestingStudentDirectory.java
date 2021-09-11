// --== CS400 File Header Information ==--
// Name: Joseph O'Connell
// Email: jpoconnell2@wisc.edu
// Team: FB
// Role: Test Engineer 2
// TA: Abhay Kumar
// Lecturer: Gary Dahl
// Notes to Grader: 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * This class tests the Student directory class and the methods and classes it extends
 * 
 * @author Joseph O'Connell
 *
 */
public class TestingStudentDirectory {

  /**
   * Load Data takes a reference to a studentdirectory and adds the data for 30 students
   * 
   * @param map StudentDirectory holds the studentBlock objects
   * @throws FileNotFoundException if path to the student information file is not found
   */
  public static void loadData(StudentDirectory map) throws FileNotFoundException {
    File data = new File("studentData.txt");
    Scanner scnr = new Scanner(data);

    String firstName;
    String lastName;
    String email;

    while (scnr.hasNextLine()) {
      if (scnr.hasNext()) {
        firstName = scnr.next();
        lastName = scnr.next();
        email = scnr.next();
        StudentDirectory.addInfo(firstName + " " + lastName, email);
      }
    }
  }

  /**
   * Tests the proper loading and entry of StudentBlocks into the directory Tests other methods as
   * well to confirm entries were added.
   * 
   * @return true if test passes, false if it fails
   */
  public static boolean testLoad() {
    StudentDirectory map = new StudentDirectory();
    try {
      loadData(map);
    } catch (FileNotFoundException e) {
      System.out.println(e);
    }

    // test number of students in map
    if (map.entriesInDirectory() != 30) {
      return false;
    }

    // test a couple students from the map
    if (!map.entryCheck("Phil Swanson")) {
      return false;
    }

    if (!map.entryCheck("Andrea Lara")) {
      return false;
    }


    // try to retrieve student
    if (!map.retrieveEntry("Andrea Lara").getInfo().equals("alara@wisc.edu")) {
      return false;
    }


    return true;
  }

  /**
   * Tests the functions within the Student Object block
   * 
   * @return true if test passes, false if it fails
   */
  public static boolean testStudentObject() {
    StudentBlock testStudent = new StudentBlock("Alexa Johnson", "ajohnson@wisc.edu");

    // test get first
    if (!testStudent.getFirst().equals("Alexa")) {
      return false;
    }

    // test get last
    if (!testStudent.getLast().equals("Johnson")) {
      return false;
    }

    // test get info
    if (!testStudent.getInfo().equals("ajohnson@wisc.edu")) {
      return false;
    }

    // test change info
    testStudent.changeInfo("wrong email");

    if (!testStudent.getInfo().equals("wrong email")) {
      return false;
    }

    return true;
  }

  /**
   * This method tests both constructors for the student directory class.
   * 
   * @return true if test passes, false if it fails
   */
  public static boolean testStudentDirectory() {
    // test default
    StudentDirectory map1 = new StudentDirectory();
    try {
      loadData(map1);
    } catch (FileNotFoundException e) {
      System.out.println(e);
    }
    if (map1.entriesInDirectory() != 30) {
      return false;
    }

    // test normal
    StudentDirectory map2 = new StudentDirectory();
    try {
      loadData(map2);
    } catch (FileNotFoundException e) {
      System.out.println(e);
    }
    if (map2.entriesInDirectory() != 30) {
      return false;
    }
    return true;
  }

  /**
   * Tests that addInfo() properly creates and adds studentblocks to the directory. Tests that
   * students with the same name will not be added.
   * 
   * @return true if test passes, false if it fails
   */
  public static boolean testAddInfo() {
    StudentDirectory map = new StudentDirectory();
    if (!map.addInfo("Samantha Morris", "smorris@wisc.edu")) {
      return false;
    }
    if (map.entriesInDirectory() != 1) {
      return false;
    }

    if (map.addInfo("Samantha Morris", "smorris@wisc.edu")) {
      return false;
    }

    if (map.entriesInDirectory() != 1) {
      return false;
    }
    return true;
  }

  /**
   * Tests if retrieveEntry() properly returns a student block. Tests that retrieving a nonexistent
   * student returns null
   * 
   * @return true if test passes, false if it fails
   */
  public static boolean testRetrieveEntry() {
    StudentDirectory map = new StudentDirectory();
    try {
      loadData(map);
    } catch (FileNotFoundException e) {
      System.out.println(e);
    }

    StudentBlock testStudent = map.retrieveEntry("Olivia Coutre");
    if (!testStudent.getFirst().equals("olivia")) {
      return false;
    }

    StudentBlock testStudent1 = map.retrieveEntry("fake name");
    if (testStudent1 != null) {
      return false;
    }
    return true;
  }

  /**
   * Tests that students were properly removed from the directory. Tests that the remove method
   * returns the student object that was removed. Tests that remove will return null if trying to
   * remove a nonexistent student
   * 
   * @return true if test passes, false if it fails
   */
  public static boolean testRemove() {
    StudentDirectory map = new StudentDirectory();
    try {
      loadData(map);
    } catch (FileNotFoundException e) {
      System.out.println(e);
    }

    map.remove("Olivia Coutre");
    if (map.retrieveEntry("fake name") != null) {
      return false;
    }

    if (map.entriesInDirectory() != 29) {
      return false;
    }

    if (map.entryCheck("Olivia Coutre")) {
      return false;
    }

    return true;
  }

  /**
   * Tests that the clear method removes all entries from the directory
   * 
   * @return true if test passes, false if it fails
   */
  public static boolean testClear() {
    StudentDirectory map = new StudentDirectory();
    try {
      loadData(map);
    } catch (FileNotFoundException e) {
      System.out.println(e);
    }

    if (map.entriesInDirectory() != 30) {
      return false;
    }

    map.clear();
    if (map.entriesInDirectory() != 0) {
      return false;
    }
    return true;
  }

  /**
   * tests that EntryCheck() returns true if a name is in the directory False if it is not.
   * 
   * @return true if test passes, false if it fails
   */
  public static boolean testEntryCheck() {
    StudentDirectory map = new StudentDirectory();
    try {
      loadData(map);
    } catch (FileNotFoundException e) {
      System.out.println(e);
    }

    if (!map.entryCheck("Olivia Coutre")) {
      return false;
    }

    if (map.entryCheck("fake name")) {
      return false;
    }
    return true;
  }

  /**
   * Tests that the method returns the proper number of students in the directory.
   * 
   * @return true if test passes, false if it fails
   */
  public static boolean testEntriesInDirectory() {
    StudentDirectory map = new StudentDirectory();

    if (map.entriesInDirectory() != 0) {
      return false;
    }

    try {
      loadData(map);
    } catch (FileNotFoundException e) {
      System.out.println(e);
    }

    if (map.entriesInDirectory() != 30) {
      return false;
    }
    return true;
  }


  /**
   * Tests all of the methods listed above.
   * 
   */
  public static void main(String[] args) {
    if (testLoad()) {
      System.out.println("Loading Student Data passed tests");
    } else {
      System.out.println("Loading Student Data failed tests");
    }

    if (testStudentObject()) {
      System.out.println("Student Object passed tests");
    } else {
      System.out.println("Student Object failed tests");
    }

    if (testStudentDirectory()) {
      System.out.println("Student Directory passed tests");
    } else {
      System.out.println("Student Directory failed tests");
    }

    if (testAddInfo()) {
      System.out.println("Add Info passed tests");
    } else {
      System.out.println("Add Info failed tests");
    }

    if (testRetrieveEntry()) {
      System.out.println("Retrieve Entry passed tests");
    } else {
      System.out.println("Retrieve Entry failed tests");
    }

    if (testRemove()) {
      System.out.println("Remove passed tests");
    } else {
      System.out.println("Remove failed tests");
    }

    if (testClear()) {
      System.out.println("Clear passed tests");
    } else {
      System.out.println("Clear failed tests");
    }

    if (testEntryCheck()) {
      System.out.println("Entry Check passed tests");
    } else {
      System.out.println("Entry Check failed tests");
    }

    if (testEntriesInDirectory()) {
      System.out.println("Entries in directory passed tests");
    } else {
      System.out.println("Entries in directory failed tests");
    }

  }

}
