package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

/**
 * Created by u0098595 on 17/06/2016.
 */
public class BandosTassets extends Item {
    private int value;
    private String name;
    private Rarity rarity;

    public BandosTassets(){
        this.value = 25000000;
        this.name = "Bandos Tassets";
    }

    public BandosTassets(Rarity rarity){
        this.value = 25000000;
        this.name = "Bandos Tassets";
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
        return R.drawable.bandos_tassets;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }
}
