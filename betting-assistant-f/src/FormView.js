import './App.css';
import { Link } from 'react-router-dom';
import ResultView from './ResultView';
import { Button, ButtonGroup, Dropdown, Form, FormGroup } from "react-bootstrap";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

function FormView() {
  function handleSubmit(e) {
    console.log("ASDSA")
    console.log(document.getElementById("team1Input").value);
    console.log(document.getElementById("team2Input").value);
    console.log(document.getElementById("resultTeam1WinBetOddsInput").value);
    console.log(document.getElementById("resultTieBetOddsInput").value);
    console.log(document.getElementById("resultTeam2WinBetOddsInput").value);
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
      <Container >
        <Form>
          <Row>
            <Col md={{ span: 6, offset: 3 }}>
              <Form.Group className="mb-3" controlId="team1Input">
                <Form.Label>Team 1</Form.Label>
                <Form.Control type="text" id="team1Input" name="team1Input" placeholder="Ex. 49" />
              </Form.Group>
            </Col>
          </Row>

          <Row>
            <Col md={{ span: 6, offset: 3 }}>
              <Form.Group className="mb-3" controlId="team2Input">
                <Form.Label>Team 1</Form.Label>
                <Form.Control type="text" id="team2Input" name="team2Input" placeholder="Ex. 40" />
              </Form.Group>
            </Col>
          </Row>

          <Row>
            <Col md={{ span: 6, offset: 3 }}>
              <Form.Group className="mb-3" controlId="resultTeam1WinBetOddsDiv">
                <Form.Label>Bet Odds for TEAM 1 WIN</Form.Label>
                <Form.Control type="text" id="resultTeam1WinBetOddsInput" name="resultTeam1WinBetOddsInput" placeholder="Ex. 2.14"/>
              </Form.Group>
            </Col>
          </Row>

          <Row>
            <Col md={{ span: 6, offset: 3 }}>
              <Form.Group className="mb-3" controlId="resultTieBetOddsDiv">
                <Form.Label>Bet Odds for DRAW</Form.Label>
                <Form.Control type="text" id="resultTieBetOddsInput" name="resultTieBetOddsInput" placeholder="Ex. 1.77"/>
              </Form.Group>
            </Col>
          </Row>

          <Row>
            <Col md={{ span: 6, offset: 3 }}>
              <Form.Group className="mb-3" controlId="resultTeam2WinBetOddsDiv">
                <Form.Label>Bet Odds for TEAM 2 WIN</Form.Label>
                <Form.Control type="text" id="resultTeam2WinBetOddsInput" name="resultTeam2WinBetOddsInput" placeholder="Ex. 1.53"/>
              </Form.Group>
            </Col>
          </Row>

          <Row>
            <Col md={{ span: 6, offset: 3 }} className="text-center">
              <Button variant="primary" onClick={handleSubmit}>
                Submit
              </Button>
            </Col>
          </Row>
        </Form>
      </Container>

  );
}

export default FormView;
