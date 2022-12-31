import './App.css';
import { useLocation } from "react-router-dom"

function ResultView() {
  const location = useLocation();
  const params = new URLSearchParams(location.search);

  return (
    <div>{params.get("result")}</div>
  )
}

export default ResultView;
