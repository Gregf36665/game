

// Note for the coordiantes (0,0) is the first array (x,y) note the arrays count y as down and x as 
//across but the index is [y][x] 

public class Map{
  public static int x = 35;
  public static int y = 45;
  public static int size = x*y;
  public static int[][] map = new int[y][x];
  
  public static int[][] build(){
    
    
    // fill with zeros
    for(int i=0; i<size;i++){
      map[i/x][i%x] = 0;
    }
    
    // build rooms in the form (xpos,ypos,xsize,ysize)
    woods(0,0,1,1);
    woods(2,1,2,2);
    woods(7,0,3,2);
    woods(4,4,2,2);
    woods(0,7,3,3);
    woods(7,8,3,2);
    woods(7,3,3,2);
    
    // build the hall vertical or horizontal(x1,y1,length)
    vHall(0,1,4);
    vHall(1,4,3);
    vHall(5,2,2);
    vHall(8,2,1);
    vHall(8,5,3);
    vHall(3,6,2);
    vHall(5,8,2);

    
    hHall(3,0,4);
    hHall(1,1,1);
    hHall(4,2,1);
    hHall(2,4,2);
    hHall(4,6,1);
    hHall(3,9,2);
    hHall(6,8,1);
    hHall(6,4,1);
  
    
    // add in enemies
    Rat rat1 = new Rat(5,4);
    Rat rat2 = new Rat(0,0);
    Rat rat3 = new Rat(5,3);
    Rat rat4 = new Rat(5,2);
    Warrior warrior1 = new Warrior(2,4);
    addEnemy(rat1.build());
    addEnemy(rat2.build());
    addEnemy(rat3.build());
    addEnemy(rat4.build());
    addEnemy(warrior1.build());

    // set up the regions
    region(0,0,5,5,1);
    // return the map
    
    return map;
  }
  
  public static void region(int xPosInitial, int yPosInitial, int sizeX,int sizeY, int region){
	  try{
		  map[xPosInitial+sizeX-1] = map[xPosInitial+sizeX-1]; // check within x dimentions
		  try{
			  map[yPosInitial+sizeY-1] = map[yPosInitial+sizeY-1]; // check within y dimentions
			  // build the room
			  for(int xPos=xPosInitial; xPos<xPosInitial+sizeX;xPos++){
				  for(int yPos=yPosInitial; yPos<yPosInitial+sizeY;yPos++){
					  map[yPos][xPos] = map[yPos][xPos] + (region*100);
				  }
			  }
		  }
		  catch (ArrayIndexOutOfBoundsException e){
			  System.err.println("invalid y dimentions at (" + xPosInitial +"," + yPosInitial +")");
		  }
	  }
	  catch (ArrayIndexOutOfBoundsException e){
		  System.err.println("invalid x dimentions at (" + xPosInitial +"," + yPosInitial +")");
	  }
  }
  
  public static void woods(int xPosInitial, int yPosInitial, int sizeX,int sizeY){
    try{
      map[xPosInitial+sizeX-1] = map[xPosInitial+sizeX-1]; // check within x dimentions
      
      try{
        map[yPosInitial+sizeY-1] = map[yPosInitial+sizeY-1]; // check within y dimentions
        // build the room
        for(int xPos=xPosInitial; xPos<xPosInitial+sizeX;xPos++){
          for(int yPos=yPosInitial; yPos<yPosInitial+sizeY;yPos++){
            map[yPos][xPos] = 2;
          }
        }
      }
      catch (ArrayIndexOutOfBoundsException e){
        System.err.println("invalid y dimentions at (" + xPosInitial +"," + yPosInitial +")");
      }
    }
    catch (ArrayIndexOutOfBoundsException e){
      System.err.println("invalid x dimentions at (" + xPosInitial +"," + yPosInitial +")");
    }
  }

  public static void addEnemy(Enemy name){
	  map[name.getYPos()][name.getXPos()] = name.getType()*10 + map[name.getYPos()][name.getXPos()];
  }
  
  
  public static void hHall(int xPosInitial, int yPosInitial, int length){
    try{
      map[xPosInitial+length-1] = map[xPosInitial+length-1]; // test if hall will go out of range 
      for(int xPos=xPosInitial; xPos<xPosInitial+length;xPos++){
        map[yPosInitial][xPos] = 1;
    }
    }

    catch (ArrayIndexOutOfBoundsException e){
      System.err.println("length too long horizontally at (" + xPosInitial +"," + yPosInitial +")");
    }
  }
  
  public static void vHall(int xPosInitial, int yPosInitial, int length){
    try{
      map[yPosInitial+length-1] =  map[yPosInitial+length-1]; // test if hall will go out of range 
      for(int yPos=yPosInitial; yPos<yPosInitial+length;yPos++){
        map[yPos][xPosInitial] = 1;
    }
    }
    catch (ArrayIndexOutOfBoundsException e){
      System.err.println("length too long vertically at (" + xPosInitial +"," + yPosInitial +")");
  }
  }
  
  public static void corpse(int xPos,int yPos){
	  map[yPos][xPos] = map[yPos][xPos]%10; 
  }
  
}