import java.util.LinkedList;

public class landmark{
	String[] getLandmark(String[] words, String[] prep){
		int i=0;
		for(String x:words){
			//if(x.equals(prep[0]))
				if(compare(words, i, prep)==true)
				   break;
		    i++;
		    //System.out.println(i);
		}
		//System.out.println("the last word is :"+words[i]);
		i+=prep.length-1;
		int numLand=0,randomCase=0, check=0;
		String land[]=new String[10];
		noise noiseObject=new noise();
		preposition prepObject=new preposition();
		String str="", addTraj="";
		land[0]="undefined";
		int addTrajectory=0;
		LinkedList<String> list3=new LinkedList<String>();
		String randomCases[];
		randomcases random=new randomcases();
		for(int k=i+1; k <words.length;k++){
			    if(numLand==1 && randomCase==0){
			       randomCase=1;
			      // if(words)
			      if(prepObject.checkForCompoundPreposition(words[k])==true || prepObject.singleWordPreposition(words[k])==1){
			    	for(int g=k;g<words.length;g++)
			    	       list3.add(words[g]);
			    	if(noiseObject.Noise(words[k-1]) && noiseObject.checkForConjunction(words[k-1])==false){
			    		addTrajectory=1;
			    		addTraj=words[k-1];
			    	}
			    	randomCases=list3.toArray(new String[list3.size()]);
			    	for(int g=k;g<words.length;g++){
			    		if(prepObject.checkForPreposition(randomCases)==true){
			        		String prepo=prepObject.perpFind(randomCases);
			        		String p[]=prepo.split(" ");
			        		//g=g+p.length;
			        		  land[numLand++]="preposition";
			        		  land[numLand++]=prepo;
			        		  if(addTrajectory==1){
			        			  land[numLand++]="trajectory";
			        			  land[numLand++]=addTraj;
			        		  }
			        		  land[numLand++]="landmark";
			        		  land[numLand]="undefined";
			        		for(int h=g+p.length;h<words.length;h++){
			        			if(words[h].equals(".")){
			        				land[numLand]="end";
			        				return land;
			        			}
			        		      if(noiseObject.Noise(words[h])==true){
			        		           land[numLand++]=words[h];
			        		           land[numLand++]="end";
			        		           return land;
			        		      }
			        	    }
			        	  land[numLand]=null;
			           	}
			    	 }
			      }
			   }
			    if(words[k].equals("with"))
			    	continue;
			    if(noiseObject.Noise(words[k])==false && noiseObject.checkForConjunction(words[k])==false)
			    	   continue;
			    if(words[k].equals(".")){
			    	if(numLand==0)
			    		numLand=1;
			    	land[numLand++]="end";
			    	return land;
			    }
			    if(words[k].equals("and")){
			            str=words[k+1];
			            for(int l=k+2;l<words.length;l++)
				            str=str+" "+words[l];
				        String[] strs=str.split(" ");
		                if(prepObject.checkForPreposition(strs)==true){
		                	  if(numLand==0){
		                		  numLand=2;
		                		  land[1]=prep[prep.length-1];
		                	  }
		     	              land[numLand++]="newprep";
				              return land;
			     	     }
     		            else{
				            	 check=0;
				        	     continue;
				            }
				        }
	   			    else{
				          if(noiseObject.checkForConjunction(words[k])==true){
					           str=words[k];
					           for(int l=k+1;l<words.length;l++)
						            str=str+" "+words[l];
				               String[] strs=str.split(" ");
			                   if(prepObject.checkForPreposition(strs)==true){
		 		                     land[numLand++]="newprep";
				               return land;
				 	         }
			              }
			       }
		     	  if(check==0 ){	  
					    land[numLand++]=words[k];
				        check=1;							 
		       }
		}
		if(numLand==0){
	    		numLand=1;
		    	land[0]="undefined";
		}
		land[numLand++]="end";
		return land;
		//String strwords[]=str.split(" ");
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
  boolean checkForNewprep(){
	  return false;
  }
}