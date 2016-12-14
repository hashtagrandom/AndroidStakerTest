package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

public class GodSwordShard2 extends Item{
	 	private int value;
	    private String name;
	    private Rarity rarity;

	    public GodSwordShard2(){
	        this.value = 150000;
	        this.name = "Godsword Shard 2";
	    }

	    public GodSwordShard2(Rarity rarity){
	        this.value = 150000;
	        this.name = "Godsword Shard 2";
	        this.rarity = rarity;
	    }

	    public int getValue() {
	        return value;
	    }

	    public String getName() {
	        return name;
	    }

	public int getImageResource(){
		return R.drawable.godsword_shard_2;
	}

	    public Rarity getRarity() {
	        return rarity;
	    }
}
