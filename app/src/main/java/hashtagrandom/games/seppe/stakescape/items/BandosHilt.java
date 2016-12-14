package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

/**
 * Created by u0098595 on 17/06/2016.
 */
public class BandosHilt extends Item{
    private int value;
    private String name;
    private Rarity rarity;

    public BandosHilt(){
        this.value = 3000000;
        this.name = "Bandos Hilt";
    }

    public BandosHilt(Rarity rarity){
        this.value = 3000000;
        this.name = "Bandos Hilt";
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
        return R.drawable.bandos_hilt;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }
}
