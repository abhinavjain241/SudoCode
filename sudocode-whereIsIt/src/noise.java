public class noise {
	boolean Noise(String word){
		if(checkForArticle(word)==false)
			return false;
		if(checkForNumber(word)==false)
			return false;
		if(checkForVerb(word)==false)
			return false;
		if(checkForColor(word)==false)
			return false;
		if(checkForAdjectives(word)==false)
			return false;
		if(checkForRandom(word)==false)
			return false;
	   // if(checkForConjunction(word)==false)
	    //	return false;
	return true;
	}
	boolean checkForRandom(String word){
		String w[]={"can", "still","palm","left","right","up","down","front","may","might","should","will","won't",
				    "did","cannot","didn't","jumps","kept","could","must","agree","side","from"};
		for(String y:w){
			if(word.equals(y))
				return false;
		} 
		return true;
	}
	boolean checkForArticle(String wordForCheck){
		 String article[]={"has","have","had","been","can","did","might","may","traditional","be","can't","that",
				           "to","visible","both","off","was","were","distant","back","at","their","right","left","centre","on","middle","brick","from","in","all","bit","giant","of","its","view","front","a","an","the","levels","is","am","are","already","wooden","although","very","some","most","few","more","few","my","his","her"};
			for(String y:article){
				if(wordForCheck.equals(y))
					return false;
			}
		return true;  
	}
	boolean checkForNumber(String wordForCheck){
		String number[]={"zero",
				"first",
				"second",
				"third",
				"fourth",
				"fifth",
				"sixth",
				"ninth",
				"seventh",
				"eigth",
                "one",
                "two",
                "three",
                "four",
                "five",
                "six",
                "seven",
                "eight",
                "nine",
                "ten"};
			for(String y:number){
				if(wordForCheck.equals(y))
					return false;
			}
		return true;
	}
	boolean checkForColor(String wordForCheck){
		String color[]={"blue",
				"pink",
				"black",
				"white",
				"grey",
                "red",
                "green",
                "yellow",
                "purple",
                "orange",
                "violet",
                "brown"
                };
			for(String y:color){
				if(wordForCheck.equals(y))
					return false;
			}
		return true;
	}
	boolean checkForVerb(String wordForCheck){
		if(wordForCheck.equals("building"))
			return true;
		if(wordForCheck.endsWith("ed")==true)
			return false;
		if(wordForCheck.endsWith("ing")==true)
			return false;
		if(wordForCheck.startsWith("dark")==true)
			return false;
		return true;
	}
	boolean checkForAdjectives(String wordForCheck){
		String adjectives[]={
			"other",
			"abrupt",
			"sandy",
			"acidic",
			"adorable",
			"adventurous",
			"aggressive",
			"aggressive",
			"agitated",
			"alert",
			"aloof",
			"amiable",
			"amused",
			"annoyed",
			"antsy",
			"anxious",
			"appalling",
			"appetizing",
			"apprehensive",
			"arrogant",
			"attractive",
			"average",
			"batty",
			"beefy",
			"bewildered",
			"biting",
			"big",
			"bitter",
			"bland",
			"blushing",
			"bored",
			"brave",
			"bright",
			"broad",
			"bulky",
			"burly",
			"cheecky",
			"cheerful",
			"chubby",
			"clean",
			"cloudy",
			"clueless",
			"clumsy",
			"colorful",
			"colossal",
			"combative",
			"comfortable",
			"condemned",
			"contemplative",
			"cooperative",
			"corny",
			"costly",
			"courageous",
			"crabby",
			"creepy",
			"cruel",
			"cumbersome",
			"cynical",
			"dangerous",
			"deceitful",
			"deep",
			"defiant",
			"delicious",
			"delightful",
			"despicable",
			"diminutive",
			"distinct",
			"distraught",
			"dizzy",
			"drab",
			"dull",
			"eager",
			"ecstatic",
			"elated",
			"elegent",
			"emaciated",
			"energetic",
			"enormous",
			"enthusiastic",
			"envious",
			"extensive",
			"exuberant",
			"fancy",
			"fantastic",
			"firce",
			"filhy",
			"flat",
			"floppy",
			"foolish",
			"frantic",
			"fresh",
			"friendly",
			"frothy",
			"funny",
			"fuzzy",
			"gaudy",
			"gentle",
			"ghastly",
			"giddy",
			"gigantic",
			"glamourous",
			"glorious",
			"gorgeous",
			"graceful",
			"greasy",
			"grieving",
			"gritty",
			"grotesque",
			"grubby",
			"grumpy",
			"handsome",
			"healthy",
			"happy",
			"helpful",
			"helpless",
			"high",
			"hollow",
			"homely",
			"horrific",
			"huge",
			"hungry",
			"hurt",
			"icy",
			"ideal",
			"immense",
			"impressionable",
			"intrigued",
			"irate",
			"irritable",
			"itchy",
			"jealous",
			"jittery",
			"jolly",
			"joyous",
			"juicy",
			"jumpy",
			"kind",
			"lackadaisical",
			"large",
			"lazy",
			"lethal",
			"little",
			"lively",
			"livid",
			"lonely",
			"loose",
			"lovely",
			"lucky",
			"ludicrous",
			"macho",
			"magnificent",
			"mammoth",
			"maniacal",
			"massive",
			"melancholy",
			"miniature",
			"minute",
			"mistaken",
			"misty",
			"moody",
			"motionless",
			"muddy",
			"mysterious",
			"narrow",
			"nasty",
			"naughty",
			"nervous",
			"nonchalant",
			"nonsensical",
			"nutritious",
			"nutty",
			"obedient",
			"oblivious",
			"obnoxious",
			"odd",
			"old-fashioned",
			"outrageous",
			"panicky",
			"perfect",
			"petite",
			"petty",
			"plain",
			"pleasant",
			"pmpous",
			"precious",
			"prickly",
			"proud",
			"pungent",
			"puny",
			"quaint",
			"quizzical",
			"ratty",
			"repulsive",
			"responsive",
			"ripe",
			"robust",
			"rotten",
			"rotund",
			"rough",
			"round",
			"salty",
			"sarcastic",
			"scant",
			"scary",
			"scrawny",
			"selfish",
			"shaggy",
			"shaky",
			"shallow",
			"sharp",
			"shiny",
			"short",
			"silky",
			"silly",
			"skinny",
			"slimy",
			"slippery",
			"small",
			"smarmy",
			"smiling",
			"smoggy",
			"smooth",
			"smug",
			"soggy",
			"solid",
			"sore",
			"sour",
			"spicy",
			"splendid",
			"spotless",
			"there",
			"slate",
			"steady",
			"steep",
			"sticky",
			"stormy",
			"stout",
			"straight",
			"strange",
			"strong",
			"substantial",
			"successful",
			"succulent",
			"superficial",
			"superior",
			"swanky",
			"sweet",
			"tart",
			"tasty",
			"teeny",
			"teeny",
			"tender",
			"tease",
			"terrible",
			"testy",
			"thankful",
			"thick",
			"thoughtful",
			"thoughtless",
			"tight",
			"timely",
			"tricky",
			"trite",
			"uneven",
			"unslightly",
			"upset",
			"uptight",
			"uneven",
			"unslightly",
			"vast",
			"victorious",
			"virtuous",
			"vivacious",
			"vivid",
			"wacky",
			"weary",
			"whimsical",
			"witty",
			"wobbly",
			"wonderful",
			"yummy",
			"zany",
			"zealous",
			"zippy"
		};
		for(String y:adjectives){
			if(wordForCheck.equals(y))
				return false;
		}
	return true;
	}
    boolean checkForConjunction(String wordForCheck){
    	String conj[]={"and",
    			       "but",
    			       "nor",
    			       "so",
    			       "for",
    			       "yet"
    	};
		for(String y:conj){
			if(wordForCheck.equals(y))
				return true;
		    }
		return false;
    	}  
}