
public class difficult {
  private static int direction=0;
  public void justDoIt(int position_x,int position_y, int [][]grid, int num_terrorists, int color, int numTerrorists, int [][]path){
		
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
			for(int i=0; i <function.length;i++){
				if(function[func][i]==-1)
					continue;
				 switch(function[func][i]/100){
				 case 5:func=500 +grid[x][y]%10;
				        break;
				 case 3:if(grid[x][y]%10==1){
					        if(checkToAdd(x_added, y_added, x,y,killed)==1){
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
				 case 2:if(grid[x][y]%10==1){
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
				 case 1:if(grid[x][y]%10==1){
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

//0-forward
//1-right
//2-left
//3-u-turn
//when on same tile its counting the num of terrorists as more .! name = new (arguments);
//now goin to compound functions

}
