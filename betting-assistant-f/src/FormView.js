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
      <Container >
        <Form onSubmit={handleSubmit}>
          <Row>
            <Col md={{ span: 6, offset: 3 }}>
              <Form.Group className="mb-3" controlId="team1Input">
                <Form.Label>Team 1</Form.Label>
                <Form.Control type="text" id="team1Input" name="team1Input" placeholder="Team 1 ID" />
              </Form.Group>
            </Col>
          </Row>

          <Row>
            <Col md={{ span: 6, offset: 3 }}>
              <Form.Group className="mb-3" controlId="team2Input">
                <Form.Label>Team 1</Form.Label>
                <Form.Control type="text" id="team2Input" name="team2Input" placeholder="Team 2 ID" />
              </Form.Group>
            </Col>
          </Row>

          <Row>
            <Col md={{ span: 6, offset: 3 }}>
              <Form.Group className="mb-3" controlId="resultTeam1WinBetOddsDiv">
                <Form.Label>Bet Odds for TEAM 1 WIN</Form.Label>
                <Form.Control type="text" id="resultTeam1WinBetOddsInput" name="resultTeam1WinBetOddsInput"/>
              </Form.Group>
            </Col>
          </Row>

          <Row>
            <Col md={{ span: 6, offset: 3 }}>
              <Form.Group className="mb-3" controlId="resultTieBetOddsDiv">
                <Form.Label>Bet Odds for DRAW</Form.Label>
                <Form.Control type="text" id="resultTieBetOddsDiv" name="resultTieBetOddsDiv"/>
              </Form.Group>
            </Col>
          </Row>

          <Row>
            <Col md={{ span: 6, offset: 3 }}>
              <Form.Group className="mb-3" controlId="resultTeam2WinBetOddsDiv">
                <Form.Label>Bet Odds for TEAM 2 WIN</Form.Label>
                <Form.Control type="text" id="resultTeam2WinBetOddsDiv" name="resultTeam2WinBetOddsDiv"/>
              </Form.Group>
            </Col>
          </Row>

          <Row>
            <Col md={{ span: 6, offset: 3 }} className="text-center">
              <Button variant="primary" type="submit">
                Submit
              </Button>
            </Col>
          </Row>
        </Form>
      </Container>

  );
}

export default FormView;
