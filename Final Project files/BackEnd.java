// --== CS400 File Header Information ==--
// Name: Pak Lun Kevin Cheung
// Email: pcheung4@wisc.edu
// Team: FB
// TA: Abhay Kumar
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * This is the BackEnd class that will be used for the front end to add, search, list, and display
 * the number of tweets. A red black tree will be used to store tweets. The tree will be ordered
 * using dates, and collisions will be handled by using a linked list.
 * 
 * @author Pak Lun Kevin Cheung
 *
 */
public class BackEnd {

  protected class RBTreeTweets extends RedBlackTree<TweetList> {

    /**
     * Tweetlist search using compareTo method in Tweetlist.
     * 
     * @param date
     * @return TweetList The TweetList at the specified date.
     * 
     * @author Pak Lun Kevin Cheung
     * 
     */
    protected TweetList search(int date) {
      
      // Construct TweetList with the specified date for comparison.
      TweetList temp = new TweetList();
      temp.addTweet(new Tweet(date, null, 0, 0, null, null, null));
      
      // Search through the tree
      Node<TweetList> curNode = this.root;
      while (curNode != null) {
    	  // If found, return the list. If not found, go to right/left child.
    	  if (temp.compareTo(curNode.data) == 0) {
    		  return curNode.data;
    	  } else if (temp.compareTo(curNode.data) < 0) {
    		  curNode = curNode.leftChild;
    	  } else {
    		  curNode = curNode.rightChild;
    	  }
      }
      
      // Not found in tree, return null
      return null;
    }

  }

  private RBTreeTweets tweetTree; // Red black tree used for storing data in form of TweetLists.

  /**
   * Constructor method to create a new red black tree for data storage.
   * 
   * @author Pak Lun Kevin Cheung
   * 
   */
  public BackEnd() {
    tweetTree = new RBTreeTweets();
  }

  /**
   * Method for adding a tweet into the red black tree.
   * 
   * @param newTweet A new tweet to be added
   * 
   * @author Pak Lun Kevin Cheung
   * 
   */
  public void addTweet(Tweet newTweet) {
    int date = newTweet.getDate();
    TweetList listAtDate = searchTweet(date);
    if (listAtDate == null) {
      TweetList newList = new TweetList();
      newList.addTweet(newTweet);
      tweetTree.insert(newList);
    } else {
      listAtDate.addTweet(newTweet);
    }
  }

  /**
   * Method to find tweets from a given date
   * 
   * @param date The date of the tweet
   * @return TweetList Tweets at that date
   * 
   * @author Pak Lun Kevin Cheung
   * 
   */
  public TweetList searchTweet(int date) {
    return tweetTree.search(date);
  }
}
