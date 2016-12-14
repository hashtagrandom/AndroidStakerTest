package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

public class RangerBoots extends Item{
	private int value;
	    private String name;
	    private Rarity rarity;

	    public RangerBoots(){
	        this.value = 25000000;
	        this.name = "Ranger boots";
	    }

	    public RangerBoots(Rarity rarity){
	        this.value = 25000000;
	        this.name = "Ranger boots";
	        this.rarity = rarity;
	    }

	    public int getValue() {
	        return value;
	    }

	    public String getName() {
	        return name;
	    }

	    public int getImageResource(){
	        return R.drawable.coal;
	    }

	    public Rarity getRarity() {
	        return rarity;
	    }
}
