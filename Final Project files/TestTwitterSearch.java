// --== CS400 File Header Information ==--
// Name: Cristian Sanchez
// Email: csanchez24@wisc.edu
// Team: FB
// TA: Abhay Kumar
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestTwitterSearch {

	/*
	 * Tests the components of a newly initialized Tweet object
	 */
	@Test
	void testNewTweetObject() {
		Tweet testTweet = new Tweet(120523, "Cool Guys Don't Look at Explosions", 39, 10, "www.google.com", null, "Cool");
		
		assertTrue(testTweet.getDate() == 120523);
		assertEquals(testTweet.getText(), "Cool Guys Don't Look at Explosions");
		assertEquals(testTweet.getURL(), "www.google.com");
		assertEquals(testTweet.getID(), "Cool");
		
	}
  
	/*
	 * Tests adding a new Tweet List Object to the Red Black Tree
	 */
	@Test
	void testAddTweetListObject() {
		BackEnd tweetSearch = new BackEnd();
		Tweet testTweet = new Tweet(120523, "Testing TweetList", 53, 12, "www.bing.com", null, "ATL");
		Tweet testTweet2 = new Tweet(130211, "Testing TweetList 2", 63, 86, "www.google.com", null, "ATL2");
		Tweet testTweet3 = new Tweet(150922, "Testing TweetList 3", 433, 123, "www.yahoo.com", null, "ATL3");
		
		tweetSearch.addTweet(testTweet);
		tweetSearch.addTweet(testTweet2);
		tweetSearch.addTweet(testTweet3);
		
		// Checks that all tweets were successfully added to the RedBlackTree
		assertTrue(tweetSearch.searchTweet(120523) != null);
		assertTrue(tweetSearch.searchTweet(130211) != null);
		assertTrue(tweetSearch.searchTweet(150922) != null);
		
		// Checks that size of all TweetLists should be 1 (no Tweets share the same date)
		assertTrue(tweetSearch.searchTweet(120523).getSize() == 1);
		assertTrue(tweetSearch.searchTweet(130211).getSize() == 1);
		assertTrue(tweetSearch.searchTweet(150922).getSize() == 1);
		
	}
	
	/*
	 * Tests that Tweets on the same day are in the same TweetList Object
	 */
	@Test
	void testTweetListLinkedList() {
		BackEnd tweetSearch = new BackEnd();
		
		Tweet testTweet = new Tweet(120523, "Testing TweetList Dates", 53, 12, "www.bing.com", null, "TLD");
		Tweet testTweet2 = new Tweet(120523, "Testing TweetList Dates 2", 63, 86, "www.google.com", null, "TLD2");
		Tweet testTweet3 = new Tweet(120523, "Testing TweetList Dates 3", 433, 123, "www.yahoo.com", null, "TLD3");
		Tweet testTweet4 = new Tweet(111223, "Testing TweetList Dates 4", 21, 33, "www.youtube.com", null, "TLD4");
		
		tweetSearch.addTweet(testTweet);
		tweetSearch.addTweet(testTweet2);
		tweetSearch.addTweet(testTweet3);
		tweetSearch.addTweet(testTweet4);
		
		// 3 of the Tweets Share a Date, so the Size of the TweetList for that date should be 3
		assertTrue(tweetSearch.searchTweet(120523).getSize() == 3);
		
		// Ensures components of the first Tweet in the TweetList are unchanged
		Tweet tempTweet = tweetSearch.searchTweet(120523).getTweet(0);
		assertEquals(tempTweet.getID(), "TLD");
		assertEquals(tempTweet.getText(), "Testing TweetList Dates");
		assertEquals(tempTweet.getURL(), "www.bing.com");
		assertTrue(tempTweet.getFavorites() == 53);
		assertTrue(tempTweet.getRetweets() == 12);
		
		// Same as above but for the last Tweet in the TweetList
		int tweetListSize = tweetSearch.searchTweet(120523).getSize();
		Tweet tempTweet2 = tweetSearch.searchTweet(120523).getTweet(tweetListSize - 1);
		assertEquals(tempTweet2.getID(), "TLD3");
		assertEquals(tempTweet2.getText(), "Testing TweetList Dates 3");
		assertEquals(tempTweet2.getURL(), "www.yahoo.com");
		assertTrue(tempTweet2.getFavorites() == 433);
		assertTrue(tempTweet2.getRetweets() == 123);
		
	}
	
	/*
	 * Tests that particular Tweets store the correct number of retweets
	 */
	@Test
	void testRetweets() {
		Tweet testTweet = new Tweet(120523, "Testing Retweets", 53, 12, "www.bing.com", null, "RT");
		Tweet testTweet2 = new Tweet(130211, "Testing Retweets 2", 63, 86, "www.google.com", null, "RT2");
		Tweet testTweet3 = new Tweet(150922, "Testing Retweets 3", 433, 123, "www.yahoo.com", null, "RT3");
		
		assertTrue(testTweet.getRetweets() == 12);
		assertTrue(testTweet2.getRetweets() == 86);
		assertTrue(testTweet3.getRetweets() == 123);
	}
	
	/*
	 * Tests that particular Tweets store the correct value for favorites
	 */
	@Test
	void testFavorites() {
		Tweet testTweet = new Tweet(120523, "Testing Retweets", 53, 12, "www.bing.com", null, "RT");
		Tweet testTweet2 = new Tweet(130211, "Testing Retweets 2", 63, 86, "www.google.com", null, "RT2");
		Tweet testTweet3 = new Tweet(150922, "Testing Retweets 3", 433, 123, "www.yahoo.com", null, "RT3");
		
		assertTrue(testTweet.getFavorites() == 53);
		assertTrue(testTweet2.getFavorites() == 63);
		assertTrue(testTweet3.getFavorites() == 433);
	}
	
	/*
	 * Tests the Tweet Tree class without loading, with a basic date input we expect
	 * a null output as nothing has been added to the tree yet.
	 */
	@Test
	void testTweetTreeNull() {
		TweetTree tempTree = new TweetTree();
		assertTrue(tempTree.searchTweet(200512) == null);
	}
	
	/*
	 * Tests the TweetTree's loadFile method, and then searches using a date with
	 * a known amount of entries (May 12th, 2020 has 12 tweets) 
	 */
	@Test
	void testTweetTreeLoadFile() {
		TweetTree tempTree = new TweetTree();
		tempTree.loadTree();
		assertTrue(tempTree.searchTweet(200512).getSize() == 12);
	}
	
  /*
	 * Tests the TweetTree's returned TweetList for the correct Favorites counts on individual
     tweets.
	 */
	@Test
	void testTweetTreeFavorites() {
		TweetTree tempTree = new TweetTree();
		tempTree.loadTree();
		TweetList tempList = tempTree.searchTweet(200512);
    
    // Tests All 12 Tweet's Favorite Counts
    assertTrue(tempList.getTweet(0).getFavorites() == 185);
    assertTrue(tempList.getTweet(1).getFavorites() == 0);
    assertTrue(tempList.getTweet(2).getFavorites() == 13);
    assertTrue(tempList.getTweet(3).getFavorites() == 16);
    assertTrue(tempList.getTweet(4).getFavorites() == 0);
    assertTrue(tempList.getTweet(5).getFavorites() == 0);
    assertTrue(tempList.getTweet(6).getFavorites() == 60);
    assertTrue(tempList.getTweet(7).getFavorites() == 0);
    assertTrue(tempList.getTweet(8).getFavorites() == 19);
    assertTrue(tempList.getTweet(9).getFavorites() == 5);
    assertTrue(tempList.getTweet(10).getFavorites() == 31);
    assertTrue(tempList.getTweet(11).getFavorites() == 0);
	}
  
  /*
	 * Tests the TweetTree's returned TweetList for the correct Retweets counts on individual
     tweets.
	 */
	@Test
	void testTweetTreeRetweets() {
		TweetTree tempTree = new TweetTree();
		tempTree.loadTree();
		TweetList tempList = tempTree.searchTweet(200512);
    
    // Tests All 12 Tweet's Retweet Counts
    assertTrue(tempList.getTweet(0).getRetweets() == 16);
    assertTrue(tempList.getTweet(1).getRetweets() == 0);
    assertTrue(tempList.getTweet(2).getRetweets() == 2);
    assertTrue(tempList.getTweet(3).getRetweets() == 2);
    assertTrue(tempList.getTweet(4).getRetweets() == 0);
    assertTrue(tempList.getTweet(5).getRetweets() == 0);
    assertTrue(tempList.getTweet(6).getRetweets() == 7);
    assertTrue(tempList.getTweet(7).getRetweets() == 0);
    assertTrue(tempList.getTweet(8).getRetweets() == 7);
    assertTrue(tempList.getTweet(9).getRetweets() == 4);
    assertTrue(tempList.getTweet(10).getRetweets() == 5);
    assertTrue(tempList.getTweet(11).getRetweets() == 0);
	}
 
  /*
	 * Tests the TweetTree's returned TweetList for the correct URLs (non-media)
	 */
 	@Test
	void testTweetTreeURL() {
		TweetTree tempTree = new TweetTree();
		tempTree.loadTree();
		TweetList tempList = tempTree.searchTweet(200512);
    
    // Tests the Tweets with normal (non-media) URLs:
    assertEquals(tempList.getTweet(1).getURL(), "https://youtu.be/bJ_awBvoye4 ");
    assertEquals(tempList.getTweet(2).getURL(), "https://twitter.com/i/moments/1260319637461401601 ");
    assertEquals(tempList.getTweet(3).getURL(), "https://news.wisc.edu/research-on-viral-junk-quicker-drug-testing-could-help-outflank-coronaviruses/ ");
    assertEquals(tempList.getTweet(4).getURL(), "https://twitter.com/APLU_News/status/1260205336004362240 ");
    assertEquals(tempList.getTweet(5).getURL(), "https://www.wpr.org/shows/coronavirus-making-vaccine ");
    assertEquals(tempList.getTweet(6).getURL(), "https://news.wisc.edu/faculty-receive-warf-kellett-romnes-awards-2/ ");
    assertEquals(tempList.getTweet(9).getURL(), "https://www.allwaysforward.org/uwnow/economy-and-covid/ ");
	}
 
   /*
	 * Tests the TweetTree's returned TweetList for the correct media file
	 */
 	@Test
	void testTweetTreeMedia() {
		TweetTree tempTree = new TweetTree();
		tempTree.loadTree();
		TweetList tempList = tempTree.searchTweet(200512);
    
    // Tests only the Tweets with media links:
    assertEquals(tempList.getTweet(0).getMedia().get(0), "https://pbs.twimg.com/media/EXknOMqXkAAYZ7N.jpg");
    assertEquals(tempList.getTweet(7).getMedia().get(0), "https://pbs.twimg.com/media/EX1V6vBXgAI6XMF.jpg");
    assertEquals(tempList.getTweet(8).getMedia().get(0), "https://pbs.twimg.com/media/EX1RmU3XQAAbxWH.jpg");
    assertEquals(tempList.getTweet(10).getMedia().get(0), "https://pbs.twimg.com/media/EWspWqUUcAMRgD9.jpg");
    assertEquals(tempList.getTweet(11).getMedia().get(0), "https://video.twimg.com/amplify_video/1257807953912123392/vid/1280x720/q0D1Zbt_HpGN7bRv.mp4?tag=13");
	}
 
   /*
	 * Tests the TweetTree's returned TweetList for the correct Tweet text in each individual Tweet
	 */
 	@Test
	void testTweetTreeText() {
		TweetTree tempTree = new TweetTree();
		tempTree.loadTree();
		TweetList tempList = tempTree.searchTweet(200512);
    
    // Ensures that each of the 12 Tweets contains characters unique to them
    assertTrue(tempList.getTweet(0).getText().contains("@SenatorBaldwin '89 offers her congratulations"));
    assertTrue(tempList.getTweet(1).getText().contains("RT @WisAlumni : TONIGHT 7 PM"));
    assertTrue(tempList.getTweet(2).getText().contains("We're keeping the #UWGrad shoutout"));
    assertTrue(tempList.getTweet(3).getText().contains("#UWMadison researchers are on the front lines fighting against #COVID19"));
    assertTrue(tempList.getTweet(4).getText().contains("RT @BeckyBlank"));
    assertTrue(tempList.getTweet(5).getText().contains("RT @UWMadPharmacy : School of Pharmacy Professor"));
    assertTrue(tempList.getTweet(6).getText().contains("Our faculty are among the best in the world across the arts and humanities"));
    assertTrue(tempList.getTweet(7).getText().contains("RT @DiscoveryBldg"));
    assertTrue(tempList.getTweet(8).getText().contains("Everyone should #StayHome as much as possible to slow the spread of #COVID19"));
    assertTrue(tempList.getTweet(9).getText().contains("economists Ananth Seshadri, Brad Tank and Noah Williams"));
    assertTrue(tempList.getTweet(10).getText().contains("Our best defense against #COVID19 is to work together"));
    assertTrue(tempList.getTweet(11).getText().contains("RT @NCAA :"));
	}
 
   /*
	 * Tests that, after loading the data, a date within the designated range in which there were 
   * no Tweets returns a null reference when being searched (July 12th, 2020).
	 */
	@Test
	void testTweetTreeNullDate() {
		TweetTree tempTree = new TweetTree();
		tempTree.loadTree();
		assertTrue(tempTree.searchTweet(200712) == null);
	}
 
   /*
	 * Tests that the TweetTree's returned TweetList is null when searched using a date out of the 
  * designated range for Tweets. (November 10th, 20001)
	 */
 	@Test
	void testTweetTreeDateOutOfRange() {
		TweetTree tempTree = new TweetTree();
		tempTree.loadTree();
		assertTrue(tempTree.searchTweet(011110) == null);
    
	}
 
   /*
	 * Tests various TweetTree and TweetList for a different date than the one previously used
   * (this time, it uses December 12th, 2019) 
	 */
	@Test
	void testTweetTreeDecember12() {
		TweetTree tempTree = new TweetTree();
		tempTree.loadTree();
		TweetList tempList = tempTree.searchTweet(191212);
    
    // Size
    assertTrue(tempList.getSize() == 11);
    
    // Favorites
    assertTrue(tempList.getTweet(0).getFavorites() == 13);
    assertTrue(tempList.getTweet(5).getFavorites() == 153);
    
    // Retweets
    assertTrue(tempList.getTweet(1).getRetweets() == 0);
    assertTrue(tempList.getTweet(2).getRetweets() == 0);
    
    // Normal URL 
    assertEquals(tempList.getTweet(6).getURL(), "https://today.wisc.edu/events/view/141084 ");
    
    // Media URLs
    assertEquals(tempList.getTweet(3).getMedia().get(0), "https://pbs.twimg.com/media/ELm-rQ3XsAAafrn.jpg");
    
    // Text
    assertTrue(tempList.getTweet(5).getText().contains("It's the most wonderful time of the year!"));
    
	}
    /*
	 * Testing another Tweet Tree date from the data.  This time, May 18th 2020.
	 */
	@Test
	void testTweetTreeMay18() {
		TweetTree tempTree = new TweetTree();
		tempTree.loadTree();
		TweetList tempList = tempTree.searchTweet(200518);
    
    // Size
    assertTrue(tempList.getSize() == 8);
    
    // Favorites
    assertTrue(tempList.getTweet(0).getFavorites() == 0);
    assertTrue(tempList.getTweet(5).getFavorites() == 0);
    
    // Retweets
    assertTrue(tempList.getTweet(1).getRetweets() == 0);
    assertTrue(tempList.getTweet(2).getRetweets() == 0);
    
    // Normal URL 
    assertEquals(tempList.getTweet(6).getURL(), "https://www.library.wisc.edu/help/ ");
    
    // Media URLs
    assertEquals(tempList.getTweet(2).getMedia().get(0), "https://pbs.twimg.com/media/EYUK6D1XQAA_Ege.jpg");
    
    // Text
    assertTrue(tempList.getTweet(2).getText().contains("Summer at UW-Madison has officially begun!"));
    
	}
 
   /*
	 * Tests the Tweet Tree from the data set on September 1st, 2019.
	 */
	@Test
	void testTweetTreeSeptember1() {
		TweetTree tempTree = new TweetTree();
		tempTree.loadTree();
		TweetList tempList = tempTree.searchTweet(200901);
    
    // Size
    assertTrue(tempList.getSize() == 18);
    
    // Favorites
    assertTrue(tempList.getTweet(0).getFavorites() == 1);
    assertTrue(tempList.getTweet(5).getFavorites() == 9);
    
    // Retweets
    assertTrue(tempList.getTweet(13).getRetweets() == 0);
    assertTrue(tempList.getTweet(9).getRetweets() == 10);
    
    // Normal URL 
    assertEquals(tempList.getTweet(8).getURL(), "https://onwisconsin.uwalumni.com/traditions/a-place-where-you-belong/ ");
    
    // Media URLs
    assertEquals(tempList.getTweet(9).getMedia().get(0), "https://pbs.twimg.com/media/Eg2alXsWAAA32yf.jpg");
    
    // Text
    assertTrue(tempList.getTweet(11).getText().contains("And most important, we want to make sure you take care of yourself."));
	}
 
    /*
	 * Tests the components of the Tweet tree near graduation last year, May 14th 2020.
	 */
	@Test
	void testTweetTreeMay14() {
		TweetTree tempTree = new TweetTree();
		tempTree.loadTree();
		TweetList tempList = tempTree.searchTweet(200514);
    
    // Size
    assertTrue(tempList.getSize() == 12);
    
    // Favorites
    assertTrue(tempList.getTweet(0).getFavorites() == 2);
    assertTrue(tempList.getTweet(5).getFavorites() == 0);
    
    // Retweets
    assertTrue(tempList.getTweet(3).getRetweets() == 0);
    assertTrue(tempList.getTweet(9).getRetweets() == 0);
    
    // Normal URL 
    assertEquals(tempList.getTweet(10).getURL(), "https://covid19.wisc.edu/safer-at-home-guidelines-still-in-effect-in-dane-county/ ");
    
    // Media URLs
    assertEquals(tempList.getTweet(9).getMedia().get(0), "https://pbs.twimg.com/media/EX-sSjOXsAIkpvF.jpg");
    
    // Text
    assertTrue(tempList.getTweet(3).getText().contains("Please visit our Facebook page for the winners"));
    
	}
 
   /*
    * Tests the components of the TweetTree for Halloween, October 31st, 2020.
	 */
	@Test
	void testTweetTreeOctober31() {
		TweetTree tempTree = new TweetTree();
		tempTree.loadTree();
		TweetList tempList = tempTree.searchTweet(201031);
    
    // Size
    assertTrue(tempList.getSize() == 8);
    
    // Favorites
    assertTrue(tempList.getTweet(0).getFavorites() == 6);
    assertTrue(tempList.getTweet(1).getFavorites() == 20);
    
    // Retweets
    assertTrue(tempList.getTweet(3).getRetweets() == 1);
    assertTrue(tempList.getTweet(4).getRetweets() == 1);
    
    // Normal URL 
    assertEquals(tempList.getTweet(7).getURL(), "https://students.wisc.edu/news/be-safe-healthy-and-kind-this-halloween-2/ ");
    
    // Media URLs
    assertEquals(tempList.getTweet(6).getMedia().get(0), "https://pbs.twimg.com/media/Elq6MA-WoAEr8Fc.png");
    
    // Text
    assertTrue(tempList.getTweet(7).getText().contains("Halloween will look different this year")); 
	}
 
  /*
  * Tests the media component of a Tweet with more than 1 media link, and ensures the values
  * of the individual links are maintained.
  */
  @Test
  void testMultipleMediaLinks() {
    TweetTree tempTree = new TweetTree();
		tempTree.loadTree();
		TweetList tempList = tempTree.searchTweet(191212);
   
   assertTrue(tempList.getTweet(3).getMedia().size() == 4);  
   
   assertEquals(tempList.getTweet(3).getMedia().get(0), "https://pbs.twimg.com/media/ELm-rQ3XsAAafrn.jpg");
   assertEquals(tempList.getTweet(3).getMedia().get(1), "https://pbs.twimg.com/media/ELm-rQ3XsAAafrn.jpg");
   assertEquals(tempList.getTweet(3).getMedia().get(2), "https://pbs.twimg.com/media/ELm-rQ3XsAAafrn.jpg");
   assertEquals(tempList.getTweet(3).getMedia().get(3), "https://pbs.twimg.com/media/ELm-rQ3XsAAafrn.jpg");
  }
}
