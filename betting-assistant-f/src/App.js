import './App.css';
import FormView from './FormView.js';
import ResultView from './ResultView';
import { Routes, Route } from 'react-router-dom';

function App() {
  return (
    <Routes>
          <Route path="/resultView" element={ <ResultView /> } />
          <Route path="/" element={ <FormView /> } />
    </Routes>
  )
}

export default App;
