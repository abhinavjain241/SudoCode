import java.util.Arrays;
import java.util.LinkedList;
public class randomcases {
	LinkedList values(String []words, String prepwords[], LinkedList finalValues){
		int numValues=0;
		LinkedList<String> finlValues=new LinkedList<String>();
		finlValues.add("preposition");
		String prep=prepwords[0];
		for(int k=1; k <prepwords.length;k++)
		      prep=prep+" "+prepwords[k];
		finlValues.add(prep);
		finlValues.add("trajectory");
		numValues++;
		noise noiseObject=new noise();
		int i=0,check=0;
		for(String x:words){
			//if(x.equals(prep[0]))
				if(compare(words, i, prepwords)==true)
			       	break;
		    i++;
		}
		if(words[i+prepwords.length].equals(".") || noiseObject.checkForConjunction(words[i+prepwords.length])==false){
		   for(int j=i; j>0;j--){
			  // System.out.println(words[j-1]);
		      if(words[j-1].equals("with")){
		    	  check=0;
		    	  finlValues.add("landmark");
		    	  continue;
		      }
		      if(noiseObject.Noise(words[j-1])==true){
		    	  if(words[j-1].equals("and")){
		    		  check=0;
		    	   }
		    	  else if(check==0){
		    		  finlValues.add(words[j-1]);
		              check=1;
		              }
		     }
		  }
		 return finlValues;
		}
		return finalValues;
	}
	LinkedList trajvales(String[] words, String[] prepwords, LinkedList finalValues){
		int numValues=0;
		LinkedList<String> finlValues=new LinkedList<String>();
		finlValues.add("preposition");
		String prep=prepwords[0];
		for(int k=1; k <prepwords.length;k++)
		      prep=prep+" "+prepwords[k];
		finlValues.add(prep);
		noise noiseObject=new noise();
		int check=0;
		finlValues.add("trajectory");
		for(int k=prepwords.length;k<words.length;k++){
				if(words[k].equals("with") ){
					finlValues.add("landmark");
					finlValues.add("undefined");
					return finlValues;
					}
				if(words[k].equals("and")){
					check=0;
				    continue;
				}
				else if(check==0 && noiseObject.Noise(words[k])==true){	
					finlValues.add(words[k]);
					check=1;
				}
			}
		return finalValues;
	}
	  boolean compare(String[] words, int i, String[] prep){
		  String check=words[i];
		  String pre=prep[0];
		  if(prep.length>1){
		      for(int j=1 ; j <prep.length;j++){
		     	  check=check+" "+words[i+j];
		     	 pre=pre+" "+prep[j];
		      }
		  } 
		  if(check.equals(pre))
			   return true;
		  return false;
	  }
    String []checkAtEnd(String[] words, String land[], int x){
    	preposition prepObject=new preposition();
    	LinkedList<String> list3=new LinkedList<String>();
    	LinkedList<String> l1=(LinkedList<String>) Arrays.asList(land);
    	if(prepObject.checkForPreposition(words)==true){
    		String prep=prepObject.perpFind(words);
    		String p[]=prep.split(" ");
    		
    	}
    	return land;
    }
}

