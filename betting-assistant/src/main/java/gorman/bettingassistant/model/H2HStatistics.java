package gorman.bettingassistant.model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class H2HStatistics {
    private final String team1Id;
    private final String team2Id;
    private final EnumMap<MatchResult, Integer> results; // From team1's perspective

    public H2HStatistics(JsonNode H2HRaw) {
        String h2h = H2HRaw.findValue("h2h").asText();
        team1Id = h2h.split("-")[0];
        team2Id = h2h.split("-")[1];
        results = parseResults(H2HRaw);
    }

    private EnumMap<MatchResult, Integer> parseResults(JsonNode H2HRaw) {
        EnumMap<MatchResult, Integer> resultsParsed = new EnumMap<>(MatchResult.class);
        List<JsonNode> matches = H2HRaw.findValues("teams");
        for (JsonNode match : matches) {
            JsonNode home = match.findValue("home");
            String homeTeamId = home.findValue("id").asText();
            String winner = home.findValue("winner").asText();

            if (winner.equals("null")) {
                resultsParsed.merge(MatchResult.D, 1, Integer::sum);
            } else if (homeTeamId.equals(team1Id)) {
                if (winner.equals("true")) {
                    resultsParsed.merge(MatchResult.W, 1, Integer::sum);
                } else {
                    resultsParsed.merge(MatchResult.L, 1, Integer::sum);
                }
            } else {
                if (winner.equals("true")) {
                    resultsParsed.merge(MatchResult.L, 1, Integer::sum);
                } else {
                    resultsParsed.merge(MatchResult.W, 1, Integer::sum);
                }
            }
        }
        return resultsParsed;
    }

    public String getTeam1Id() {
        return team1Id;
    }

    public String getTeam2Id() {
        return team2Id;
    }

    public Map<MatchResult, Integer> getResults() {
        return results;
    }
}
