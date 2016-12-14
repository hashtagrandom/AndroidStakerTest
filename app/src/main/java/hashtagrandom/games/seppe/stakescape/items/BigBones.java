package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

public class BigBones extends Item implements Stackable{
	private int value;
    private String name;
    private Rarity rarity;
    private int amount;

    public BigBones(){
        this.value = 1000;
        this.name = "Big Bones";
        this.amount = 1;
    }

    public BigBones(Rarity rarity){
        this.value = 1000;
        this.name = "Big Bones";
        this.rarity = rarity;
        this.amount = 1;
    }

    public BigBones(Rarity rarity, int amount){
        this.value = 1000;
        this.name = "Big Bones";
        this.rarity = rarity;
        this.amount = amount;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int getImageResource(){
        return R.drawable.big_bones;
    }

    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void decreaseAmount(int amount) {
        this.amount = this.amount - amount;
    }

    @Override
    public void addAmount(int amount) {
        this.amount = this.amount + amount;
    }

    @Override
    public boolean isStackable(){ return true; }
}
