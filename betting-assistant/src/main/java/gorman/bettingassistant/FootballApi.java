package gorman.bettingassistant;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Nullable;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FootballApi {
    private static final String API_URL = "https://v3.football.api-sports.io";
    private static final String API_HOST = "v3.football.api-sports.io";
    private static final String SEASON = "2022";
    private static final String LEAGUE_ID = "39";
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

    private URIBuilder getRequestUriBase(String apiUrlExtension) {
        String apiUrl = apiUrlExtension.length() > 0 ? API_URL + "/" + apiUrlExtension : API_URL;
        try {
            return new URIBuilder(apiUrl);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private JsonNode sendRequest(HttpRequest.Builder requestBuilder) {
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

    @Nullable
    public JsonNode getTeams() {
        HttpRequest.Builder requestBuilder = getRequestBase();
        URI apiUri;
        try {
            apiUri = getRequestUriBase("/teams")
                    .addParameter("season", SEASON)
                    .addParameter("league", LEAGUE_ID)
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        requestBuilder.uri(apiUri);
        return sendRequest(requestBuilder);
    }
}
