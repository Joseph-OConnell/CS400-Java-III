// --== CS400 File Header Information ==--
// Name: Joseph O'Connell
// Email: jpoconnell2@wisc.edu
// Team: FB
// Role: Test Engineer 2
// TA: Abhay Kumar
// Lecturer: Gary Dahl
// Notes to Grader: 
import java.util.Scanner;
import java.util.NoSuchElementException;

public class StudentDirectory {
	  static HashTableMap<String, StudentBlock> directory;            //FIXME, change HashTableMap to your hashTable's method name
	  
	  /*
	   * Constructor that uses a user chosen size for the directory, creates a new hash table and saves the reference
	   * @author Mark Stout
	   */
	  public StudentDirectory(int capacity) {
	    directory = new HashTableMap<String, StudentBlock>(capacity);
	  }
	  
	  /*
	   * Constructor that uses a default size (20) for the directory, creates a new hash table and saves the reference
	   * @author Mark Stout
	   */
	  public StudentDirectory() {
	    directory = new HashTableMap<String, StudentBlock>(20);
	  }
	  
	  /*
	   * Takes the users name and info and adds it to the hashTable if possible. First checks if the name is already
	   * in the directory, if not, then adds it to it.
	   * 
	   * @return false : If the put command failed or the name was already in the file
	   * @return true  : If the studentBlock was successfully added
	   * @author Mark Stout
	   */
	  public static boolean addInfo(String name, String info) {
	    name = name.toLowerCase();
	    
	    if (directory.containsKey(name)) {
	      return false;
	    }
	    
	    StudentBlock newStudent = new StudentBlock(name, info);
	    name = name.toLowerCase();
	    return (directory.put(name, newStudent));
	  }
	  
	  /*
	   * Returns the entry searched for by name
	   * 
	   * @return foundStudent : returned the studentBlock when found
	   * @return null : studentBlock not found, so null returned.
	   * @author Mark Stout
	   */
	  public static StudentBlock retrieveEntry(String name) {
	    name = name.toLowerCase();
	    try {
	      StudentBlock foundStudent = (StudentBlock) directory.get(name);
	      return foundStudent;
	    } catch (NoSuchElementException e) {
	      return null;
	    }
	  }
	  
	  /*
	   * Removes the studentBlock keyed to.
	   * 
	   * @return studentBlock : Successfully removed entry
	   * @return null : When a student is not found at name, returns null
	   * @author Mark Stout
	   */
	  public static StudentBlock remove(String name) {
	    name = name.toLowerCase();
	    return (StudentBlock) directory.remove(name);
	  }
	  
	  /*
	   * Clears the directory
	   * @author Mark Stout
	   */
	  public static void clear() {
	    directory.clear();
	  }
	  
	  /*
	   * Checks the directory for the reference
	   * 
	   * @return true : entry found
	   * @return false : entry not found
	   * @author Mark Stout
	   */
	  public static boolean entryCheck(String name) {
	    name = name.toLowerCase();
	    return directory.containsKey(name);
	  }
	  
	  /*
	   * Returns the size of the directory
	   * @author Mark Stout
	   */
	  public static int entriesInDirectory() {
	    return directory.size();
	  }
	  
	
	/* Method that processes the user's character command, prompts user for more information if necessary,
	 * and makes calls to the appropriate back-end method to carry out each operation.  All user inputs
	 * are also validated within this method.  Returns to command prompt after operation is processed.
	 * 	
	 * @param command - user input Character passed from commandPrompt()
	 * @author Cristian Sanchez
	 */
	public static void userCommands(Character command) {
		
		String name;
		String[] nameArray;
		String email;
		String[] emailArray;
		Scanner scnr = new Scanner(System.in);
		
		boolean addStatus;
		StudentBlock searchBlock;
		StudentBlock removeBlock;
		int size;
		
		switch(command) {
			
			// Add Functionality
			case('a'):
				System.out.println("Adding a Student to the Directory:");
				System.out.println("Enter the first and last name of the student separated by a space.");
				
				// Separates words in user input into a String array
				name = scnr.nextLine();
				nameArray = name.split("[ ]+");
				// Checks for correct # of 'names'
				if(nameArray.length == 2) {
					// Keep prompting the user until they give a valid email input
					do {
						System.out.println("Enter this Student's e-mail address in the format <netID>@wisc.edu: ");
						email = scnr.nextLine();
						emailArray = email.split("[ ]+");
						
						// Makes sure user input for email is valid
						if (email.contains("@wisc.edu") && (emailArray.length == 1)) {
							break;
						}
						
						System.out.println("Invalid e-mail format. Must be in the format <netID>@wisc.edu:");
					} while(true);
					
					// Attempt to add the student to the database
					addStatus = addInfo(name, email);
					
					if(!addStatus) {
						System.out.println("Student already in the database.\n");
					}
					else {
					System.out.println("Student successfully added to database.\n");
					}
				}
				
				// Invalid # of strings for FirstName LastName
				else {
					System.out.println("Invalid name format.  Student's name must be in the format 'FirstName LastName'");
					// Prompt the user for a name again
					userCommands(command);
				}
									
				break;
			
			// Search Functionality
			case('s'):
				System.out.println("Search for a Student in the Directory:");
				System.out.println("Enter the first and last name of the student separated by a space.");
				name = scnr.nextLine();
				nameArray = name.split("[ ]+");
				
				// Search for name if input has valid formatting
				if(nameArray.length == 2) {
					searchBlock = retrieveEntry(name);
					// No Student Found
					if(searchBlock == null) {
						System.out.println("Student specified does not exist in the directory.");
					}
					// Display Found Student's Name and E-mail
					else {
						System.out.println("Student Name: " + searchBlock.name);
						System.out.println("Student's E-mail: " + searchBlock.getInfo());
					}
				}
				
				// Invalid # of strings for FirstName LastName
				else {
					System.out.println("Invalid name format.  Student's name must be in the format 'FirstName LastName'");
					// Prompt user for a name again
					userCommands(command);
				}
				
				break;
		
			// Remove Functionality
			case('r'):
				System.out.println("Removing a Student from the Directory:");
				System.out.println("Enter the first and last name of the student to remove separated by a space:");
				name = scnr.nextLine();
				nameArray = name.split("[ ]+");
				// Valid Name Input
				if(nameArray.length == 2) {
					removeBlock = remove(name);
					if (removeBlock == null) {
						System.out.println("Student specified does not exist in the directory.");
					}
					 else {
						System.out.println("Student successfully removed");
					}
				}
				
				// Invalid Name Input
				else {
					System.out.println("Invalid name format.  Student's name must be in the format 'FirstName LastName'");
					userCommands(command);
				}
				
				// If valid name input is not found, remove defaults to returning to command prompt to avoid infinite loop
				break;
			
			case('c'):
				System.out.println("Clearing the Student Directory...");
				clear();
				System.out.println("Student Directory Cleared");
				break;
			
			case('z'):
				size = entriesInDirectory();
				System.out.println("The Number of Students Currently in the Directory is: " + size);
				
				break;
		
			// User did not input one of the 5 valid character commands
			default:
				System.out.println("Invalid character input.  Please try again.\n");
		}
		// After a completed operation, return back to the command prompt
		commandPrompt();
	}
	
	/* Method that displays the user command prompt and calls userCommands() for a given
	 * user input, where the appropriate operation will be carried out.
	 * 
	 * @author Cristian Sanchez
	 */ 
	public static void commandPrompt() {
		Scanner sc = new Scanner(System.in);
		String inputString;
		Character x;
		
		// Command Prompt Display
		System.out.println("Enter a USER COMMAND!");
		System.out.println("'A' - Add a Student to the Directory");
		System.out.println("'S' - Search for a Student in the Directory");
		System.out.println("'R' - Remove a Student from the Directory");
		System.out.println("'C' - Clears the Student Directory");
		System.out.println("'Z' - Displays the Number of Students in the Directory");
		
		inputString = sc.nextLine();

		
		// Ensures user only input 1 character
		if (inputString.length() == 1) {
			x = inputString.charAt(0);	
			// For standardization in later comparisons
			x = Character.toLowerCase(x);
			userCommands(x);
		}	
		
		else {
			System.out.println("User input must be one character.  Please try again.\n");
			commandPrompt();
		}
		
	}
	
	public static void main(String[] args) {
		StudentDirectory dir = new StudentDirectory();
		System.out.println("Welcome to the Student E-mail Search!\n");
		commandPrompt();		
	}
}
