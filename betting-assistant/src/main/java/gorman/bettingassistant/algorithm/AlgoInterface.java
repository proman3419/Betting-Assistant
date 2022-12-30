package gorman.bettingassistant.algorithm;

import gorman.bettingassistant.api.ApiKeyLoader;
import gorman.bettingassistant.api.FootballApi;
import gorman.bettingassistant.model.H2HStatistics;
import gorman.bettingassistant.model.MatchResult;
import gorman.bettingassistant.model.TeamStatistics;

public class AlgoInterface {
    public static String chooseTicket(String team1Id, String team2Id, double team1Odds, double drawOdds, double team2Odds) {
        String apiKey = ApiKeyLoader.loadFromFile("api_key.txt");
        FootballApi footballApi = new FootballApi(apiKey);
        H2HStatistics h2h = new H2HStatistics(footballApi.getH2H(team1Id, team2Id));
        TeamStatistics ts1 = new TeamStatistics(footballApi.getTeamStatistics(team1Id));
        TeamStatistics ts2 = new TeamStatistics(footballApi.getTeamStatistics(team2Id));

        double h2hSize = h2h.getResults().values().stream().mapToDouble(s -> s).sum();
        double t1Size = ts1.getForm().values().stream().mapToDouble(s -> s).sum();
        double t2Size = ts2.getForm().values().stream().mapToDouble(s -> s).sum();

        System.out.println(ts1.getForm());
        System.out.println(ts2.getForm());
        System.out.println(h2h.getResults());


        AHP ahp = new AHP();
        Alternative win = new Alternative("win", team1Odds,
                ts1.getForm().get(MatchResult.W) / t1Size,
                ts2.getForm().get(MatchResult.L) / t2Size,
                h2h.getResults().get(MatchResult.W) / h2hSize);
        Alternative draw = new Alternative("draw", drawOdds,
                ts1.getForm().get(MatchResult.D) / t1Size,
                ts2.getForm().get(MatchResult.D) / t2Size,
                h2h.getResults().get(MatchResult.D) / h2hSize);
        Alternative lose = new Alternative("lose", team2Odds,
                ts1.getForm().get(MatchResult.L) / t1Size,
                ts2.getForm().get(MatchResult.W) / t2Size,
                h2h.getResults().get(MatchResult.L) / h2hSize);

        ahp.addAlternative(win);
        ahp.addAlternative(draw);
        ahp.addAlternative(lose);

        System.out.println(win);
        System.out.println(draw);
        System.out.println(lose);

        ahp.createMatrixes();
        ahp.createPrioritizationVectors();
        String res = ahp.getBestAlternative();

        System.out.println(res);
        return res;
    }
}
