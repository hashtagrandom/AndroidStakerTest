package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

public class RuneSword extends Item{
	 	private int value;
	    private String name;
	    private Rarity rarity;

	    public RuneSword(){
	        this.value = 250000;
	        this.name = "Rune sword";
	    }

	    public RuneSword(Rarity rarity){
	        this.value = 250000;
	        this.name = "Rune sword";
	        this.rarity = rarity;
	    }

	    public int getValue() {
	        return value;
	    }

	    public String getName() {
	        return name;
	    }

	    public int getImageResource(){
	        return R.drawable.rune_sword;
	    }

	    public Rarity getRarity() {
	        return rarity;
	    }
}
