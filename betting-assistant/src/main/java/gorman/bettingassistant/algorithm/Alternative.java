package gorman.bettingassistant.algorithm;

public class Alternative {
    private final double minRatioValue = 0.0001;
    private final String name;
    private final double betOdds;
    private final double ratio;
    private final double opponentRatio;
    private final double h2hRatio;

    public Alternative(String name, double betOdds, double ratio, double opponentRatio, double h2hRatio) {
        this.name = name;
        this.betOdds = betOdds;
        this.opponentRatio = Math.max(opponentRatio, minRatioValue);
        this.ratio = Math.max(ratio, minRatioValue);
        this.h2hRatio = Math.max(h2hRatio, minRatioValue);
    }

    public double getAttribute(Attributes attribute) {
        return switch (attribute){
            case RATIO -> ratio;
            case BET_ODDS -> betOdds;
            case H2H_RATIO -> h2hRatio;
            case OPPONENT_RATIO -> opponentRatio;
        };
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Alternative{" +
                "name='" + name + '\'' +
                ", betOdds=" + betOdds +
                ", ratio=" + ratio +
                ", opponentRatio=" + opponentRatio +
                ", h2hRatio=" + h2hRatio +
                '}';
    }
}
