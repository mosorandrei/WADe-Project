import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import GardenMatrix from './GardenMatrix';
import ToursPopup from './ToursPopup';
import { BOTANICAL_GARDEN_ENDPOINT } from '../constants';

const GardenMap = ({isAuthenticated, selectedGarden}) => {
  const [cellSize, setCellSize] = useState(180);
  const [showTours, setShowTours] = useState(false);


  const [tours, setTours] = useState(selectedGarden.tours);

  useEffect(() => {
    const fetchGardenTours = async () => {
      try {
        const response = await fetch(BOTANICAL_GARDEN_ENDPOINT, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
          }
        });
        if (!response.ok) {
          throw new Error('Failed to fetch garden tours');
        }
        const data = await response.json();
        setTours(data.find(item => selectedGarden.gardenName == item.gardenName).tours);
      } catch (error) {
        console.error('Error fetching garden tours:', error);
      }
    };
    fetchGardenTours();
  }, []);

  useEffect(() => {
    const handleResize = () => {
      const newCellSize = (window.innerHeight * 15) / 100;
      setCellSize(newCellSize);
    };

    handleResize();
    window.addEventListener('resize', handleResize);

    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, []);

  return isAuthenticated && selectedGarden && (
    <section typeof="bot:Garden">
      <h2 className="mb-10 text-4xl">Map of <p className='italic mt-2 font-bold text-6xl' property='bot:hasGardenName'>{selectedGarden.gardenName}</p>
        <div>
        <Link to="/gardens">
        <button className="mt-10 mr-4">
          <span className="text-white text-lg md:text-xl lg:text-2xl hover:underline bg-red-800 rounded-full px-8 p-3 cursor-pointer">
            Explore other gardens!
          </span>
        </button>
      </Link>
        <button className="mt-10 mr-4" onClick={()=>setShowTours(true)}>
          <span className="text-white text-lg md:text-xl lg:text-2xl hover:underline bg-red-800 rounded-full px-8 p-3 cursor-pointer">
            Check available tours!
          </span>
        </button>
        </div>
      </h2>
      <GardenMatrix gardenInfo={selectedGarden} cellSize={cellSize} />
      <ToursPopup 
          tours={tours}
          showTours={showTours}
          onClose={() => setShowTours(false)}
        />
    </section>
  );
};

export default GardenMap;