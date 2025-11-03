package lotto.domain;

public enum Rank {
    SIX(6, false, 2_000_000_000),
    FIVE_BONUS(5, true, 30_000_000),
    FIVE(5, false, 1_500_000),
    FOUR(4, false, 50_000),
    THREE(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchedCount;
    private final boolean matchBonus;
    private final int prize;

    Rank(int matchedCount, boolean matchBonus, int prize) {
        this.matchedCount = matchedCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
    }

    public int getPrize() {
        return prize;
    }

    public static Rank valueOf(int matchedCount, boolean bonusMatched) {
        if (matchedCount == 6) return SIX;
        if (matchedCount == 5 && bonusMatched) return FIVE_BONUS;
        if (matchedCount == 5) return FIVE;
        if (matchedCount == 4) return FOUR;
        if (matchedCount == 3) return THREE;
        return NONE;
    }
}