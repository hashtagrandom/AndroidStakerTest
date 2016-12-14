package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

public class Coal extends Item implements Stackable{
	private int value;
    private String name;
    private int amount;
    private Rarity rarity;
    
    public Coal(){
        this.value = 2500;
        this.name = "Coal";
        this.amount = 1;
    }

    public Coal(Rarity rarity){
    		this.value = 2500;
	        this.name = "Coal";
	        this.rarity = rarity;
	        this.amount = 1;
    }

    public Coal(Rarity rarity, int amount){
        this.value = 2500;
        this.name = "Coal";
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
        return R.drawable.coal;
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

    public boolean isStackable(){ return true; }
}
