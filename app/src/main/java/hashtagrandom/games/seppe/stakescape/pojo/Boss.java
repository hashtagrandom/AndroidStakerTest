package hashtagrandom.games.seppe.stakescape.pojo;

/**
 * Created by u0098595 on 16/06/2016.
 */
public abstract class Boss {
    abstract String AIAttack(User user);

    abstract void decreaseHp(int amount);


    //TODO: ook de loot per boss terug geven via methode
}
