package gorman.bettingassistant;

import gorman.bettingassistant.model.AHP;
import gorman.bettingassistant.model.Alternative;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BettingAssistantApplication {

    public static void main(String[] args) {
        chooseTicket("49", "40", 1.58, 2.15, 2.31);
//        TeamStatistics ts = new TeamStatistics(JsonHelper.loadFromFile("responses/statistics.json"));
//        String apiKey = ApiKeyLoader.loadFromFile("api_key.txt");
//        FootballApi footballApi = new FootballApi(apiKey);
//        TeamStatistics ts = new TeamStatistics(footballApi.getTeamStatistics("49"));
//        System.out.println(ts);
//        System.out.println(footballApi.getTeams());
//        H2HStatistics h2hs = new H2HStatistics(footballApi.getH2H("49", "40"));
//        H2HStatistics h2hs = new H2HStatistics(JsonHelper.loadFromFile("responses/h2h.json"));
//        SpringApplication.run(BettingAssistantApplication.class, args);
    }

    public static String chooseTicket(String team1Id, String team2Id, double team1Odds, double drawOdds, double team2Odds){
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
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
