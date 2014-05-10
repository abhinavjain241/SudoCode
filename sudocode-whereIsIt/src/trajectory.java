import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;
public class trajectory {
	String[] getTrajectory(String words[], String prep[]){
		int i=0;
		for(String x:words){
			//if(x.equals(prep[0]))
				if(compare(words, i, prep)==true)
			       	break;
		    i++;
		}
		LinkedList<String> list2=new LinkedList<String>();
		int numTraj=0,check=0;
		String[] traj=new String[5];
		traj[0]="undefined";
		noise noiseObject=new noise();
		for(int j=i; j>0;j--){
			if((j-2)>=0){
			if(words[j-2].equalsIgnoreCase("view") || words[j-2].equalsIgnoreCase("and")){
			    System.out.println();	
			}
			else{
			//System.out.println(words[i+1]);
			if(words[j-1].equals("with") || (words[j-1]+" "+words[j]).equals("made of") || words[j-1].equals("both") || noiseObject.checkForVerb(words[j-1])==false){
				   for(int l=1; l<5;l++)
				    	traj[l]=null;
				   traj[0]="undefined";
				   numTraj=0;	
				   check=0;
			       continue;
			        }   
			    }
			}
			if(check==0){
			   if(noiseObject.Noise(words[j-1].toLowerCase())==true){
				   if(words[j-1].equals("and"))
					   check=0;
				   else{
				       traj[numTraj++]=words[j-1];
				       // System.out.printf("this is traj : %s\n",traj[numTraj]);
				       check=1;
			         }
				  }
			  }
			if(check==1){
				if(words[j-1].equals("and")){
					check=0;
					}
			   }
		}
		return traj;  
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
}
