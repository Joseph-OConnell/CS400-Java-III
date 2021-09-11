// --== CS400 File Header Information ==--
// Name: Joseph O'Connell
// Email: jpoconnell2@wisc.edu
// Team: FB
// Role: Data Wrangler 2
// TA: Abhay Kumar
// Lecturer: Gary Dahl
// Notes to Grader:
import java.util.LinkedList;

/**
 * This class creates linked lists of tweets with the same date to be used as nodes in the RBT.
 * 
 * @author Joseph O'Connell
 *
 */
public class TweetList implements Comparable<TweetList> {
  // variable that contains the list of tweets with the same date.
  public LinkedList<Tweet> tweets;

  /**
   * no argument constructor that creates the tweets list.
   */
  public TweetList() {
    tweets = new LinkedList<Tweet>();
  }

  /**
   * adds a tweet to the tweet list. tweets have the same date.
   * 
   * @param tweet the tweet to add to the list
   * @return true if it successfully adds. False otherwise
   */
  public boolean addTweet(Tweet tweet) {
    return tweets.add(tweet);
  }

  /**
   * gets a tweet from an index in the list
   * 
   * @param index the index where the tweet is stored
   * @return tweet from the given index
   */
  public Tweet getTweet(int index) {
    return tweets.get(index);
  }

  /**
   * gets the size of the linked list
   * 
   * @return int size of the tweetList
   */
  public int getSize() {
    return tweets.size();
  }

  /**
   * Calls the tweet to string method repeatedly with additional formatting.
   * 
   * @return string represenation of the tweetList
   */
  public String toString() {
    String rawDate = "" + this.getTweet(0).getDate();
    String fixedDate = "" + rawDate.substring(2,4) + "/" + rawDate.substring(4,6) + "/" + rawDate.substring(0,2);
    String out = "<p>" + "Date: " + fixedDate + "\n\n" + "</p><hr class=\"solid\">";
    for (int i = 0; i < tweets.size(); i++) {
      out += "<p>" + "TWEET: " + (i + 1) + "\n<br>" + tweets.get(i).toString() + "\n\n\n" + "</p><hr class=\"solid\">";
    }
    return out;
  }

  /**
   * the compareTo method required for the tweetlist to be used as a node in the RBT. The tweets in
   * a given list all have the same date, looking at the 0th index will not cause problems.
   * 
   * @param tweetList to compare to this
   */
  @Override
  public int compareTo(TweetList tweetList) {
    if (this.getTweet(0).getDate() == tweetList.getTweet(0).getDate()) {
      return 0;
    }

    else if (this.getTweet(0).getDate() > tweetList.getTweet(0).getDate()) {
      return -1;
    }

    else {
      return 1;
    }
  }
}
