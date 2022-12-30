import './App.css';
import { Link } from 'react-router-dom';
import ResultView from './ResultView';
import { Button, ButtonGroup, Dropdown } from "react-bootstrap";
function FormView() {
  function handleSubmit(e) {
    console.log("ASDSA")
    console.log(document.getElementById("team1Input").value)
    console.log(JSON.stringify({
      team1Id: document.getElementById("team1Input").value,
      team2Id: document.getElementById("team2Input").value,
      team1Odds: document.getElementById("resultTeam1WinBetOddsInput").value,
      drawOdds: document.getElementById("resultTieBetOddsInput").value,
      team2Odds: document.getElementById("resultTeam2WinBetOddsInput").value,
    }))
    e.preventDefault()
    fetch("http://localhost:8080/chooseTicket", {
      method: "POST",
      headers: {"Content-Type": "application/json", 'Accept': 'application/json'},
      body: JSON.stringify({
        team1Id: document.getElementById("team1Input").value,
        team2Id: document.getElementById("team2Input").value,
        team1Odds: document.getElementById("resultTeam1WinBetOddsInput").value,
        drawOdds: document.getElementById("resultTieBetOddsInput").value,
        team2Odds: document.getElementById("resultTeam2WinBetOddsInput").value
      })
    })
    .then((response) => response.json())
    .then(data => { 
      console.log(data);
      window.location.href = "/resultView?result=" + data["result"];
    });
  }

  return (
      <div id="backgroundDiv">
        <div id="superDiv">
          <div id="team1Div">
            <label for="team1Input">Team 1</label>
            <input id="team1Input" name="team1Input" type="text"></input>
          </div>
          <div id="team2Div">
            <label for="team2Input">Team 2</label>
            <input id="team2Input" name="team2Input" type="text"></input>
          </div>
          <div id="resultTeam1WinBetOddsDiv">
            <label for="resultTeam1WinBetOddsInput">resultTeam1WinBetOdds</label>
            <input id="resultTeam1WinBetOddsInput" name="resultTeam1WinBetOddsInput" type="text"></input>
          </div>
          <div id="resultTieBetOddsDiv">
            <label for="resultTieBetOddsInput">resultTieBetOdds</label>
            <input id="resultTieBetOddsInput" name="resultTieBetOddsInput" type="text"></input>
          </div>
          <div id="resultTeam2WinBetOddsDiv">
            <label for="resultTeam2WinBetOddsInput">resultTeam2WinBetOdds</label>
            <input id="resultTeam2WinBetOddsInput" name="resultTeam2WinBetOddsInput" type="text"></input>
          </div>
          <form id="calculateForm">
            <Button id="calculateButton" type="submit" onClick={handleSubmit}>Submit</Button>
            {/* <Link to="resultView" id="calculateButton">adsfads</Link> */}
          </form>
        </div>
      </div>
  );
}

export default FormView;
