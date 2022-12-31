import './App.css';
import { Button, Form } from "react-bootstrap";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

function FormView() {
  function handleSubmit(e) {
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
      let result = data["result"];
      let resultPretty = "";
      if (result === "win") {
        resultPretty = "Team 1 will win";
      } else if (result === "lose") {
        resultPretty = "Team 2 will win";
      } else {
        resultPretty = "Teams will draw";
      }
      window.location.href = "/resultView?result=" + resultPretty;
    });
  }

  return (
      <Container >
        <Form>
          <Row>
            <Col md={{ span: 6, offset: 3 }}>
              <Form.Group className="mb-3" controlId="team1Input">
                <Form.Label>Team 1 ID</Form.Label>
                <Form.Control type="text" id="team1Input" name="team1Input" placeholder="Ex. 49" />
              </Form.Group>
            </Col>
          </Row>

          <Row>
            <Col md={{ span: 6, offset: 3 }}>
              <Form.Group className="mb-3" controlId="team2Input">
                <Form.Label>Team 2 ID</Form.Label>
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
