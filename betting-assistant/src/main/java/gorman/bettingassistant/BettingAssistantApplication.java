package gorman.bettingassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BettingAssistantApplication {

    public static void main(String[] args) {
        TeamStatistics ts = new TeamStatistics(JsonHelper.loadFromFile("responses/statistics.json"));
        String apiKey = ApiKeyLoader.loadFromFile("api_key.txt");
        FootballApi footballApi = new FootballApi(apiKey);
        System.out.println(footballApi.getTeams());
        SpringApplication.run(BettingAssistantApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
