import React from 'react';
import Home from './Home';
import GardensDropdown from './GardensDropdown';
import GardenMap from './GardenMap';
import Login from './Login';
import Register from './Register';

const Content = ({ isAuthenticated, setAuthenticated, selectedGarden, setSelectedGarden, currentPage, setCurrentPage }) => {
  const isHome = currentPage === 'Home';
  
  return (
    <main className="mt-16 p-4">
      {isAuthenticated && currentPage === 'GardensDropdown' && <GardensDropdown setSelectedGarden={setSelectedGarden} setCurrentPage={setCurrentPage} />}
      {isAuthenticated && currentPage === 'GardenMap' && <GardenMap selectedGarden={selectedGarden} setSelectedGarden={setSelectedGarden} setCurrentPage={setCurrentPage}/>}
      {!isAuthenticated && currentPage === 'Login' && <Login isAuthenticated= {isAuthenticated} setAuthenticated={setAuthenticated} setCurrentPage={setCurrentPage} />}
      {!isAuthenticated && currentPage === 'Register' && <Register isAuthenticated= {isAuthenticated} setAuthenticated={setAuthenticated} setCurrentPage={setCurrentPage}/>}
      {(isHome || !currentPage) && <Home isAuthenticated={isAuthenticated} setCurrentPage={setCurrentPage} />}
    </main>
  );
};

export default Content;
