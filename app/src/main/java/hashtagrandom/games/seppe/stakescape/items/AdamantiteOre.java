package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

public class AdamantiteOre extends Item implements Stackable{
	private int value;
	private String name;
	private int amount;
	private Rarity rarity;

	public AdamantiteOre(){
		this.value = 2500;
		this.name = "Adamantite ore";
		this.amount = 1;
	}

	public AdamantiteOre(Rarity rarity){
		this.value = 2500;
		this.name = "Adamantite ore";
		this.rarity = rarity;
		this.amount = 1;
	}

	public AdamantiteOre(Rarity rarity, int amount){
		this.value = 2500;
		this.name = "Adamantite ore";
		this.rarity = rarity;
		this.amount = amount;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getImageResource(){
		return R.drawable.adamantite_ore;
	}

	@Override
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
