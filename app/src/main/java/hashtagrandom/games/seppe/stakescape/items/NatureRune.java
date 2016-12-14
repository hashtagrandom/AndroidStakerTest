package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

public class NatureRune extends Item implements Stackable{
	private int value;
	private String name;
	private int amount;
	private Rarity rarity;

	public NatureRune(){
		this.value = 5000;
		this.name = "Nature rune";
	}

	public NatureRune(Rarity rarity){
	this.value = 1000;
	this.name = "Nature rune";
	this.rarity = rarity;
	this.amount = 1;
	}

	public NatureRune(Rarity rarity, int amount){
		this.value = 1000;
		this.name = "Nature rune";
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
		return R.drawable.nature_rune;
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
