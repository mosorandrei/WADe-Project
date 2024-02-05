import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { BOTANICAL_GARDEN_ENDPOINT } from '../constants';

const GardensDropdown = ({isAuthenticated, setSelectedGarden}) => {
  const [gardenNames, setGardenNames] = useState([]);
  const [gardensData, setGardensData] = useState({});
  const fetchGardenNames = async () => {
    try {
      const response = await fetch(BOTANICAL_GARDEN_ENDPOINT, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        }
      });
      if (!response.ok) {
        throw new Error('Failed to fetch garden names');
      }
      const data = await response.json();
      setGardensData(data);
      setSelectedGarden(data[0]);
      setGardenNames(data.map(item => item.gardenName));
    } catch (error) {
      console.error('Error fetching garden names:', error);
    }
  };

  useEffect(() => {
    fetchGardenNames();
  }, []);

  const pickGarden = (event) => {
    const selectedGarden = event.target.value;
    setSelectedGarden(gardensData.find(item => item.gardenName == selectedGarden));
  };

  return (
    isAuthenticated && <div className="max-w-xl mx-auto bg-white p-4 md:p-8 rounded shadow-md" vocab="http://schema.org/">
      <h2 className="text-2xl md:text-3xl font-bold mb-4 md:mb-6">Which marvelous botanical garden are you ready to explore?</h2>
      <div className="flex flex-col">
        <label htmlFor="gardenSelect" className="block text-gray-700 font-bold mb-2">Select a garden:</label>
        <select
          id="gardenSelect"
          className="w-full border-2 border-gray-300 p-2 rounded focus:outline-none focus:ring focus:border-blue-400 mb-10"
          onChange={pickGarden}
        >
          <option value="" disabled property="disabled">Select a garden</option>
          {gardenNames.map((garden, index) => (
            <option key={index} value={garden}>{garden}</option>
          ))}
        </select>
      </div>
      <Link to="/garden-map" className="text-white text-lg md:text-xl lg:text-2xl hover:underline bg-red-800 rounded-full px-8 p-3 cursor-pointer">
        Explore Selected Garden
      </Link>
    </div>
  );
};

export default GardensDropdown;
