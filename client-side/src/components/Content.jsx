// Content.jsx
import React from 'react';
import Routes from './Routes';

const Content = ({ isAuthenticated, setAuthenticated, selectedGarden, setSelectedGarden }) => {
  return (
    <main className="mt-16 p-4">
      <Routes isAuthenticated={isAuthenticated} setAuthenticated={setAuthenticated} selectedGarden={selectedGarden} setSelectedGarden={setSelectedGarden} />
    </main>
  );
};

export default Content;
