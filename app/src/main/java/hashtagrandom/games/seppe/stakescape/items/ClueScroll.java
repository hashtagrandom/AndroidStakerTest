package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

/**
 * Created by u0098595 on 17/06/2016.
 */
public class ClueScroll extends Item{
    private int value;
    private String name;
    private Rarity rarity;

    public ClueScroll(){
        this.value = 0;
        this.name = "Clue Scroll";
    }

    public ClueScroll(Rarity rarity){
        this.value = 0;
        this.name = "Clue Scroll";
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
        return R.drawable.clue_scroll;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }
}
