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
 * 
 * @author Joseph O'Connell
 *
 */
public class Tweet {
  private int date;
  private String text;
  private int favorites;
  private int retweets;
  private String url;
  private LinkedList<String> media;
  private String id;

  /**
   * Tweet Object constructor where csv data will be entered For some tweets there are no url or
   * media links defined. For these tweets we will pass in null as the argument to the constructor.
   * 
   * @param date      the date of the tweet
   * @param text      the full text content of the tweet
   * @param favorites number of favorites
   * @param retweets  number of retweets
   * @param url       linked list of the links contained in the tweet
   * @param media     linked list of the media links in the tweet
   * @param id        string representation of the tweet id
   */
  public Tweet(int date, String text, int favorites, int retweets, String url,
      LinkedList<String> media, String id) {
    this.date = date;
    this.text = text;
    this.favorites = favorites;
    this.retweets = retweets;
    this.url = url;
    this.media = media;
    this.id = id;
  }

  /**
   * Returns the date of the given tweet.
   * 
   * @return int representation of the date
   */
  public int getDate() {
    return this.date;
  }

  /**
   * gets the string info of the tweet
   * 
   * @return text representation of the tweet.
   */
  public String getText() {
    return this.text;
  }

  /**
   * gets the number of favorites
   * 
   * @return int number of favorites
   */
  public int getFavorites() {
    return this.favorites;
  }

  /**
   * gets the number of retweets
   * 
   * @return int number of retweets
   */
  public int getRetweets() {
    return this.retweets;
  }

  /**
   * gets the linked list string representation of the links from the tweet
   * 
   * @return URL in the form of a string
   */
  public String getURL() {
    return this.url;
  }

  /**
   * gets the linked list string representation of the media links from the tweet
   * 
   * @return linked list of media link strings
   */
  public LinkedList<String> getMedia() {
    return this.media;
  }

  /**
   * gets the id of the tweet
   * 
   * @return string representation of the ID
   */
  public String getID() {
    return this.id;
  }

  /**
   * Outputs a formatted string version of the tweet Date will be included in the TweetList toString
   * method
   * 
   * @return String representation of whole tweet + data
   */
  public String toString() {
    String out;
    out = "Tweet: " + text + "\n<br>" + "Favorites: " + favorites + "\n<br>" + "Retweets: " + retweets
        + "\n<br>";
    
    if( url.length() > 2) {
      out += "Link: " + url + "\n<br>";
    }

    // iterate through list of links
    for (int i = 0; i < media.size(); i++) {
      out += "Media Link " + (i + 1) + ": " + media.get(i) + "\n<br>";
    }
    return out;
  }
}
