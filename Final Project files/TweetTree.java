// --== CS400 File Header Information ==--
// Name: Joseph O'Connell
// Email: jpoconnell2@wisc.edu
// Team: FB
// TA: Abhay Kumar
// Lecturer: Gary Dahl
// Notes to Grader: I watched many many tutorials on delimiters as help
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class TweetTree extends BackEnd {

  /**
   * @author Joseph O'Connell
   * 
   *         the loadTree method fills the already generated tweetTree (extended RBT) with the
   *         tweets from our dataset (last 3200 tweets from @UWMadison). It simply loads the
   *         existing static data into the tree which can then be recursively searched by date. The
   *         front end code will need to call this method before attempting to search for the
   *         requested date in the tree.
   */
  public void loadTree() {
    // import the data and parse with scanner
    File tweetFile = new File("UWMadison_user_tweets.csv");
    Scanner sc = null;
    try {
      sc = new Scanner(tweetFile, "utf-8");
    } catch (FileNotFoundException e) {
      System.out.println("Could not load data");
      e.printStackTrace();
    }
    // getting rid of initial line since it's not used
    sc.nextLine();

    // declare all the variables used in loop
    String id;
    String text;
    String time;
    int date;
    int fav;
    int rt;
    String url;
    String media1;
    String media2;
    String media3;
    String media4;
    LinkedList<String> media;
    Tweet tempTweet = null;

    // loop as long as there is still data in the file
    while (sc.hasNextLine()) {

      // CSV file, so delimit using ,
      sc.useDelimiter(",");
      id = sc.next();

      // change delimiter to account for ',' in the text portion of tweet
      sc.useDelimiter(",20");
      text = sc.next();

      // get rid of initial comma
      text = text.substring(1);
      sc.useDelimiter(",");
      time = sc.next();

      // change into a sortable int format
      time = time.substring(2, 4) + time.substring(5, 7) + time.substring(8, 10);
      date = Integer.parseInt(time);

      // fav retweet and url
      fav = sc.nextInt();
      rt = sc.nextInt();
      url = sc.next();

      // skip over unused data
      sc.next();
      sc.next();
      sc.next();

      // collect all the media links and add to the LinkedList if not empty
      media1 = sc.next();
      media2 = sc.next();
      media3 = sc.next();
      media4 = sc.nextLine();
      media4 = media4.substring(1);
      media = new LinkedList<String>();
      if (media1.length() > 2) {
        media.add(media1);
      }
      if (media2.length() > 2) {
        media.add(media1);
      }
      if (media3.length() > 2) {
        media.add(media1);
      }
      if (media4.length() > 2) {
        media.add(media1);
      }

      // create the tweet object and add it
      tempTweet = new Tweet(date, text, fav, rt, url, media, id);
      this.addTweet(tempTweet);
    }
  }
}
