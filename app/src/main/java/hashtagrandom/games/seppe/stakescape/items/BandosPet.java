package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

/**
 * Created by u0098595 on 17/06/2016.
 */
public class BandosPet extends Item{
    private int value;
    private String name;
    private Rarity rarity;

    public BandosPet(){
        this.value = 10000000;
        this.name = "Bandos Pet";
    }

    public BandosPet(Rarity rarity){
        this.value = 1000000;
        this.name = "Bandos Pet";
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
        return R.drawable.bandos_pet;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }
    
}
