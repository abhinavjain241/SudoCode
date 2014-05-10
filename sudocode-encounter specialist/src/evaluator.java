public class evaluator extends main_encounter{
     public void start(int position_x, int position_y, int [][]grid, int numTerrorists, int []numFunctions, int []numColorTiles, int gridSize, int direction){
    	int F1[]=new int[numFunctions[0]];
    	int check=compare(numColorTiles, gridSize);
    	function func=new function();
    	
    	int getMaxColor=0;
    	int [][]path;
    	if(check!=0 && numFunctions.length==1){
    		func.simpleFunction(position_x, position_y, grid, numTerrorists, numFunctions[0], check, gridSize, direction);
    	}
    	else if(check==0 && numFunctions.length==1){
    		getMaxColor=mColor(grid);
    		path=makepaths(position_x,  position_y, grid,numTerrorists, numFunctions, numColorTiles, gridSize, direction, getMaxColor); 
    			createSingleCode(grid,path, numFunctions, position_x, position_y, getMaxColor, numTerrorists);
    	}
    	else if(numFunctions.length>1){
    		if(check!=0){
    			try{
    			func.simpleFunction(position_x, position_y, grid, numTerrorists, numFunctions[0], check, gridSize, direction);
    		    }
    			catch(Exception e){
    				System.out.println(" ");
    			}}
    			getMaxColor=mColor(grid);

    			path=makepaths(position_x,  position_y, grid,numTerrorists, numFunctions, numColorTiles, gridSize, direction, getMaxColor); 
    			try{
    				 System.out.println("here its is ");
    			createSingleCode(grid,path, numFunctions, position_x, position_y, getMaxColor, numTerrorists);
    		    }catch(Exception e){
    			System.out.println(" ");
    	     	}
    			path=makepaths(position_x,  position_y, grid,numTerrorists, numFunctions, numColorTiles, gridSize, direction, getMaxColor); 
    			difficult dif=new difficult();
    			dif.justDoIt(position_x, position_y, grid,  getMaxColor, numTerrorists, path, numFunctions);
    		}
     }
     private int mColor(int grid[][]){
    	 int color[]=new int[3];
    	 color[0]=0;
    	 color[1]=0;
    	 color[2]=0;
    	 for(int i=1 ; i <grid[0].length-1; i++){
    		 for(int j=1; j<grid[0].length-1;j++){
    			 if(grid[i][j]==0)
    				 continue;
    			 color[grid[i][j]/10-1]++;
    		 }
    	 }
    	 if(color[0]>color[1]+color[2])
    		 return 1;
    	 else if(color[1]>color[2]+color[0])
    		 return 2;
    	 else if(color[2]>color[1]+color[0])
    		 return 3;
    	 return 0;
     }
     private  int [][] makepaths(int position_x, int position_y, int[][] grid,
			int numTerrorists, int[] numFunctions, int[] numColorTiles,
			int gridSize, int direction, int maxColor) {
    	    int toAdd=0,start_x=position_x, start_y=position_y , killed=0;
    	    int [][]path=new int[10][2*gridSize*gridSize+2];
    	    function funcObj=new function();
    	    for(int l=0;l<10;l++){
    	    	for(int j=0;j<2*gridSize*gridSize+2;j++){
    	    		path[l][j]=0;
    	    	}
    	    }
    	    int [][]gridCheck=new int[gridSize][gridSize];
    	    for(int i=0; i <gridSize;i++){
    	    	for(int j=0; j<gridSize;j++){
    	    		gridCheck[i][j]=2;
    	    	}
    	    }
    	   if(checkLine(grid, position_x, position_y, direction)==0){
    		   int  reverse=direction;
    	    	direction=setDirection(grid, position_x, position_y, direction);
    	    	if(reverse-direction>0){
    	    		path[0][toAdd++]=(grid[position_x][position_y]/10)*100000+ position_x*1000+position_y*10+2;
    	    	}
    	    	else if(reverse-direction<0){
    	    		path[0][toAdd++]=(grid[position_x][position_y]/10)*100000+ position_x*1000+position_y*10+1;
    	    	}
    	    }
    	 
    	    while(toAdd>=0){
    	    	if(change_x(position_x,position_y,grid, direction)!=0 && change_y(position_x, position_y, grid,direction)!=0){
    	    		 path[0][toAdd++]=(grid[position_x][position_y]/10)*100000+position_x*1000+position_y*10+0;
    	    		 position_x=change_x(position_x,position_y,grid, direction);
    	    		 position_y=change_y(position_x, position_y, grid,direction);
    	    		 if(grid[position_x][position_y]%10==1){
    	    			 killed++;
    	    		   }
    	    		 if(killed==numTerrorists){
    	    			 break;
    	    		   }
    	    	   }
    	    	else{
    	    		int reverse=direction, direct = 0;
    	    		direction=setDirection(grid,position_x, position_y, direction);
    	    		if(direction==3){
    	    			path[0][toAdd++]=(grid[position_x][position_y]/10)*100000+position_x*1000+position_y*10+1;
    	    			path[0][toAdd++]=(grid[position_x][position_y]/10)*100000+position_x*1000+position_y*10+1;
    	    		   }
    	    		else{
    	    		switch(reverse){
    	    		case 0:if(direction==1){
    	    			        direct=1;
    	    			        break;
    	    		       }
    	    		       if(direction==3){
    	    		    	   direct=2;
    	    		    	   break;
    	    		       }
    	    		case 1:if(direction==2){
    	    			        direct=1;
    	    			        break;
    	    		       }
    	    		       if(direction==0){
    	    		    	   direct=2;
    	    		    	   break;
    	    		       }
    	    		case 2:if(direction==3){
    	    			         direct=1;
    	    			         break;
    	    		       }
    	    		       if(direction==1){
    	    		    	   direct=2;
    	    		    	   break;
    	    		       }
    	    		case 3:if(direction==0){
    	    			        direct=1;
    	    			        break;
    	    		        }
    	    		       if(direction==2){
    	    		    	   direct=2;
    	    		    	   break;
    	    		       }
    	    		 }
    	    		path[0][toAdd++]=(grid[position_x][position_y]/10)*100000+position_x*1000+position_y*10+direct;
    	           }
    	    	}
    	    }
		return path;
	}
     static int change_x(int x,int y,int [][]grid, int direction){
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
   				return 0;
   		 }
   	  }
     static int change_y(int x,int y,int[][] grid, int direction){
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
     private int setDirection(int [][]grid, int x, int y,int direction){
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
    		 case 0:return 1;
    		 case 1:return 2;
    		 case 2:return 3;
    		 case 3:return 0;
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
    		 case 0:return 3;
    		 case 1:return 0;
    		 case 2:return 1;
    		 case 3:return 2;
    		 }
    	 }
    	 
    	 return 3;
     }
     private int checkLine(int [][]grid, int x, int y, int direction){
    	// System.out.println("in cehckline "+grid[x][y]);
    	 if(direction==0 || direction==2){
    		 for(int i=1; i<grid[0].length-1; i++){
    			 if(i==x)
    				 continue;
    			 if(grid[i][y]/10!=grid[x][y]/10){
    				// System.out.println("returned 1");
    				 return 1;
    			 } 
    		 }
    	 }
    	 else if(direction==1 || direction==3){
    		 for(int i=1; i<grid[0].length-1; i++){
    			 if(i==y)
    				 continue;
    			 if(grid[x][i]/10!=grid[x][y]/10){
    				 //System.out.println(grid[x][i]+"returned 1"+grid[x][y]);
    				 return 1;
    			 }		 
    		 }
    	 }
    	 return 0;
     }
     private int compare(int []colorTile, int gridSize){
    	int max=0;
    	 if(colorTile[0]>(colorTile[1]+colorTile[2]))
    		max= 1;
    	 if(colorTile[1]>(colorTile[0]+colorTile[2]))
    		max =2;
    	 if(colorTile[2]>(colorTile[1]+colorTile[0]))
    		max= 3;
    	if(colorTile[max]>colorTile[3])
    		return max;
		return 0; 
     }
		// TODO Auto-generated method stub   
	private void createSingleCode(int [][]grid, int [][]path, int []functions, int position_x, int position_y, int color, int numTerrorists){
	    int []solution= new int[functions[0]];
	    for(int h=0;h<functions[0];h++)
	    	solution[h]=0;
	    int x=position_x, y=position_y;
	    int x_turn=path[0][0]/1000-(path[0][0]/100000*100), y_turn=path[0][0]/10-(path[0][0]/1000*100);
	    int operationNum=0;
        if(path[0][0]%10==0){
        	solution[operationNum++]=400;
        	solution[operationNum]=-1;
        }
        else {
        	solution[operationNum++]=(path[0][0]/100000)*100+path[0][0]%10;
        	solution[operationNum]=-1;
        }
        int add, check=0;
        for(int l=1; l<=grid[0].length*grid[0].length*2;l++){
        	check=0;
        	if(path[0][l]==0){
        		break;
        	}
        	if(path[0][l]%10!=0){
        		System.out.println(path[0][l]);
        		add=path[0][l]/100000*100+path[0][l]%10;
        		for(int z=operationNum-2; z>=0;z--){
        		     if(solution[z]==add)
        		    	 check=1;
        		}
        		if(path[0][l-1]/100000==path[0][l]/100000 && solution[operationNum-1]!=add){
        			//System.out.println("here we are");
        			solution[operationNum++]=path[0][l-1]/100000*100+0;
        			solution[operationNum++]=add;
        			solution[operationNum]=-1;
        			check=1;
        			}
        		if(check==0){
        	    if(solution[operationNum-1]==add && path[0][l-1]/100000*100+path[0][l-1]%10==add){
        		    System.out.println(add+"  "+solution[operationNum-1]);
        		    solution[operationNum++]=(path[0][l]/100000)*100+path[0][l]%10;
        		    solution[operationNum]=-1;
        		    check=1;
        	    }
                if(solution[operationNum-1]==add && path[0][l-1]/100000*100+path[0][l-1]%10!=add)
                	check=1;
        		}
        	    if(check==0){
        	    	  System.out.println(add+"  "+solution[operationNum-1]);
          		    solution[operationNum++]=(path[0][l]/100000)*100+path[0][l]%10;
          		    solution[operationNum]=-1;
          		    check=1;
        	    
        	}}
            if(operationNum+1==solution.length)
            	break;
        }
        System.out.println(solution[0]+"   "+solution[1]+"  "+solution[2]+"   "+solution[3]);
        function obj=new function();
        writeToFile(solution);
        int []out=obj.primary_func(path[0][0]/1000-(path[0][0]/100000*100), path[0][0]/10-(path[0][0]/1000*100), grid, numTerrorists, solution, color);
        }
	   
}
//-1 for recursively calling itself