package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

public class SnapdragonSeed extends Item{
	private int value;
    private String name;
    private int amount;
    private Rarity rarity;
    
    public SnapdragonSeed(){
        this.value = 45000;
        this.name = "Snapdragon seed";
    }

    public SnapdragonSeed(Rarity rarity){
        this.value = 45000;
        this.name = "Snapdragon seed";
        this.rarity = rarity;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int getImageResource(){
        return R.drawable.snapdragon_seed;
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
