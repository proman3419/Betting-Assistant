import './App.css';
import { Link } from 'react-router-dom';

function FormView() {
  return (
    <div id="superDiv">
      <div id="team1Div">
        <label for="team1Input">Team1</label>
        <input id="team1Input" name="team1Input" type="text"></input>
      </div>
      <div id="team2Div">
        <label for="team2Input">Team2</label>
        <input id="team2Input" name="team2Input" type="text"></input>
      </div>
      <div id="resultTeam1WinBetOddsDiv">
        <label for="resultTeam1WinBetOddsInput">resultTeam1WinBetOdds</label>
        <input id="resultTeam1WinBetOddsInput" name="resultTeam1WinBetOddsInput" type="text"></input>
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
      <Link to="resultView" id="calculateButton">adsfads</Link>
    </div>
  );
}

export default FormView;
