package gorman.bettingassistant.model;

public class Alternative {
    private final String name;
    private final double betOdds;
    private final double ratio;
    private final double opponentRatio;
    private final double h2hRatio;

    public Alternative(String name, double betOdds, double ratio, double opponentRatio, double h2hRatio) {
        this.name = name;
        this.betOdds = betOdds;
        this.opponentRatio = opponentRatio;
        this.ratio = ratio;
        this.h2hRatio = h2hRatio;
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
}
