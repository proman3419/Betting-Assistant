package gorman.bettingassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BettingAssistantApplication {

    public static void main(String[] args) {
        //        chooseTicket("49", "40", 1.58, 2.15, 2.31);
        SpringApplication.run(BettingAssistantApplication.class, args);
    }
}
