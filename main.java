import java.util.Scanner;

public class main{
  static int size = Map.size;
  static int[][] map = Map.build();
  static int xSize = Map.x;
  static Player player = new Player();
  
  public static void display(){
    for(int i=0;i<size;i++){
      if(i%xSize==(xSize-1)){
        System.out.println(map[xSize][i%xSize]+"\n");
      }
      else
        System.out.print(map[i/xSize][i%xSize] + " ");
    }
  }
  
  public static void move(){
    boolean error = false;
    while(true){
      Scanner scanner = new Scanner(System.in);
      System.out.print("Move:");
      String direction = scanner.nextLine();
      direction = direction.toUpperCase();
      if(direction.equals("C") || direction.equals("CANCEL")||direction.equals("STOP")||direction.equals("BREAK")){
        break;
      }
      if(direction.equals("N") || direction.equals("NORTH")||direction.equals("U")||direction.equals("UP")){
        if(terrain(0,-1) ==0){
          System.out.println("You can't go that way!");
          error = true;
        }
        else{
          player.up();
          if(NPCType(0,0) != 0){
            	combat();
            	break;
            }
          break;
        }
      }
      if(direction.equals("E") || direction.equals("EAST")||direction.equals("R")||direction.equals("RIGHT")){
        if(terrain(1,0) ==0){
          System.out.println("You can't go that way!");
          error = true;
        }
        else{
          player.right();
          if(NPCType(0,0) != 0){
            	combat();
            	break;
            }
          break;
        }
      }
      if(direction.equals("S") || direction.equals("SOUTH")||direction.equals("D")||direction.equals("DOWN")){
        if(terrain(0,1) ==0){
          System.out.println("You can't go that way!");
          error = true;
        }
        
        else{
          player.down();
          if(NPCType(0,0) != 0){
          	combat();
          	break;
          }
          break;
        }
      }
      if(direction.equals("W") || direction.equals("WEST")||direction.equals("L")||direction.equals("LEFT")){
        if(terrain(-1,0) ==0){
          System.out.println("You can't go that way!");
          error = true;
        }
        else{
          player.left();
          if(NPCType(0,0) != 0){
            	combat();
            	break;
            }
          break;
        }
      }
      if(!error){
        System.out.println("Not a direction");
      }
      error = false;
    }
  }
  
  
  public static int terrain(int xChange,int yChange){
    try{
      return (map[player.getYPos()+yChange][player.getXPos()+xChange]%10);
    }
    catch (ArrayIndexOutOfBoundsException e){
      return 0;
    }
  }

  public static String terrainType(int xChange,int yChange){
	    switch(terrain(xChange,yChange)){
	      case 1: return "a hallway";
	      case 2: return "a room";
	      default: return "a wall";
	    }
	  }
  
  public static String NPCName(int NPCType){
	  switch(NPCType){
	  case 0: return "nothing";
	  case 1: return "a rat";
	  case 2: return "a warrior";
	  default: return "a unknown foe!";
	  }
	  }
	  
  public static int NPCType(int xChange, int yChange){
	  try{
	      return ((map[player.getYPos()+yChange][player.getXPos()+xChange]/10)%10); // this has changed to enable regions
	    }
	    catch (ArrayIndexOutOfBoundsException e){
	      return 0;
	    }
  }
 
  public static int regionType(int xChange, int yChange){
	  try{
		  return (map[player.getYPos()+yChange][player.getXPos()+xChange]/100);
	  }
	  catch (ArrayIndexOutOfBoundsException e){
		  return 0;
	  }
  }
  
  public static String regionName(int regionType){
	  switch(regionType){
	  case 0: System.err.println("What to print here?"); return "";
	  case 1: return "Valnesse";
	  case 2: return "Landwick";
	  case 3: return "Stonehaven";
	  case 4: return "Pinehedge";
	  case 5: return "Valbridge";
	  case 6: return "Magefield";
	  case 7: return "Clearlake";
	  case 8: return "Highdale";
	  default: return "you are lost!";
	  }
  }
  public static void look(){
	  System.out.print("To the left there is " + terrainType(-1,0));
	  if(NPCType(-1,0) != 0) System.out.println(" and " + NPCName(NPCType(-1,0)));
	  else System.out.println();
	  System.out.print("To the right there is " + terrainType(1,0));
	  if(NPCType(1,0) != 0) System.out.println(" and " + NPCName(NPCType(1,0)));
	  else System.out.println();
	  System.out.print("Up there is " + terrainType(0,-1));
	  if(NPCType(0,-1) != 0) System.out.println(" and " + NPCName(NPCType(0,-1)));
	  else System.out.println();
	  System.out.print("Down there is " + terrainType(0,1));
	  if(NPCType(0,1) != 0) System.out.println(" and " + NPCName(NPCType(0,1)));
	  else System.out.println();
	  System.out.println();
  }
  
  public static void examine(){
	  int target = 0;
	  System.out.println("Which way do you want to examine?");
	  Scanner scanner = new Scanner(System.in);
	  String direction = scanner.nextLine();
	  direction = direction.toUpperCase();
	  if(direction.equals("C") || direction.equals("CANCEL")||direction.equals("STOP")||direction.equals("BREAK")){
	        return;
	      }
	  else
		  if(direction.equals("N") || direction.equals("NORTH")||direction.equals("U")||direction.equals("UP")){
			  target = NPCType(0,-1);
		  }
		  else
			  if(direction.equals("E") || direction.equals("EAST")||direction.equals("R")||direction.equals("RIGHT")){
				  target = NPCType(1,0);
			  }
			  else
				  if(direction.equals("S") || direction.equals("SOUTH")||direction.equals("D")||direction.equals("DOWN")){
					  target = NPCType(0,1);
				  }
				  else
					  if(direction.equals("W") || direction.equals("WEST")||direction.equals("L")||direction.equals("LEFT")){
						  target = NPCType(-1,0);
					  }
					  else{
						  System.out.println("Not a direction!");
						  return;
					  }
      
      if(target ==0){
    	  System.out.println("There's nothing there...");
    	  return;
      }
      else{
    	  System.out.println("There's " + NPCName(target) + " there. Check stats?");
      }
      String confirm = scanner.nextLine();
      confirm = confirm.toUpperCase();
      if(confirm.equals("Y")||confirm.equals("YES")){
    	  checkStats(target);
      }
      else{
    	  return;
      }
      }

 
public static void checkStats(int target){
	int HP = 0;
	int level = 0;
	int attack = 0;
	switch(target){
	case 1: HP = Rat.getHP();
			attack = Rat.getAttack();
			level = Rat.getLevel();
			break;
			
	case 2: HP = Warrior.getHP();
			attack = Warrior.getAttack();
			level = Warrior.getLevel();
			break;
	}
	System.out.println("HP: " + HP);
	System.out.println("level:" + level);
	System.out.println("attack:" + attack);
}
      

  
  public static void commands(){
    System.out.println("You are currently in " + terrainType(0,0));
    System.out.print("What do you want to do? ");
    
    Scanner scanner = new Scanner(System.in);
    String order = scanner.nextLine();
    order = order.toUpperCase();
    System.out.println();
    if(order.equals("MOVE")||order.equals("ATTACK")||order.equals("FIGHT")){
      move();
    }
    else{
    	if(order.equals("LOOK")){
    		look();
    	}
    	else{
    		if(order.equals("MAP")){
    			display();
    		}
    		else{
    			if(order.equals("STATS")||order.equals("STAT")){
    				player.stats();
    			}
    		
    		else{
    			if(order.equals("BREAK")||order.equals("EXIT")){
    				System.out.println("exiting");
    				System.exit(0);
    			}
    				else{
    			    	if(order.equals("EXAMINE")){
    			    		examine();
    			    	}
    			    	else{
    					System.out.println("Unrecognised command!");
    			    	}
    			}
    		}
    		}
    	}
    }
}
  
  // combat
  
  public static int xPos,yPos;
  
  public static void combat(){
	  System.out.println("There's " + NPCName(NPCType(0,0)) + "\nIt goes to attack!");
	  xPos = player.getXPos();
	  yPos = player.getYPos();;
	  int type = map[yPos][xPos]/10;
	  if (type == 0){
		  System.err.println("No enemy found.  Why was this called?");
	  }
	  else{
		  fight(type);
	  }  
  }
  
  public static int damagePlayer = player.getAttack();
  public static int PlayerHP = player.getHP();
  public static int damageNPC = 0;
  public static int NPCHP = 0;
  public static String name = "";

public static void fightStats(int type){
	name = NPCName(type);
	switch (type)
	{
		case 1:
		damageNPC = Rat.getAttack();
		NPCHP = Rat.getHP();
		break;
	  case 2:
		damageNPC = Warrior.getAttack();
		NPCHP = Warrior.getHP();
	  }
	  
  }
  

  private static void fight(int type){
	  fightStats(type);
	  int XPboost = NPCHP;
	  while(PlayerHP>0 && NPCHP > 0){
		  int PlayerDamage = (int)(Math.random()*damageNPC);
		  PlayerHP -= PlayerDamage;
		  player.hurt(PlayerDamage);
		  if(PlayerHP <= 0){break;}
		  NPCHP -= Math.random()*damagePlayer;
		  }
	  if(NPCHP <= 0){
		  System.out.println("You killed " + name);
		  Map.corpse(xPos,yPos);
		  player.incXP(XPboost);
		  }
	  
	  if(PlayerHP <= 0){
		  System.out.println("You died");
		  System.exit(0);
	  }
	  
	  return;
	  }
	  
  
  
  
  
  public static void main(String[] args){
	    while(true){
	    	commands();
	    }
	  }
}