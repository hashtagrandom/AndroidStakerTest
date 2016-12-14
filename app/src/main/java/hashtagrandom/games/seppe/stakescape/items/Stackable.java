package hashtagrandom.games.seppe.stakescape.items;

/**
 * Methodes die gebruikt moeten worden bij een item dat stackable is.
 */
public interface Stackable {
    int getAmount();
    void addAmount(int amount);
    void setAmount(int amount);
    void decreaseAmount(int amount);
}
