build: BackEnd.java frontEnd.java Tweet.java TweetList.java UWMadison_user_tweets.csv TweetTree.java RedBlackTree.java
	javac -source 1.7 -target 1.7 -cp . BackEnd.java
	javac -source 1.7 -target 1.7 -cp . Tweet.java
	javac -source 1.7 -target 1.7 -cp . TweetList.java
	javac -source 1.7 -target 1.7 -cp . TweetTree.java
	javac -source 1.7 -target 1.7 -cp . RedBlackTree.java
	javac -source 1.7 -target 1.7 -cp . frontEnd.java

run: build
	java -cp . frontEnd

upload: build UWMadison_user_tweets.csv
	./upload

TestSuite.class: junit5.jar build
	javac -cp .:junit5.jar TestTwitterSearch.java

test: TestSuite.class
	java -jar junit5.jar --class-path . --scan-classpath --details tree 

clean:
	rm *.class
