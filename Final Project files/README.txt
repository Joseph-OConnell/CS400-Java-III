README for Team Project Four (CS400 @ UW Madison)
==================================================

Every member of a team must have an individual README.txt file filled in in their folder on
the team's GitHub repo.

Name of submitting team member: Joseph O'Connell
Wisc email of submitting team member: jpoconnell2@wisc.edu
Team name: FB
Role of submitting team member: Data Wrangler 2
TA: Abhay Kumar
Lecturer: Gary Dahl

Contributions Week 1:
---------------------
- Wrote Tweet.java object file
- Wrote TweetList.java object file
Our group misiniterpreted the phrasing of the proposal so I ended up writing these files two times.  
Some of the variables I wrote match Jacob's (Data Wrangler 1) code because the backend/frontend code use the variable names.
Worked with the group to get the data organized from the CSV & a general plan for how to import lines from the file.
We should have a working implementation next week.

Contributions Week 2:
---------------------
- Added the loadTree method to BackEnd
- removed uneccessary information from the dataset
This method loads all the data from the csv and adds it into the tweetTree (extended RBT).  I also revised past files
that needed manipulation to format the data properly.  Proposed revisions to back end files to cut down on unused code and
propose changes to improve efficiency.  

Contributions Week 3:
---------------------
- Moved LoadTree() method from BackEnd to a new file called TweetTree
- Wrote simple tests for Tweet Tree (code included) and adjusted formatting for HTML
- Troubleshooted the website, CGI issues, and permission issues.
I assisted Mark with some of the front end, and he also helped me get my end of the CGI working.  We ran into some permissions issues
with the wisc servers which took awhile to troubleshoot.  I also worked with my teammates to help them get their websites working.  
My functioning version of the website is listed below!

Files written by me:
--------------------
- Tweet.java
- TweetList.java
- TweetTree.java
- TweetTreeTest.java (not used)

Files submitted with this project that were developed in an earlier project:
----------------------------------------------------------------------------
- RedBlackTree.java (assigned in class)
- project4.cgi (cgi activity)
- upload (cgi activity)


Web address at which the program is available:
----------------------------------------------
https://pages.cs.wisc.edu/~jo-connell/project4/project4.cgi

Additional notes about the submission:
--------------------------------------
I uploaded my code and run the program from directly within the Wisc public html-s server.  

Unused files
- TweetTreeTest.java - just me experimenting/testing
- upload (not needed since I uploaded directly to wisc server).  This includes the upload part of the Makefile too.  
