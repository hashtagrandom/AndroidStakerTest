package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

public class GodSwordShard3 extends Item{
	private int value;
    private String name;
    private Rarity rarity;

    public GodSwordShard3(){
        this.value = 150000;
        this.name = "Godsword Shard 3";
    }

    public GodSwordShard3(Rarity rarity){
        this.value = 150000;
        this.name = "Godsword Shard 3";
        this.rarity = rarity;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int getImageResource(){
        return R.drawable.godsword_shard_3;
    }

    public Rarity getRarity() {
        return rarity;
    }
}
