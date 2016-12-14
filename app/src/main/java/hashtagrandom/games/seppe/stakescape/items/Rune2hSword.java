package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

public class Rune2hSword extends Item{
	private int value;
	    private String name;
	    private Rarity rarity;

	    public Rune2hSword(){
	        this.value = 50000;
	        this.name = "Rune 2h sword";
	    }

	    public Rune2hSword(Rarity rarity){
	        this.value = 50000;
	        this.name = "Rune 2h sword";
	        this.rarity = rarity;
	    }

	    public int getValue() {
	        return value;
	    }

	    public String getName() {
	    	return name;	
	    }

	    public int getImageResource(){
	        return R.drawable.rune_2h_sword;
	    }

	    public Rarity getRarity() {
	        return rarity;
	    }
}
