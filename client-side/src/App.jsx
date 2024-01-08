import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import Header from './components/Header';
import Content from './components/Content';
import Footer from './components/Footer';

import './App.css';

function App() {
  const [isAuthenticated, setAuthenticated] = useState(false);
  const [selectedGarden, setSelectedGarden] = useState(null);
  const [currentPage, setCurrentPage] = useState('Home');

  return (
    <Router>
      <div>
        <Header isAuthenticated={isAuthenticated} setAuthenticated={setAuthenticated} setCurrentPage={setCurrentPage} />
        <Content isAuthenticated={isAuthenticated} setAuthenticated={setAuthenticated} selectedGarden={selectedGarden} setSelectedGarden={setSelectedGarden} currentPage={currentPage} setCurrentPage={setCurrentPage} />
        <Footer />
      </div>
    </Router>
  );
}

export default App;
