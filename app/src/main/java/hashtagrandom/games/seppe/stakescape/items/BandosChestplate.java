package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

/**
 * Created by u0098595 on 17/06/2016.
 */
public class BandosChestplate extends Item {

    private int value;
    private String name;
    private Rarity rarity;

    public BandosChestplate(){
        this.value = 15000000;
        this.name = "Bandos Chestplate";
    }

    public BandosChestplate(Rarity rarity){
        this.value = 15000000;
        this.name = "Bandos Chestplate";
        this.rarity = rarity;
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
        return R.drawable.bandos_chestplate;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }
}
