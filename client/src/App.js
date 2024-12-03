import React from 'react';
import { HashRouter, Route, Routes } from 'react-router';
import Main from './views/Main';
import './assets/style.css'

function App() {
  return (
    <HashRouter>
      <Routes>
        <Route exact path="/main" element={<Main />}/>
      </Routes>
    </HashRouter>
  );
}

export default App;
