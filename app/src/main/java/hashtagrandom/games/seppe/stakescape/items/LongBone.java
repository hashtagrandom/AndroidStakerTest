package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

public class LongBone extends Item{
	private int value;
    private String name;
    private int amount;
    private Rarity rarity;
    
    public LongBone(){
        this.value = 100000;
        this.name = "Long bone";
    }

    public LongBone(Rarity rarity){
        this.value = 100000;
        this.name = "Long bone";
        this.rarity = rarity;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int getImageResource(){
        return R.drawable.long_bone;
    }

    public Rarity getRarity() {
        return rarity;
    }
    
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
