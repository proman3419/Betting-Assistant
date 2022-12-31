import './App.css';
import FormView from './FormView.js';
import ResultView from './ResultView';
import TeamsView from './TeamsView';
import HomeView from './HomeView';
import { Routes, Route, BrowserRouter } from 'react-router-dom';

function App() {
  return (
    <BrowserRouter>
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/">Betting Assistant</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <a class="nav-link" href="/">Home <span class="sr-only"></span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="formView">Betting assistance</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="teamsView">Teams</a>
            </li>
          </ul>
        </div>
      </nav>
      <div>
        <Routes>
          <Route path="/" element={<HomeView />} />
          <Route path="formView" element={<FormView />} />
          <Route path="resultView" element={<ResultView />} />
          <Route path="teamsView" element={<TeamsView />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
