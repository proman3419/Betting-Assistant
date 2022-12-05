package gorman.bettingassistant;

import com.fasterxml.jackson.databind.JsonNode;

public class TeamStatistics {
    private final JsonNode all;
    private final JsonNode form;
    private final JsonNode goals;

    private final JsonNode cleanSheet;
    private final JsonNode failedToScore;

    public TeamStatistics(JsonNode all) {
        this.all = all;
        form = all.findValue("goals");
        goals = all.findValue("fixtures");
        cleanSheet = all.findValue("clean_sheet");
        failedToScore = all.findValue("failed_to_score");
    }

    public JsonNode getAll() {
        return all;
    }

    public JsonNode getForm() {
        return form;
    }

    public JsonNode getGoals() {
        return goals;
    }

    public JsonNode getCleanSheet() {
        return cleanSheet;
    }

    public JsonNode getFailedToScore() {
        return failedToScore;
    }
}
