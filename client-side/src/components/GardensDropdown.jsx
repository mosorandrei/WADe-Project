import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';

const GardensDropdown = ({isAuthenticated, setSelectedGarden}) => {
  const gardenNames = ["Garden 1", "Garden 2", "Garden 3"];

  const selectGarden = (event) => {
    const selectedGarden = event.target.value;
    setSelectedGarden(selectedGarden);
  }

  useEffect(() => {
    setSelectedGarden(gardenNames[0]);
  }, [setSelectedGarden]);

  return (
    isAuthenticated && <div className="max-w-xl mx-auto bg-white p-4 md:p-8 rounded shadow-md" vocab="http://schema.org/">
      <h2 className="text-2xl md:text-3xl font-bold mb-4 md:mb-6" property="headline">Which marvelous botanical garden are you ready to explore?</h2>
      <div className="flex flex-col">
        <label htmlFor="gardenSelect" className="block text-gray-700 font-bold mb-2" property="label">Select a garden:</label>
        <select
          id="gardenSelect"
          className="w-full border-2 border-gray-300 p-2 rounded focus:outline-none focus:ring focus:border-blue-400 mb-10"
          onChange={selectGarden}
          property="gardenSelection"
        >
          <option value="" disabled property="disabled">Select a garden</option>
          {gardenNames.map((garden, index) => (
            <option key={index} value={garden} property="gardenOption">{garden}</option>
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
