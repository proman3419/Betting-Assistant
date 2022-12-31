import './App.css';
import { useLocation } from "react-router-dom"

function ResultView() {
  const location = useLocation();
  const params = new URLSearchParams(location.search);

  return (
    <div id="resultDiv">
      <p id="resultParagraph">
        {params.get("result")}
      </p>
    </div>
  )
}

export default ResultView;
