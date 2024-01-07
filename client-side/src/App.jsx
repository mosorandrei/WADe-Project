import React, { useState } from 'react';
import { BrowserRouter as Router} from 'react-router-dom';
import Header from './components/Header';
import Content from './components/Content';
import Footer from './components/Footer';

import './App.css';

function App() {
  const [isAuthenticated, setAuthenticated] = useState(false);
  const [selectedGarden, setSelectedGarden] = useState(null); 
  return (
    <Router>
      <div>
        <Header isAuthenticated={isAuthenticated}/>
        <Content isAuthenticated={isAuthenticated} selectedGarden={null}/>
        <Footer />
      </div>
    </Router>
  );
}

export default App
