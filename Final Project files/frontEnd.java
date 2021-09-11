// --== CS400 File Header Information ==--
// Name: Mark Stout
// Email: mnstout@wisc.edu
// Team: FB
// TA: Abhay Kumar
// Lecturer: Florian
// Notes to Grader: <optional extra notes>


public class frontEnd{
	public static void main(String[] args) {
		
		TweetTree backEnd = new TweetTree();
		backEnd.loadTree();
    		String output = "";
		if (args.length != 0) {
		  int argument = Integer.parseInt(args[0]);
			TweetList dateTweets = backEnd.searchTweet(argument);
			if (dateTweets != null) {
			  output = dateTweets.toString();
			}
		}
		
		System.out.println("<html><head><title>UW Madison Tweet Search</title>");
		
		
		
		//Style
		System.out.println("<style>" +
			"body {background-color: #00ccff;}" +
			"h1 {background-color: #0099cc; text-align: center; color: #ffffff; font-size: 42px; border: 8px ridge #ffffff;}" +
			"h2 {background-color: #0099cc; text-align: center; color: #ffffff; font-size: 20px; border: 8px ridge #ffffff;}" +
			"h3 {background-color: #0099cc; text-align: center; color: #ffffff; font-size: 18px; border: 8px ridge #ffffff;}" +
			"button {color: #000066; background-color: #ffffff; border: 2px ridge #000066; padding: 4px 16px;}" +
			"button:hover {color: #000066; background-color: #b3b3b3; border: 2px ridge #000066; padding: 4px 16px;}" +
			"hr.solid { border-top: 3px solid #bbb; }" + 
			"</style>");
		
		
		
		//Script
		System.out.println("<script>" +
			"function loadTweets() {" +
			"var day = document.getElementById(\"Day\").value;" +
			"var month = document.getElementById(\"Month\").value;" +
			"var year = document.getElementById(\"Year\").value;" +
				
			//Test Bench for incorrect Values
			"if (isNaN(day) || isNaN(month) || isNaN(year)) {" +
				"document.getElementById(\"output\").innerHTML = \"Please enter a number value in each data field.\";" +
					"return;" +
				"} else if (year < 2019 || year > 2020) {" +
					"document.getElementById(\"output\").innerHTML = \"This program's Year Range is 2019-2020.\";" +
					"return;" +
				"} else if (month < 1 || month > 12) {" +
					"document.getElementById(\"output\").innerHTML = \"There are only 12 months in a year, choose one.\";" +
					"return;" +
				"} else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && (day < 1 || day > 31)) {" +
					"document.getElementById(\"output\").innerHTML = \"In your chosen month, there are 31 days, please choose a valid date.\";" +
					"return;" +
				"} else if ((month == 4 || month == 6 || month == 9 || month == 11) && (day < 1 || day > 30)) {" +
					"document.getElementById(\"output\").innerHTML = \"In your chosen month, there are 30 days, please choose a valid date.\";" +
					"return;" +
				"} else if ((year == 2019) && (month < 11 || (month == 11 && day < 20))) {" +
					"document.getElementById(\"output\").innerHTML = \"This date is before the time range allowed.\";" +
					"return;" +
				"} else if ((year == 2020) && (month > 11 || (month == 11 && day > 10))) {" +
                                        "document.getElementById(\"output\").innerHTML = \"This date is after the time range allowed.\";" +
                                        "return;" +


				"} else if (month == 2 && (day < 1 || day > 29) && year == 2020) {" +
					"document.getElementById(\"output\").innerHTML = \"This February (2020) had 29 days, please choose a valid day.\";" +
					"return;" +
				"} else if (month == 2 && (day < 1 || day > 28) && year == 2019) {" +
					"document.getElementById(\"output\").innerHTML = \"This February (2019) had 28 days, please choose a valid day.\";" +
					"return;" +
				"}" +
				
				//Create Date Variable
				//Year
				"var date = year.slice(2,4);" +
				//Month
				"if (month > 9) {" +
					"date = date.concat(month);" +
				"} else {" +
					"date = date.concat(\"0\"+month);" +
				"}" +
				//Day
				"if (day > 9) {" +
					"date = date.concat(day);" +
				"} else {" +
					"date = date.concat(\"0\"+day);" +
				"}" +
			
				"var url = window.location.href;" +
				
				"if (url.indexOf(\"?\") != -1) {" +
					"url = url.slice(0, (url.indexOf(\"?\")));}" +

				"url = url.concat(\"?\");" +
				"url = url.concat(date); " +	
				"location = url;" +	
				"document.getElementById(\"output\").innerHTML = (date);" +
				
			"}" +

		"</script></head>");
		
		
		
		//Body
		System.out.println("<body>" +
			"<h1><br>UW Madison Twitter Search<br><br></h1>" +
			
			"<h2><br>" +
			"<label>Enter a date from November 20th, 2019, to November 10th, 2020.</label>" +
			"<br><br>" +
			"<label>Enter Day (#): </label><input id=\"Day\" type=\"number\"></input>" +
			"<br>" +
			"<label>Enter Month (#): </label><input id=\"Month\" type=\"number\"></input>" +
			"<br>" +
			"<label>Enter Year (#): </label><input id=\"Year\" type=\"number\"></input>" +
			"<br><br>" +
			"<button onclick=\"loadTweets()\"> Load Tweets </button>" +
			"<br><br>" +
			"</h2>" +
		
			"<h3><br>" +
			"<p><span id=\"output\"></span></p><br>" +
			"<p>Tweets for the day!</p><br><hr class=\"solid\">" +
			"<p>" + output + "</p>" +
			"<br></h3></body></html>");
		
		
}}
