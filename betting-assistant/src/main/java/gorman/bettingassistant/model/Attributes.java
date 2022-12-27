package gorman.bettingassistant.model;

public enum Attributes {
    BET_ODDS(0.125),
    RATIO(0.25),
    OPPONENT_RATIO(0.25),
    H2H_RATIO(0.375);

    private final double priority;
    Attributes(double v) {
        this.priority = v;
    }

    public double getPriority() {
        return priority;
    }
}
