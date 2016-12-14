package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

/**
 * Created by u0098595 on 17/06/2016.
 */
public class MagicLogs extends Item implements Stackable{
    private int value;
    private String name;
    private int amount;
    private Rarity rarity;

    public MagicLogs(){
        this.value = 5000;
        this.name = "Magic Logs";
        this.amount = 1;
    }

    public MagicLogs(Rarity rarity){
        this.value = 5000;
        this.name = "Magic Logs";
        this.rarity = rarity;
        this.amount = 1;
    }

    public MagicLogs(Rarity rarity, int amount){
        this.value = 5000;
        this.name = "Magic Logs";
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
        return R.drawable.magic_logs;
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
