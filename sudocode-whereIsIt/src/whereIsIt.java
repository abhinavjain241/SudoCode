import java.awt.List;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;
public class whereIsIt {
	public static void main(String[] args){
		Scanner fileScanner = null;
		PrintWriter x = null;
		int sentenceNumber=0;
		try {
		    fileScanner = new Scanner(new File("input.txt"));
		} catch (Exception e) {
		    e.printStackTrace();  
		}
		try{
			x=new PrintWriter("output.txt");
		}
		catch(Exception e){
		   e.printStackTrace();
		}
		while (fileScanner.hasNextLine()) {
			sentenceNumber++;
	       try{ String trajectory, landmark, end = null;
	        String word = fileScanner.nextLine();
	        String word1=word.replace(",", " and");
	        String word3=word1.replace("."," .");
	        String word4=word3.replace("  ", " ");
	        String word2=word4.replace("  and", " and");
	        String[] array2 = word2.split(" ");
	        LinkedList<String> list1=new LinkedList<String>();
	        for(int q=2;q<array2.length;q++){
	        	System.out.println("word"+array2[q]);
	        	if(array2[q].equals("(") || array2[q].equals(")"))
	        		continue;
	    	    list1.add(array2[q]);
	        }
	        //remove for "."
	        array2=list1.toArray(new String[list1.size()]);
	        int loop=0,checkNewPrep=0, position=0;
	        LinkedList<String> finalValues=new LinkedList<String>();
	        while(loop==0){
	    	    for(String j: array2)
	    		    System.out.print("\t"+j);
	    	    System.out.println();
	    	    checkNewPrep=0;
	    	    position=0;
	    	    finalValues.clear();
	    	    finalValues.add("preposition");
	            preposition prepObject=new preposition();
	            String prep= prepObject.perpFind(array2);
	            finalValues.add(prep);
	            finalValues.add("trajectory");
	            if(prep.equals("no preposition")){
	            	String []answer={"preposition","no preposition","trajectory","undefined","landmark","undefined"};
	            	writeToFile(x,answer,sentenceNumber);
	    	        break;
	            }
	            String prepwords[]=prep.split(" ");
		        trajectory trajobject=new trajectory();
		        String straj[]=trajobject.getTrajectory(array2, prepwords);
		        for(String w:straj){
			        if(w==null)
				        break;
			        finalValues.add(w);
		         }
		        finalValues.add("landmark");
	            landmark landObject=new landmark();
	            String[] sland= landObject.getLandmark(array2, prepwords);
	            for(String w:sland){
	    	        if(w==null)
	    		        break;
			        if(w.equals("newprep")){
			    	    checkNewPrep=1;
			    	    continue;
			        }
	    	        if(w.equals("end")){
	    	    	    loop=1;
	    	    	    continue;
	    	        }
	    	        end=w;
	    	        finalValues.add(w);
	            }
	            randomcases random=new randomcases();
	            if(straj[0].equals("undefined")){
	        	    finalValues=random.trajvales(array2, prepwords, finalValues);
	         	}
	            if(end.equals("undefined")){
	        	    finalValues=random.values(array2, prepwords,finalValues);
	        	    end=prepwords[prepwords.length-1];
	            }
	            String []answer=finalValues.toArray(new String[finalValues.size()]);
	            writeToFile(x,answer, sentenceNumber);
	           // for(String w:answer){
	    	     //    System.out.println(w);
	            // }
	            position=0;
	            if(checkNewPrep==1){
	        	     for(String w:array2){
	        		       if(w.equals(end)){
	        			       System.out.println(array2[position+3]);
	        			       if(array2[position+3].equals("with"))
	        				       list1.subList(0, position+3).clear();
	        			       else
	        			           list1.subList(0, position+2).clear();
	        			       array2=list1.toArray(new String[list1.size()]);
	        			       break;
	        		          }
	        		      position++;
	        	        }
	               }
	          }
	       }
	       catch(Exception e){
	    	   String answer[]=new String[6];
	    	   answer[0]="preposition";
	    	   answer[1]="no preposition";
	    	   answer[2]="trajectory";
	    	   answer[3]="undefined";
	    	   answer[4]="landmark";
	    	   answer[5]="undefined";
	    	   writeToFile(x,answer, sentenceNumber);
	       }
       }
		x.close();
   }
	public static void writeToFile(PrintWriter x, String[] answer, int sentenceNumber){
		int count=0,added=0;
		//if(sentenceNumber==1)
			//x.print(" "+sentenceNumber+".");
		for(String word:answer){
			switch(word){
			case "preposition":x.println();
			                   added=0;
				               x.format("\n %d.", sentenceNumber);
				               count=0;
			                   break;
			case "trajectory":count=1;
			                  added=0;
			                  break;
			case "landmark":count=2;
			                break;
			default:x.format("\"%s\"", word);
			}
		}
		if (count==1)
			x.format("\"undefined\"");
	}
}
//After getting a landmark check for the the preposition in the remaining sentence.. 
// algo is:- trajectory is undefined and there is a landmark, if u get so then return the value .!
// add case for "made of" .!
// sentences with paranthesis