public class preposition {

	public String perpFind(String[] words){
		int count =0, numberOfWords;
		for(int i=0;i<words.length;i++){
			count=0;
			count=singleWordPreposition(words[i]);
			if(checkForCompoundPreposition(words[i])==true){
				if((i+1)<words.length)
				count=doubleWordPreposition(words[i]+" "+words[i+1], count);
				if((i+2)<words.length)
				count=threeWordPreposition(words[i]+" "+words[i+1]+" "+words[i+2], count);
				if((i+3)<words.length)
				count=fourWordPreposition(words[i]+" "+words[i+1]+" "+words[i+2]+" "+words[i+3], count);
			}
		 
		if(count!=0){
			switch(count){
		  	case 1:return words[i];
			case 2:return words[i]+" "+words[i+1];
			case 3:return words[i]+" "+words[i+1]+" "+words[i+2];
			case 4:return words[i]+" "+words[i+1]+" "+words[i+2]+" "+words[i+3];
			}
		  }	
		}
		return "no preposition";
	}
	public int singleWordPreposition(String wordForCheck) {
		// TODO Auto-generated method stub
		String oneWordPrep[]={
			    "abaft",
			    "aboard",
			    "about",
			    "above",
			    "absent",
			    "supporting",
			    "crossing",
			    "across",
			    "afore",
			    "after",
			    "against",
			    "along",
			    "alongside",
			    "amid",
			    "amidst",
			    "among",
			    "amongst",
			    "anenst",
			    "apropos",
			    "apud",
			    "around",
			    "as",
			    "aside",
			    "astride",
			    "at",
			    "athwart",
			    "atop",
			    "barring",
			    "before",
			    "behind",
			    "below",
			    "beneath",
			    "beside",
			    "besides",
			    "between",
			    "betwixt",
			    "beyond",
			    "but",
			    "by",
			    "circa",
			    "concerning",
			    "despite",
			    "down",
			    "during",
			    "except",
			    "excluding",
			    "failing",
			    "following",
			    //"for",
			    "forenenst",
			    //"from",
			    "given",
			    "in",
			    "including",
			    "inside",
			    "into",
			    "lest",
			    "like",
			    "mid",
			    "midst",
			    "minus",
			    "modulo",
			    "near",
			    "next",
			    "notwithstanding",
			    "o'",
			    //"of",
			    //"off",
			    "on",
			    "onto",
			    "opposite",
			    "out",
			    "outside",
			    "over",
			    "pace",
			    "past",
			    "per",
			    "plus",
			    "pro",
			    "qua",
			    "regarding",
			    "round",
			    "sans",
			    "save",
			    "since",
			    "than",
			    "through",
			    "thru",
			    "throughout",
			    "thruout",
			    "till",
			    "times",
			    "to",
			    "toward",
			    "towards",
			    "under",
			    "underneath",
			    "unlike",
			    "until",
			    "unto",
			    "up",
			    "upon",
			    "versus",
			    "via",
			    "vice",
			    "vis-à-vis",
			    //"with",
			    "within",
			    "without",
			    "worth"
			};
		for(String y:oneWordPrep){
			if(wordForCheck.equals(y))
				return 1;
		}
		return 0;
	}
    public int doubleWordPreposition(String stringForCheck, int count){
    	String twoWordPrep[]={"according to",
    			"next to",
    			"surrounded by",
    			"leaning on",
    			"leading up",
                "ahead of",
                "about to",
                "along with",
                "alongside of",
                "apart from",
                "as against",
                "lined up",
                "as between",
                "as for",
                "due to",
                "from above",
                "from behind",
                "from beneath",
                "from under",
                "from among",
                "going up",
                "inside of",
                "instead of",
                "in front",
                "out of",
                "outside of",
                "owing to",
                "previous to",
                "regardless of",
                "relating to",
                "relative to",
                "sitting around",
                "walking towards",
                "walking over",
                "what with"};
    	for(String y:twoWordPrep){
			if(stringForCheck.equals(y))
				return 2;
		}
       return count;	
    }
    public int threeWordPreposition(String stringForCheck, int count){
    	 String threeWordPrep[]={"as compared with",
    			 "at each side",
                 "by force of",
                 "on the right",
                 "on the left",
                 "by means of",
                 "by reason of",
                  "by virtue of",
                  "by way of",
                  "for fear of",
                  "for lack of",
                  "in accordance with",
                  "in addition to",
                  "in behalf of",
                  "in care of",
                   "in case of",
                   "in common with",
                   "in comparision to" ,
                   "in comparision with",
                   "in compliance with",
                   "in centre of",
                   "in connection with",
                   "in consequence of",
                   "in consideration of",
                   "in contrast to",
                   "in default of",
                   "in dereference to",
                   "in exchange for",
                   "in front of",
                   "in her left",
                   "in lieu of ",
                   "in opposition to",
                   "in place of",
                   "in preference to",
                   "in regard of",
                   "in search of",
                   "in terms of",
                   "in the left",
                   "on account of",
                   "on behalf of",
                   "on the right",
                   "on the left",
                   "on the top",
                   "on the bottom",
                   "in the centre",
                   "under cover of",
                    "with regard to",
                    "with reference to"};
    	 	for(String y:threeWordPrep){
    			if(stringForCheck.equals(y))
    				return 3;
    		}
    	return count;
    }
    public int fourWordPreposition(String stringForCheck, int count){
    	String fourWordPrep[]={"at the point of",
                "at the time of",
                "at the top of",
                "at the base of",
                "for the purpose of",
                "in the course of",
                "in the event of",
                "in the face of",
                "in the middle of",
                "on the part of",
                "on the point of",
                "along the right side",
                "with a view to",
                "with the intension of"
};
    	for(String y:fourWordPrep){
			if(stringForCheck.equals(y))
				return 4;
		}
    	return count;
    	
    }
    public boolean checkForCompoundPreposition(String wordForCheck){
    	  String word[]={"according",
    			  "leading",
    			  "sitting",
    			  "next",
    			  "about",
    			  "surrounded",
    			  "leaning",
    			  "at",
                  "going",
                  "ahead",
                  "along",
                  "alongside",
                  "apart",
                  "as",
                  "lined",
                  "at",
                  "by",
                  "in",
                  "due",
                  "except",
                  "for",
                  "from",
                  "on",
                  "out",
                  "outside",
                  "previous",
                  "walking",
                  "regardless",
                  "relative",
                  "round",
                  "short",
                  "under",
                  "with"
              };
    	 	for(String y:word){
    			if(wordForCheck.equals(y))
    				return true;
    		}
    	return false;
    }
    boolean checkForPreposition(String []words){
    	int count =0, numberOfWords;
    	noise noiseObject=new noise();
		for(int i=0;i<words.length;i++){
			if(count!=0)
				return true;
		//	if(noiseObject.checkForConjunction(words[i])==true)
			//	return false;
			count=singleWordPreposition(words[i]);

			if(checkForCompoundPreposition(words[i])==true){
				if((i+1)<words.length)
					count=doubleWordPreposition(words[i]+" "+words[i+1], count);
			   	if((i+2)<words.length)
				    count=threeWordPreposition(words[i]+" "+words[i+1]+" "+words[i+2], count);
				if((i+3)<words.length)
				   count=fourWordPreposition(words[i]+" "+words[i+1]+" "+words[i+2]+" "+words[i+3], count);
		 }
	  }
    	return false;
    }

}