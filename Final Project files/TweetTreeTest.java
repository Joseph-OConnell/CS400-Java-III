// --== CS400 File Header Information ==--
// Name: Joseph O'Connell
// Email: jpoconnell2@wisc.edu
// Team: FB
// TA: Abhay Kumar
// Lecturer: Gary Dahl
// Notes to Grader:

/**
 * This is just a very simple test to make sure TweetTree was working properly & to adjust output
 * formatting.
 * 
 * @author Joseph O'Connell
 *
 */
public class TweetTreeTest {

  public static void main(String[] args) {
    TweetTree UW = new TweetTree();
    UW.loadTree();

    System.out.println(UW.searchTweet(191211));
  }

}
