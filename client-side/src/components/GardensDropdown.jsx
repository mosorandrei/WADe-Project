import React from 'react';

const GardensDropdown = ({setSelectedGarden, setCurrentPage}) => {
  const gardenNames = ["Garden 1", "Garden 2", "Garden 3"];

  const selectGarden = (garden) => {
    setSelectedGarden(garden);
    setCurrentPage("GardenMap");
  }

  return (
    <div className="max-w-xl mx-auto bg-white p-4 md:p-8 rounded shadow-md">
      <h2 className="text-2xl md:text-3xl font-bold mb-4 md:mb-6">Which marvelous botanical garden are you ready to explore?</h2>
      <div className="flex flex-col">
        <label htmlFor="gardenSelect" className="block text-gray-700 font-bold mb-2">Select a garden:</label>
        <select
          id="gardenSelect"
          className="w-full border-2 border-gray-300 p-2 rounded focus:outline-none focus:ring focus:border-blue-400"
        >
          <option value="" disabled>Select a garden</option>
          {gardenNames.map((garden, index) => (
            <option key={index} value={garden} onClick = {selectGarden(garden)} >{garden}</option>
          ))}
        </select>
      </div>
    </div>
  );
};

export default GardensDropdown;
