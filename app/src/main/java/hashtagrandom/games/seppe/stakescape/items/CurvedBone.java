package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

public class CurvedBone extends Item{
	private int value;
    private String name;
    private int amount;
    private Rarity rarity;
    
    public CurvedBone(){
        this.value = 5000000;
        this.name = "Curved bone";
    }

    public CurvedBone(Rarity rarity){
        this.value = 5000000;
        this.name = "Curved bone";
        this.rarity = rarity;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int getImageResource(){
        return R.drawable.curved_bone;
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
