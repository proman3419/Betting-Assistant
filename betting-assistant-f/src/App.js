import './App.css';
import FormView from './FormView.js';
import ResultView from './ResultView';
import { Routes, Route, BrowserRouter } from 'react-router-dom';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<FormView />} />
        <Route path="resultView" element={<ResultView />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
