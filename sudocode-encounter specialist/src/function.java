public class function extends main_encounter {
public static int direction=0;
public static int done=0, check=0;
public void simpleFunction(int position_x, int position_y, int[][] grid, int num_terrorists, int numOperations, int maxcoloredTile, int gridSize, int directio){
		direction=directio;
		int startPos_x=position_x;
		int startPos_y=position_y;
		int primary[]=new int[numOperations+1];
		int end=1; 
		for(int i=0; i<numOperations;i++){
			primary[i]=0;
		}
		if(checkLine(grid, position_x,position_y,direction)==0){
			//System.out.println("here its ");
			setDirection(grid, position_x, position_y);
			if((direction-directio)>0){
			      primary[done++]=(grid[position_x][position_y]/10)*100+1;
			      primary[done++]=400;
			      primary[done]=-1;
			}
			else if((direction-directio)<0){
				  primary[done++]=(grid[position_x][position_y]/10)*100+2;
				  primary[done++]=400;
			      primary[done]=-1;
			}
		}
		else{
			primary[done++]=400;
			primary[done]=-1;
		}
		int []out=new int[4];
		int maxkill=0;
        out[2]=startPos_x;
        out[3]=startPos_y;
        direction=directio;
        int loop=0;
		while(loop<numOperations+2){
		    loop++;//direction=directio;
		    out=primary_func(startPos_x, startPos_y,grid, num_terrorists,primary,maxcoloredTile);
		    System.out.println(primary[1]+"   "+primary[2]+"   "+primary[3]+"  "+primary[4]+"   "+primary[5]); 
		    if(out[1]==num_terrorists)
		    {
		    	writeToFile(primary);
		    	break;
		    }
		       System.out.println(out[1]);//	break;
		    primary=setValue(grid, primary,out[2],out[3], maxcoloredTile); 
	    }
	}
public int[] setValue(int [][]grid, int []primary,int x, int y, int maxColored){
	if(check==1){
		System.out.print(" ");
      }
	else{
		System.out.println(x+"    "+y);
		int move=setMove(grid, x,y, maxColored, primary);
		//System.out.println("move is "+move);
		if(move%100==3){
			if(primary[done-1]/100==move/100){
				switch(primary[done-1]%100){
				case 1:primary[done-1]++;
				       return primary;
				case 2: primary[done-1]--;
				       return primary;
				}
			}	
			if((done+3)<=primary.length){
				primary[done++]=move-1;
				primary[done++]=move-1;
				primary[done]=-1;
		//		System.out.println("done is "+done);
			}
		}
		else{
		    primary[done++]=move;
		    primary[done]=-1;
	        }
		}
	return primary;
}
private void setDirection(int [][]grid, int x, int y){
	 int x_addendum=0;
	 int y_addendum=0;
	 //right
	 switch(direction){
	 case 0:x_addendum=0;
	        y_addendum=1;
	        break;
	 case 1:x_addendum=1;
	        y_addendum=0;
	        break;
	 case 2:x_addendum=0;
	        y_addendum=-1;
	        break;
	 case 3:x_addendum=-1;
	        y_addendum=0;
	        break;
	 }
	 if(grid[x+x_addendum][y+y_addendum]!=0){
		 switch(direction){
		 case 0:direction= 1;
		        break;
		 case 1:direction= 2;
		        break;
		 case 2:direction= 3;
		        break;
		 case 3:direction= 0;
		        break;
		 }
	 }

	 //left
	 switch(direction){
	 case 0:x_addendum=0;
	        y_addendum=-1;
	        break;
	 case 1:x_addendum=-1;
	        y_addendum=0;
	        break;
	 case 2:x_addendum=0;
	        y_addendum=1;
	        break;
	 case 3:x_addendum=1;
	        y_addendum=0;
	        break;
	 }
	// System.out.println(x+"      "+y+y_addendum);
    if(grid[x+x_addendum][y+y_addendum]!=0){
		 switch(direction){
		 case 0:direction= 3;
		        break;
		 case 1:direction= 0;
		        break;
		 case 2:direction= 1;
		        break;
		 case 3:direction= 2;
		        break;
		 }
	 }
}

private int checkLine(int [][]grid, int x, int y, int direction){
	// System.out.println("in cehckline "+grid[x][y]);
	 if(direction==0 || direction==2){
		 for(int i=1; i<grid[0].length-1; i++){
			 if(i==x)
				 continue;
			 if(grid[i][y]/10!=grid[x][y]/10){
		//		 System.out.println("returned 1");
				 return 1;
			 } 
		 }
	 }
	 else if(direction==1 || direction==3){
		 for(int i=1; i<grid[0].length-1; i++){
			 if(i==y)
				 continue;
			 if(grid[x][i]/10!=grid[x][y]/10){
			//	 System.out.println(grid[x][i]+"returned 1"+grid[x][y]);
				 return 1;
			 }		 
		 }
	 }
	 return 0;	 
}
public int setMove(int [][]grid, int x, int y,int maxColored, int []primary){
	int x_addendum = 0;
	int y_addendum = 0;
	int loop=0;
	int move=0, reverse=direction;

	if(grid[x][y]==maxColored){
		//goBack
		while(loop==0){
		switch(direction){
		case 0:direction=2;
		       break;
		case 1:direction=3;
		       break;
		case 2:direction=0;
		       break;
		case 3:direction=1;
		       break;
		}
		if(change_x(x, y,grid)!=0 && change_y(x,y,grid)!=0 ){
			x=change_x(x, y,grid);
			y=change_y(x,y,grid);
			if(grid[x][y]/10!=maxColored)
				loop=1;
		}
		else{
			loop=1;
		}
	}
	}
	direction=reverse;
	//right side
	//System.out.println(grid[x][y]+"gris value is here ");
	switch(direction){
	case 0:x_addendum=0;
	       y_addendum=1;
	       break;
	case 1:x_addendum=1;
	       y_addendum=0;
	       break;
	case 2:x_addendum=0;
	       y_addendum=-1;
	       break;
	case 3:x_addendum=-1;
	       y_addendum=0;
	       break;
	}
	if( grid[x+x_addendum][y+y_addendum]!=0 /*&&  grid[x+x_addendum][y+y_addendum]/10!=maxColored */&& primary[done-1]/100!=grid[x][y]/10){
		if(direction==1 || direction==3){
			if((x+x_addendum)!=0 && (x+x_addendum)!=grid[0].length-1){
				System.out.println("returned here top"+(x+x_addendum));
				move= (grid[x][y]/10)*100+1;
			}
		}
		if(direction==0 || direction==2){
			if((y+y_addendum)!=0 && (y+y_addendum)!=grid[0].length-1){
			//	System.out.println("returned here");
				return (grid[x][y]/10)*100+1;
		}}
		    
	}

	//left side
	switch(direction){
	case 0:x_addendum=0;
	       y_addendum=-1;
	       break;
	case 1:x_addendum=-1;
	       y_addendum=0;
	       break;
	case 2:x_addendum=0;
	       y_addendum=1;
	       break;
	case 3:x_addendum=1;
	       y_addendum=0;
	       break;
	}
	if( grid[x+x_addendum][y+y_addendum]!=0 /*&&  grid[x+x_addendum][y+y_addendum]/10!=maxColored */&& primary[done-1]/100!=grid[x][y]/10){
		if(direction==1 || direction==3){
			if((x+x_addendum)!=0 && (x+x_addendum)!=grid[0].length-1){
				if(move==0)
			     	move= (grid[x][y]/10)*100+2;

					}		   
		}
		if(direction==0 || direction==2){
			if((y+y_addendum)!=0 && (y+y_addendum)!=grid[0].length-1)
				move= (grid[x][y]/10)*100+2;
		}
		return move;    
	}
	return (grid[x][y]/10)*100+3;
	
	}
public static int[] primary_func(int position_x, int position_y, int[][] grid, int num_terrorists, int []function, int maxColoredTile){
		int loop=0,killed=0;
		int x_added[]=new int[num_terrorists];
		int y_added[]=new int[num_terrorists];
		//System.out.println("in primary function");
		int tileColor=grid[position_x][position_y];
		int x=position_x , y=position_y;
		int forReturn[]=new int[6];
		int l=0;
		
		while(loop==0){
			l++;
			if(l>=grid[0].length*grid[0].length){
				forReturn[0]=-1;
				forReturn[1]=killed;
				
				break;
			}
			for(int i=0; i <function.length;i++){
				if(function[i]==-1)
					continue;
				 switch(function[i]/100){
				
				 case 3:if(grid[x][y]%10==1){
					        if(checkToAdd(x_added, y_added, x,y,killed)==1){
					        	   killed++;
					        	   x_added[killed-1]=x;
							       y_added[killed-1]=y;
					        }  
			            }
				       if(change_x(x,y,grid)==0 || change_y(x,y,grid)==0){
			        	  if(function[i]%100==0){
		                    forReturn[0]=-1;
		                    forReturn[1]=killed;
		                    forReturn[2]=x;
		                    forReturn[3]=y;
		                    return forReturn;					            
	                       }
			        	}
				        if(grid[x][y]/10==3){  
				        	if(function[i]%100==0){
					           x=change_x(x,y,grid);
					           y=change_y(x,y,grid);
				//	           System.out.println(function[i]/100+"\t"+x+"\t"+y+"\t"+function[i]+"\t"+direction);
				               }
		                else if(function[i]%100==1 || function[i]%100==2){
		                	changeDirection(function[i]%100, direction);
		          //      	System.out.println(function[i]/100+"\t"+x+"\t"+y+"\t"+function[i]+"\t"+"direction");
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
				        	if(function[i]%100==0){
			                   forReturn[0]=-1;
			                   forReturn[1]=killed;
			                   forReturn[2]=x;
			                   forReturn[3]=y;
			                   return forReturn;					            
		                     }
				        }
					    if(grid[x][y]/10==2){
					    
					    	if(function[i]%100==0){
					           x=change_x(x,y,grid);
					           y=change_y(x,y,grid);
					           //System.out.print("\t\t"+"on green tile"+"\n");
					//           System.out.println(function[i]/100+"\t"+x+"\t"+y+"\t"+function[i]+"\t"+direction);
				           }
					    else if(function[i]%100==1 || function[i]%100==2){
					    	changeDirection(function[i]%100, direction);
					  //  	System.out.println(function[i]/100+"\t"+x+"\t"+y+"\t"+function[i]+"\t"+direction);
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
				        	if(function[i]%100==0){
			                   forReturn[0]=-1;
			                   forReturn[1]=killed;
			                   forReturn[2]=x;
			                   forReturn[3]=y;
			                   return forReturn;					            
		                      }
				        }
					    if(grid[x][y]/10==1 ){
					    
					    	if(function[i]%100==0){
					           x=change_x(x,y, grid);
					           y=change_y(x,y, grid);
					           //System.out.print("\t\t"+"on blue tile"+"\n");
					    //       System.out.println(function[i]/100+"\t"+x+"\t"+y+"\t"+function[i]+"\t"+direction);
				            }
					    	if(function[i]%100==1 || function[i]%100==2){
					    		changeDirection(function[i]%100, direction);
					    	//	System.out.println(function[i]/100+"\t"+x+"\t"+y+"\t"+function[i]+"\t"+direction);
					    		}
					    	}
					        break;
				 case 4:if(change_x(x,y,grid)==0 || change_y(x,y,grid)==0){
			               if(function[i]%100==0){
			                   forReturn[0]=-1;
			                   forReturn[1]=killed;
			                   forReturn[2]=x;
			                   forReturn[3]=y;
			                   return forReturn;					            
		                 }
			               }
		               if(function[i]%100==0){
			                x=change_x(x,y,grid);
			                y=change_y(x,y,grid);
		                }
		               else if(function[i]%100==1 ||  function[i]%100==2){
		         	            changeDirection(function[i]%100, direction);
		                }
		  //             System.out.println(function[i]/100+"\t"+x+"\t"+y+"\t"+function[i]+"\t"+direction);
			           break;
				   } 
			  }
		//	System.out.println("killed"+killed);
			if(killed==num_terrorists){
				System.out.println("going to print");
				//System.out.println("going to write in the file ");
				writeToFile(function);
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
} 

//0-forward
//1-right
//2-left
//3-u-turn
//when on same tile its counting the num of terrorists as more .! name = new (arguments);
//now goin to compound functions
