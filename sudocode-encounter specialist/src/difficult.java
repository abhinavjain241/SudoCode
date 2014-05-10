import java.io.File;
import java.io.PrintWriter;

public class difficult {
  private static int direction=0;
  public void justDoIt(int position_x,int position_y, int [][]grid, int color, int numTerrorists, int [][]path, int []function){
	int solution[]=new int[path[0].length];
	int add=0;
	int k=0;
	int clubbing[][];
	for(int l=0;l<path[0].length;l++){
	     if(path[0][l]/100000!=color && path[0][l]%10!=0){
	    	  solution[add++]=(path[0][l]/100000)*100+path[0][l]%10;
	     }	
	   }
	int i;
	//in case 1:::::
    for(i=0; i<add;i++){
    	if(solution[add]%10!=0){
    		if(solution[add]/100==color)
    			break;
    	}
    }
    if(i==add){
    	clubbing=case1(solution, color, function);
       }
    // now case 1 failed, case 2::::
    int check2=0;
    for( i=0;i<solution.length;i++){
    	if(path[0][i]==0)
    		break;
    	if(path[0][i]/100000!=color && path[0][i]%10==0){
    		if(path[0][i+1]%10==0 || path[0][i+1]/100000!=color)
    			check2=1;
    	  }	
    	}
    if(check2==0){
    	case2(solution, color, function);
    }
    
	}
  public int [][]case2(int []solution, int color, int []functions){
	  int max=functions[0];
	  for(int i=1;i<functions.length;i++){
		  if(max<functions[i])
			  max=functions[i];
	  }
    
	int add;
	int color1 = add=0;
	  int [][]code=new int[functions.length][max];
	  code[0][0]=400;
	  if(functions[1]>=3){
		  for (int i=0;i<solution.length;i++){
			  if(solution[i]==0)
				  break;
			  if(solution[i]/100000!=color){
				  if(solution[i]/100000!=color1 || add!=2){
				  add++;
				  color1=solution[i]/100000;
				  code[0][add]=solution[i]/100000*100+10+1;
				  code[1][2*add-2]=solution[i]/100000*100;
				  code[1][2*add-1]=solution[i+1]/100000+solution[i+1]%10;
			    }
		    }			
		  }
		  code[0][add+1]=-1;
		  write(code,2);
	  }
	  add=0;
	  code[0][0]=400;
	  int adding=1, toAdd;
	  for(int i=0; i <solution.length;i++){
		  if(solution[i]==0)
			  break;
		  if(solution[i]/100000!=color){
			  if(solution[i]/100000!=color1 || add!=2){
			  add++;
			  code[0][add]=solution[i]/100000*100+10+add;
			  code[add][0]=solution[i]/100000*100;
			  code[add][1]=solution[i+1]/100000*100+solution[i]%10;
			  toAdd=(solution[i]/100000)*100+10+adding;
		  }}
		  code[adding][0]=color1*100;
	  }
	  write(code, 3);
	  return code;
  }
  public int[][] case1(int []solution, int color, int []functions){
	  System.out.println("in case 1");
	  int checkColor=0;
	  int max=functions[0];
	  int direct1 = 0, direct2=0,add=1;
	  for(int c=1; c<functions.length;c++){
		  if(max<functions[c])
			  max=functions[c];
	  }
      int code[][]=new int[functions.length][max];
      code[0][0]=400;
      code[1][0]=400;
      for(int i=0;i<solution.length;i++){
    	  if(solution[i]==0)
    		  continue;
    	  if(solution[i]/100!=color && solution[i]%10!=0){
    		  direct1=solution[i];
    		  if(solution[i]%10==1){
    			  add++;
    			  direct2=direct1+1;
    			  break;
    		  }
    		  else{
    			  add++;
    			  direct2=direct1-1;
    			  break;
    		  }
    	  }
      }
      if(color+direct1/100==3)
    	  checkColor=3;
      else if(color+direct1/100==4)
    	  checkColor=2;
      else if(color+direct1/100==5)
    	  checkColor=1;
     // System.out.println(direct1+" " +direct2);
      code[0][1]=direct1;
      code[1][1]=direct2;
      code[0][2]=checkColor*100+10+1;
      code[1][2]=checkColor*100+10+0;
      code[0][3]=-1;
      code[1][3]=-1; 
      write(code, 2);
      return code;
  }
public static int[] primary_func(int position_x, int position_y, int[][] grid, int num_terrorists, int [][]function, int maxColoredTile){
		int loop=0,killed=0;
		int x_added[]=new int[num_terrorists];
		int y_added[]=new int[num_terrorists];
		//System.out.println("in primary function");
		int tileColor=grid[position_x][position_y];
		int x=position_x , y=position_y;
		int forReturn[]=new int[6];
		int l=0;
		int func=0;
		while(loop==0){
			l++;
			if(l>=grid[0].length*grid[0].length){
				forReturn[0]=-1;
				forReturn[1]=killed;
				
				break;
			}
			for(int i=0; i<function.length;i++){
				System.out.println(function[func][i]);
				if(function[func][i]==-1)
					continue;
				 switch(function[func][i]/100){
				 case 3:if((function[func][i]/100)/10==5){
					        func=function[func][i]%100;
					        break;
				        }
					     if(grid[x][y]%10==1){
					        if(checkToAdd(x_added, y_added, x,y,killed)==1){
					        	   System.out.println("killed a terror");
					        	   killed++;
					        	   x_added[killed-1]=x;
							       y_added[killed-1]=y;
					        }  
			            }
				       if(change_x(x,y,grid)==0 || change_y(x,y,grid)==0){
			        	  if(function[func][i]%100==0){
		                    forReturn[0]=-1;
		                    forReturn[1]=killed;
		                    forReturn[2]=x;
		                    forReturn[3]=y;
		                    return forReturn;					            
	                       }
			        	}
				        if(grid[x][y]/10==3){  
				        	if(function[func][i]%100==0){
					           x=change_x(x,y,grid);
					           y=change_y(x,y,grid);
					           System.out.println(function[func][i]/100+"\t"+x+"\t"+y+"\t"+function[i]+"\t"+direction);
				               }
		                else if(function[func][i]%100==1 || function[func][i]%100==2){
		                	changeDirection(function[func][i]%100, direction);
		                	System.out.println(function[func][i]/100+"\t"+x+"\t"+y+"\t"+function[func][i]+"\t"+"direction");
		                   }
				        }
				        break;
				 case 2:if((function[func][i]/100)/10==5){
				           func=function[func][i]%100;
				           break;
			            }
					    if(grid[x][y]%10==1){
				        if(checkToAdd(x_added, y_added, x,y, killed)==1){
				        	   killed++;
				        	   x_added[killed-1]=x;
						       y_added[killed-1]=y;
				             } 
				        }
				        if(change_x(x,y,grid)==0 || change_y(x,y,grid)==0){
				        	if(function[func][i]%100==0){
			                   forReturn[0]=-1;
			                   forReturn[1]=killed;
			                   forReturn[2]=x;
			                   forReturn[3]=y;
			                   return forReturn;					            
		                     }
				        }
					    if(grid[x][y]/10==2){
					    
					    	if(function[func][i]%100==0){
					           x=change_x(x,y,grid);
					           y=change_y(x,y,grid);
					           //System.out.print("\t\t"+"on green tile"+"\n");
					           System.out.println(function[func][i]/100+"\t"+x+"\t"+y+"\t"+function[i]+"\t"+direction);
				           }
					    else if(function[func][i]%100==1 || function[func][i]%100==2){
					    	changeDirection(function[func][i]%100, direction);
					    	System.out.println(function[func][i]/100+"\t"+x+"\t"+y+"\t"+function[i]+"\t"+direction);
					       }
					    }
				        break;
				 case 1:if((function[func][i]/100)/10==5){
				            func=function[func][i]%100;
				            break;
			            }
					    if(grid[x][y]%10==1){
				        if(checkToAdd(x_added, y_added, x,y,killed)==1){
				        	   killed++;
				        	   x_added[killed-1]=x;
						       y_added[killed-1]=y;
				            }  
				         }
				        if(change_x(x,y,grid)==0 || change_y(x,y, grid)==0){
				        	if(function[func][i]%100==0){
			                   forReturn[0]=-1;
			                   forReturn[1]=killed;
			                   forReturn[2]=x;
			                   forReturn[3]=y;
			                   return forReturn;					            
		                      }
				        }
					    if(grid[x][y]/10==1 ){
					    
					    	if(function[func][i]%100==0){
					           x=change_x(x,y, grid);
					           y=change_y(x,y, grid);
					           //System.out.print("\t\t"+"on blue tile"+"\n");
					           System.out.println(function[func][i]/100+"\t"+x+"\t"+y+"\t"+function[i]+"\t"+direction);
				            }
					    	if(function[func][i]%100==1 || function[func][i]%100==2){
					    		changeDirection(function[func][i]%100, direction);
					    		System.out.println(function[func][i]/100+"\t"+x+"\t"+y+"\t"+function[func][i]+"\t"+direction);
					    		}
					    	}
					        break;
				 case 4:if(change_x(x,y,grid)==0 || change_y(x,y,grid)==0){
			               if(function[func][i]%100==0){
			                   forReturn[0]=-1;
			                   forReturn[1]=killed;
			                   forReturn[2]=x;
			                   forReturn[3]=y;
			                   return forReturn;					            
		                 }
			               }
		               if(function[func][i]%100==0){
			                x=change_x(x,y,grid);
			                y=change_y(x,y,grid);
		                }
		               else if(function[func][i]%100==1 ||  function[func][i]%100==2){
		         	            changeDirection(function[func][i]%100, direction);
		                }
		               System.out.println(function[func][i]/100+"\t"+x+"\t"+y+"\t"+function[func][i]+"\t"+direction);
			           break;
				   } 
			  }
			if(killed==num_terrorists){
				System.out.print("killed all");
				forReturn[0]=1;
				forReturn[1]=killed;
				return forReturn;
			 }
	    }
		return forReturn;	
	}
private static int checkToAdd(int[] x_added, int[] y_added, int x, int y, int killed) {
	for(int i=0; i <killed;i++){
		if(x_added[i]==x && y_added[i]==y)
			return 0;
	}
	// TODO Auto-generated method stub
	return 1;
}
static int change_x(int x,int y,int [][]grid){
	  int addendum = 0;
			switch(direction){
			case 0:addendum=-1;
			       break;
			case 1:addendum=0;
			       break;
			case 2:addendum=1;
			       break;
			case 3:addendum=0;
			       break;
			 }
			if(grid[x+addendum][y]!=0){
				return x+addendum;
			}
			else{
				//System.out.println("break here at "+x+"     "+ grid[x+addendum][y]);
				return 0;
		 }
	  }
static int change_y(int x,int y,int[][] grid){
		int addendum = 0;	
				switch(direction){
				case 0:addendum=0;
				       break;
				case 1:addendum=1;
				       break;
				case 2:addendum=0;
				       break;
				case 3:addendum=-1;
				       break;
				 }
				if(grid[x][y+addendum]!=0){
					 return y+addendum;
				 }
				else{
					return 0;
				}
		 
	}
static void changeDirection(int move, int value){
	if(move==1){
	  switch(direction){
      case 0:direction=1;
	         break;
	  case 1:direction=2;
	         break;
	  case 2:direction=3;
	         break;
	  case 3:direction=0;
	         break;
		}
	}
	if(move==2){
	   switch(direction){
       case 0:direction=3;
	          break;
	   case 1:direction=0;
	          break;
	   case 2:direction=1;
	          break;
	   case 3:direction=2;
	          break;
		 }
	  }
   } 
public void write(int [][]code, int num){
	try{
		String toWrite="";
		PrintWriter wrt;
		wrt=new PrintWriter(new File("output.txt"));
	for(int j=0;j<num;j++){
		toWrite="";
		for(int i=0; i<code[j].length; i++){
		    toWrite+="";
		    if(code[j][i]==-1)
		    	toWrite+=" F1";
			//System.out.println(code[j][i]);
			if(code[j][i]!=0){
				switch(code[j][i]/100){
				case 4:toWrite+=" ";
				       break;
				case 3:toWrite+=" R";
				       break;
				case 2:toWrite+=" G";
				       break;
				case 1:toWrite+=" B";
				       break;
				}
				if((code[j][i]/10-(code[j][i]/100)*10)==1){
				//	System.out.println("to add this exception"+code[j][i]%100);
			    switch(code[j][i]%10){
			    case 0:toWrite+="F1";
			           break;
			    case 1:toWrite+="F2";
			           break;
			    case 2:toWrite+="F3";
			           break;
			    case 3:toWrite+="F4";
			           break;
			    case 4:toWrite+="F5";
			           break;
			        }		
				 }
				else{
				switch(code[j][i]%100){
				case 0:toWrite+="F";
				       break;
				case 1:toWrite+="R";
				       break;
				case 2:toWrite+="L";
				       break;
				 }
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
	     //	System.out.println(toWrite);
	        wrt.printf("%s",toWrite);
	        wrt.println();
	}
	wrt.close();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
	
}
//0-forward
//1-right
//2-left
//3-u-turn
//when on same tile its counting the num of terrorists as more .! name = new (arguments);
//now goin to compound functions

}
