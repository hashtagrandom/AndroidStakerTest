package hashtagrandom.games.seppe.stakescape.pojo;

import hashtagrandom.games.seppe.stakescape.util.Util;



/**
 * Created by u0098595 on 13/06/2016.
 */
public class Dds extends Weapon implements Special {
    private int decreaseSpec;

    public Dds(){
        decreaseSpec = 25;
    }

    @Override
    public int getValue() {
        return 20000;
    }

    public int getHit() {
        return Util.randInt(0, 20);
    }

    public int getHit2() {
        return Util.randInt(0, 20);
    }

    @Override
    public int getDecreaseSpec() {
        return decreaseSpec;
    }
}
