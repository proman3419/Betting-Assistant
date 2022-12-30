package gorman.bettingassistant;

import gorman.bettingassistant.algorithm.AlgoInterface;
import gorman.bettingassistant.model.ChooseTicketBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class BettingAssistantController {
    @PostMapping("/chooseTicket")
    public String chooseTicket(@RequestBody ChooseTicketBody chooseTicketBody) {
        String result = AlgoInterface.chooseTicket(
                chooseTicketBody.getTeam1Id(),
                chooseTicketBody.getTeam2Id(),
                chooseTicketBody.getTeam1Odds(),
                chooseTicketBody.getDrawOdds(),
                chooseTicketBody.getTeam2Odds());
        return "{\"result\":\"" + result + "\"}";
    }
}
