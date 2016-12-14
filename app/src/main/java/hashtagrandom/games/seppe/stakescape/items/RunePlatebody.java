package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

/**
 * Created by u0098595 on 17/06/2016.
 */
public class RunePlatebody extends Item{
    private int value;
    private String name;
    private Rarity rarity;

    public RunePlatebody(){
        this.value = 50000;
        this.name = "Rune Platebody";
    }

    public RunePlatebody(Rarity rarity){
        this.value = 50000;
        this.name = "Rune Platebody";
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
        return R.drawable.rune_platebody;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }
}
