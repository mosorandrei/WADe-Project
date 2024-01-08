import React from 'react';

const GardenMap = ({selectedGarden, setSelectedGarden, setCurrentPage}) => {

  const goBackToGardens = () => {
    
    setCurrentPage("GardensDropdown");
  }


  return (
    <section>
      <h2>GardenMap {selectedGarden} </h2>
      <button className="mt-10 mr-4" onClick={goBackToGardens}><span className="text-white text-lg md:text-xl lg:text-2xl hover:underline bg-red-800 rounded-full px-8 p-3 cursor-pointer">Explore other gardens</span></button>
    </section>
  );
};

export default GardenMap;