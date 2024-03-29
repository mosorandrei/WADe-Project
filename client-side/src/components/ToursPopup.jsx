import ReactModal from 'react-modal';
import React, { useState } from 'react';
import TourInfo from './TourInfo';
const customStyles = {
  content: {
    width: '60%',
    height: '70%',
    margin: 'auto',
  },
};

const ToursPopup = ({ tours, showTours, onClose }) => {
  const [tourStates, setTourStates] = useState(tours.map(() => false));

  const handleTourClick = (index) => {
    const updatedTourStates = [...tourStates];
    updatedTourStates[index] = true;
    setTourStates(updatedTourStates);
  };

  const handleTourInfoClose = (index) => {
    const updatedTourStates = [...tourStates];
    updatedTourStates[index] = false;
    setTourStates(updatedTourStates);
  };

  return (
    <ReactModal
      isOpen={showTours}
      onRequestClose={onClose}
      contentLabel="Tours"
      style={customStyles}
    >
      {tours.map((tour, index) => (
        <div key={index} className="bg-white p-4 mb-4 rounded-md border border-blue-300">
          <div className="flex items-center">
            <div className="w-1/4">
              <h3 className="text-xl font-bold">{tour.tourName}</h3>
            </div>
            <div className="w-1/4 text-center">
              <p>
                {tour.tourStartHour.split("^^")[0]}:00 - {tour.tourEndHour.split("^^")[0]}:00
              </p>
            </div>
            <div className="w-1/4 text-center">
              <p>
                Seats Available: {tour.tourTotalSeats.split("^^")[0]}
              </p>
            </div>
            <div className="w-1/4 flex justify-end">
              <button className="bg-blue-500 text-white px-4 py-2 rounded" onClick={() => handleTourClick(index)}>
                Check this out!
              </button>
            </div>
          </div>
          <TourInfo
            shownTour={tourStates[index]}
            onClose={() => handleTourInfoClose(index)}
            tour={tour}
          />
        </div>
      ))}
      <button
        onClick={onClose}
        className="absolute bottom-2 right-2 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
      >
        Close
      </button>
    </ReactModal>
  );
};

export default ToursPopup;
