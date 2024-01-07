import React from 'react';
import { BrowserRouter as Router, Link } from 'react-router-dom';
import Home from './Home';
import GardensDropdown from './GardensDropdown';
import GardenMap from './GardenMap';

const Content = ({ isAuthenticated, selectedGarden }) => {
  return (
    <main className="mt-16 w-full p-4">
      {isAuthenticated ? (
        selectedGarden ? (
          <GardenMap />
        ) : (
          <GardensDropdown />
        )
      ) : (
        <Home />
      )}
    </main>
  );
};

export default Content;
