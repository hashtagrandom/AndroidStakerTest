package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

public class RuneLongSword extends Item{
	private int value;
	    private String name;
	    private Rarity rarity;

	    public RuneLongSword(){
	        this.value = 30000;
	        this.name = "Rune longsword";
	    }

	    public RuneLongSword(Rarity rarity){
	        this.value = 250000;
	        this.name = "Rune longsword";
	        this.rarity = rarity;
	    }

	    public int getValue() {
	        return value;
	    }

	    public String getName() {
	        return name;
	    }

	    public int getImageResource(){
	        return R.drawable.rune_longsword;
	    }

	    public Rarity getRarity() {
	        return rarity;
	    }
}
