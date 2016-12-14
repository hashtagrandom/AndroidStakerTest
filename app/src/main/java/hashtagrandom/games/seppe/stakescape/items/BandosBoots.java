package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

/**
 * Created by u0098595 on 17/06/2016.
 */
public class BandosBoots extends Item{
    private int value;
    private String name;
    private Rarity rarity;

    public BandosBoots(){
        this.value = 250000;
        this.name = "Bandos Boots";
    }

    public BandosBoots(Rarity rarity){
        this.value = 250000;
        this.name = "Bandos Boots";
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
        return R.drawable.bandos_boots;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }
}
