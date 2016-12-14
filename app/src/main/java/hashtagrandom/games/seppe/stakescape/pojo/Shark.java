package hashtagrandom.games.seppe.stakescape.pojo;

public class Shark {
	public static final int MIN_AMOUNT = 0;
	
	private int healing = 20;
	
	public Shark(){
		this.setHealing(healing);
	}

	public int getHealing() {
		return healing;
	}

	public void setHealing(int healing) {
		this.healing = healing;
	}
}
