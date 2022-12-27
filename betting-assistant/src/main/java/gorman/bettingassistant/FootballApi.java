package gorman.bettingassistant;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicHeader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static gorman.bettingassistant.FootballApiConfig.*;

public class FootballApi {
    private final String apiKey;

    public FootballApi(String apiKey) {
        this.apiKey = apiKey;
    }

    private HttpRequest.Builder getRequestBase() {
        return HttpRequest.newBuilder()
                .header("x-rapidapi-key", apiKey)
                .header("x-rapidapi-host", API_HOST)
                .GET();
    }

    private URI createApiUri(String apiUriExtension, List<NameValuePair> params) {
        String apiUriBase = apiUriExtension.length() > 0 ? API_URL + "/" + apiUriExtension : API_URL;
        try {
            return new URIBuilder(apiUriBase).addParameters(params).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private JsonNode sendRequest(URI apiUri) {
        HttpRequest.Builder requestBuilder = getRequestBase();
        requestBuilder.uri(apiUri);
        HttpRequest request = requestBuilder.build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        JsonNode responseBody = null;
        if (response.statusCode() == 200) {
            responseBody = JsonHelper.loadTree(response.body());
        }
        return responseBody;
    }

    public JsonNode getTeams() {
        List<NameValuePair> params = new ArrayList<>(Arrays.asList(
                new BasicHeader("season", SEASON),
                new BasicHeader("league", LEAGUE_ID)));
        URI apiUri = createApiUri("teams", params);
        return sendRequest(apiUri);
    }

    public JsonNode getH2H(String team1Id, String team2Id) {
        List<NameValuePair> params = new ArrayList<>(Arrays.asList(
                new BasicHeader("last", LAST_MATCHES_COUNT),
                new BasicHeader("status", MATCH_STATUS),
                new BasicHeader("h2h", team1Id + "-" + team2Id)));
        URI apiUri = createApiUri("fixtures/headtohead", params);
        return sendRequest(apiUri);
    }

    public JsonNode getTeamStatistics(String teamId) {
        List<NameValuePair> params = new ArrayList<>(Arrays.asList(
                new BasicHeader("season", SEASON),
                new BasicHeader("league", LEAGUE_ID),
                new BasicHeader("team", teamId)));
        URI apiUri = createApiUri("teams/statistics", params);
        return sendRequest(apiUri);
    }
}
