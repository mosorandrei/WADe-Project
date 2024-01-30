import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router } from 'react-router-dom';

import Header from './components/Header';
import Content from './components/Content';
import Footer from './components/Footer';

import './App.css';

function App() {
  const [isAuthenticated, setAuthenticated] = useState(false);
  const [selectedGarden, setSelectedGarden] = useState(null);

  return (
    <Router
      typeof="schema:WebSite"
      vocab="http://schema.org/"
    >
      <div
        typeof="schema:WebPage"
        property="schema:name schema:description"
        content="My Botanical Web Guide"
      >
        <Header
          isAuthenticated={isAuthenticated}
          setAuthenticated={setAuthenticated}
          property="schema:header"
        />
        <Content
          isAuthenticated={isAuthenticated}
          setAuthenticated={setAuthenticated}
          selectedGarden={selectedGarden}
          setSelectedGarden={setSelectedGarden}
          property="schema:mainContentOfPage"
        />
        <Footer
          property="schema:footer"
        />
      </div>
    </Router>
  );
}

export default App;
