package hashtagrandom.games.seppe.stakescape.items;

import hashtagrandom.games.seppe.stakescape.R;

/**
 * Created by u0098595 on 17/06/2016.
 */
public class GodswordShard1 extends Item {
    private int value;
    private String name;
    private Rarity rarity;

    public GodswordShard1(){
        this.value = 150000;
        this.name = "Godsword Shard 1";
    }

    public GodswordShard1(Rarity rarity){
        this.value = 150000;
        this.name = "Godsword Shard 1";
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
        return R.drawable.godsword_shard_1;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }
}
