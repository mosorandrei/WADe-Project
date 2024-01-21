import React from 'react';
import { Route, Routes as ReactRoutes } from 'react-router-dom';
import Home from './Home';
import GardensDropdown from './GardensDropdown';
import GardenMap from './GardenMap';
import Login from './Login';
import Register from './Register';

const Routes = ({ isAuthenticated, setAuthenticated, selectedGarden, setSelectedGarden }) => {
  return (
    <ReactRoutes>
      <Route path="/" element={<Home isAuthenticated={isAuthenticated} />} />
      <Route path="/gardens" element={<GardensDropdown setSelectedGarden={setSelectedGarden} />} />
      <Route path="/garden-map" element={<GardenMap selectedGarden={selectedGarden} setSelectedGarden={setSelectedGarden} />} />
      <Route path="/login" element={<Login isAuthenticated={isAuthenticated} setAuthenticated={setAuthenticated} />} />
      <Route path="/register" element={<Register isAuthenticated={isAuthenticated} setAuthenticated={setAuthenticated} />} />
    </ReactRoutes>
  );
};

export default Routes;
