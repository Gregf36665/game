
public class Warrior {
	private static int attack = 5;
	private static int hp = 15;
	private static int level = (hp-5)/2;
	Enemy warrior = new Enemy(hp,2,attack);
	
	public Warrior(int xPos, int yPos){
		
		warrior.setXPos(xPos);
		warrior.setYPos(yPos);
		
		}
	
	  public Enemy build(){
		  return warrior;
	  }
	  
	  public static int getLevel(){
		  return level;
	  }
	  
	  public static int getHP(){
		    return hp;
		  }
		  
		  public int getType(){
		    return warrior.getType();
		  }
		  
		  public static int getAttack(){
		    return attack;
		  }
}
