package gorman.bettingassistant.model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.EnumMap;
import java.util.Map;

public class TeamStatistics {
    private final JsonNode all;
    private final EnumMap<MatchResult, Integer> form;

    public TeamStatistics(JsonNode all) {
        this.all = all;
        form = parseForm(all.findValue("form").asText());
    }

    private EnumMap<MatchResult, Integer> parseForm(String formRaw) {
        EnumMap<MatchResult, Integer> formParsed = new EnumMap<>(MatchResult.class);
        formRaw.chars()
                .mapToObj(ch -> MatchResult.valueOf(Character.toString(ch)))
                .forEach(matchResult -> formParsed.merge(matchResult, 1, Integer::sum));
        return formParsed;
    }

    public JsonNode getAll() {
        return all;
    }

    public Map<MatchResult, Integer> getForm() {
        return form;
    }
}
