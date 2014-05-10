import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Scanner;
import java.io.*;

public class main_encounter {
	public  PrintWriter x;
	public static void main(String[] args){
		Scanner fileScanner = null;
		try {
		    fileScanner = new Scanner(new File("input.txt"));
		} catch (Exception e) {
		    e.printStackTrace();  
		}	
		//sPrintWriter
		int position_x = 0,position_y=0;
		   int colorTile[]=new int[4];
		   int loop=0;
		   int distance=1;
		   colorTile[0]=0;
		   colorTile[1]=0;
		   colorTile[2]=0;
		   colorTile[3]=0;
		   int n,i=1,j=1, getting=0;
		   n=Integer.parseInt(fileScanner.nextLine());
		   int numOFTerrorists=0;
		   int gridRepresentation[][]=new int[n+2][n+2];
		   for(int k=0;k<n+2;k++){
			   for(int l=0;l<n+2;l++){
				   gridRepresentation[k][l]=0;
			   }
		   }
		   while(fileScanner.hasNextLine()){
			   for( i=1; i <=n+1;i++){
				     if(i==n+1){
				    	 loop=1;
				    	 break;
				     }
                     j=1;
			         String gridElement=fileScanner.nextLine();
			         gridElement=gridElement.replace("  ", " ");
			         String gridElements[]=gridElement.split(" ");
			         for(String grid:gridElements){
			         switch(grid){
			         case "B":gridRepresentation[i][j]=10;
			                  colorTile[(gridRepresentation[i][j]/10)-1]++;
			                  j++;
	                          break;
			         case "G":gridRepresentation[i][j]=20;
			                  colorTile[(gridRepresentation[i][j]/10)-1]++;
	                          j++;
	                          break;
			         case "R":gridRepresentation[i][j]=30;
			                  colorTile[(gridRepresentation[i][j]/10)-1]++;
	                          j++;
			                  break;
			         case "BT":gridRepresentation[i][j]=11;
			                   colorTile[(gridRepresentation[i][j]/10)-1]++;
	                           j++;
	                           numOFTerrorists++;
			                   break;
		       	     case "GT":gridRepresentation[i][j]=21;
			                   colorTile[(gridRepresentation[i][j]/10)-1]++;
			                   numOFTerrorists++;
	                           j++;
			                   break;
			         case "RT":gridRepresentation[i][j]=31;
			                   colorTile[(gridRepresentation[i][j]/10)-1]++;
			                   numOFTerrorists++;
			                   j++;
	                           break;
			         case "BSL":distance=setDirection(grid);
			                    position_x=i;
	                            position_y=j;
		                        gridRepresentation[i][j]=10;
	                            colorTile[(gridRepresentation[i][j]/10)-1]++;
                                j++;
                                break;
			         case "BSR":distance=setDirection(grid);
	                            position_x=i;
                                position_y=j;
                                gridRepresentation[i][j]=10;
                                colorTile[(gridRepresentation[i][j]/10)-1]++;
                                j++;
                                break;
			         case "BSU":distance=setDirection(grid);
	                            position_x=i;
                                position_y=j;
                                gridRepresentation[i][j]=10;
                                colorTile[(gridRepresentation[i][j]/10)-1]++;
                                j++;
                                break;
			         case "BSD":distance=setDirection(grid);
	                            position_x=i;
                                position_y=j;
                                gridRepresentation[i][j]=10;
                                colorTile[(gridRepresentation[i][j]/10)-1]++;
                                j++;
                                break;
			         case "GSU":distance=setDirection(grid);
	                            position_x=i;
                                position_y=j;
                                gridRepresentation[i][j]=20;
                                colorTile[(gridRepresentation[i][j]/10)-1]++;
                                j++;
                                break;
			         case "GSD":distance=setDirection(grid);
	                            position_x=i;
                                position_y=j;
                                gridRepresentation[i][j]=20;
                                colorTile[(gridRepresentation[i][j]/10)-1]++;
                                j++;
                                break;
			         case "GSL":distance=setDirection(grid);
	                            position_x=i;
                                position_y=j;
                                gridRepresentation[i][j]=20;
                                colorTile[(gridRepresentation[i][j]/10)-1]++;
                                j++;
                                break;
			         case "GSR":distance=setDirection(grid);
	                            position_x=i;
                                position_y=j;
                                gridRepresentation[i][j]=20;
                                colorTile[(gridRepresentation[i][j]/10)-1]++;
                                j++;
                                break;
			         case "RSL":distance=setDirection(grid);
	                            position_x=i;
                                position_y=j;
                                gridRepresentation[i][j]=30;
                                colorTile[(gridRepresentation[i][j]/10)-1]++;
                                j++;
                                break;
			         case "RSR":distance=setDirection(grid);
	                            position_x=i;
                                position_y=j;
                                gridRepresentation[i][j]=30;
                                colorTile[(gridRepresentation[i][j]/10)-1]++;
                                j++;
                                break;
			         case "RSU":distance=setDirection(grid);
	                            position_x=i;
                                position_y=j;
                                gridRepresentation[i][j]=30;
                                colorTile[(gridRepresentation[i][j]/10)-1]++;
                                j++;
                                break;
			         case "RSD":distance=setDirection(grid);
	                            position_x=i;
                                position_y=j;
                                gridRepresentation[i][j]=30;
                                colorTile[(gridRepresentation[i][j]/10)-1]++;
                                j++;
                                break;
			        
	                 default :colorTile[3]++;
	        	              gridRepresentation[i][j++]=0;
	        	              break;
			            } 
			       }
	    	}
			   if(loop==1){
				   break;
			   }
		   }
		   int checkDelete=0;
		   for( int m=1;m<=n;m++){
			   checkDelete=0;
			   for(int l=1;l<=n;l++){
				  if(gridRepresentation[m][l]!=0)
					  checkDelete++;
			   }
			   if(checkDelete>n/4){
				   colorTile[3]-=n;
			   }
		   }
		 //System.out.println(gridRepresentation[position_x][position_y]);
		int p=Integer.parseInt(fileScanner.nextLine());
		int numberOfOperations[]=new int[p];
		for(int k=0;k<p;k++){
		
			numberOfOperations[k]=Integer.parseInt(fileScanner.nextLine());
		}        
		evaluator evaluate=new evaluator();
		evaluate.start(position_x, position_y, gridRepresentation, numOFTerrorists, numberOfOperations, colorTile,n, distance);
		// inputting part is done. !
		
	}
	private static int setDirection(String grid) {
		if(grid.endsWith("L"))
			return 3;
		else if(grid.endsWith("R"))
			return 1;
		else if(grid.endsWith("U"))
			return 0;
		else if(grid.endsWith("D"))
			return 2;
		// TODO Auto-generated method stub
		return 0;
	}

	public static void writeToFile(int []function ){
		//System.out.println("here in writing");
		String toWrite="";
		PrintWriter wrt;
		try {
			wrt=new PrintWriter("output.txt");
			for(int i=0; i<function.length; i++){
			    toWrite+="";
			    if(function[i]==-1)
			    	toWrite+=" F1";
				//System.out.println(function[i]);
				if(function[i]!=0){
					switch(function[i]/100){
					case 4:toWrite+=" ";
					       break;
					case 3:toWrite+=" R";
					       break;
					case 2:toWrite+=" G";
					       break;
					case 1:toWrite+=" B";
					       break;
					}
					switch(function[i]%100){
					case 0:toWrite+="F";
					       break;
					case 1:toWrite+="R";
					       break;
					case 2:toWrite+="L";
					       break;
					 }
				   }
				else{
				   toWrite+=" 0";
				  }
			//	System.out.print("  "+toWrite);
				//wrt.format(" %s", toWrite);
				}
			toWrite.replace("  ", "");
			 //  toWrite+=" F1";
		     	//System.out.println(toWrite);
		        wrt.printf("%s",toWrite);
		        wrt.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.exit(0);
		
	}
}