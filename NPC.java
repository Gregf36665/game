
public class NPC {
	//format: hp,type,attack
	public static Enemy rat(int xPos, int yPos){
		Enemy rat = new Enemy(5,1,1);
		rat.setXPos(xPos);
		rat.setYPos(yPos);
		return rat;
	}

	public static Enemy warrior(int xPos, int yPos){
		Enemy warrior = new Enemy(10,2,3);
		warrior.setXPos(xPos);
		warrior.setYPos(yPos);
		return warrior;
	}
	}

