import React from 'react';
import { Link } from 'react-router-dom';

const GardenMap = ({selectedGarden}) => {


  return (
    <section>
      <h2>GardenMap {selectedGarden} </h2>
      <Link to="/gardens">
        <button className="mt-10 mr-4">
          <span className="text-white text-lg md:text-xl lg:text-2xl hover:underline bg-red-800 rounded-full px-8 p-3 cursor-pointer">Explore other gardens</span>
        </button>
      </Link>
    </section>
  );
};

export default GardenMap;