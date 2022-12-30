package gorman.bettingassistant.model;

public class ChooseTicketBody {
    private final String team1Id;
    private final String team2Id;
    private final double team1Odds;
    private final double drawOdds;
    private final double team2Odds;

    public ChooseTicketBody(String team1Id, String team2Id, double team1Odds, double drawOdds, double team2Odds) {
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.team1Odds = team1Odds;
        this.drawOdds = drawOdds;
        this.team2Odds = team2Odds;
    }

    public String getTeam1Id() {
        return team1Id;
    }

    public String getTeam2Id() {
        return team2Id;
    }

    public double getTeam1Odds() {
        return team1Odds;
    }

    public double getDrawOdds() {
        return drawOdds;
    }

    public double getTeam2Odds() {
        return team2Odds;
    }
}
