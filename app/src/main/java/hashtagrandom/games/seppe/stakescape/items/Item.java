package hashtagrandom.games.seppe.stakescape.items;

/**
 * Created by u0098595 on 17/06/2016.
 */
public abstract class Item {
    public abstract int getValue();

    public abstract String getName();

    public abstract int getImageResource();

    public abstract Rarity getRarity();

    public boolean isTradable(){
        return true;
    }

    public boolean isStackable(){ return false; }

    public int getAmount(){
        return 1;
    }

    public void addAmount(int amount){}

    public void decreaseAmount(int amount){}
}
