import React, { useState } from 'react';
import ReactModal from 'react-modal';

const customStyles = {
  content: {
    width: '40%',
    height: '70%',
    margin: 'auto',
  },
};

const AttractionPopup = ({ attraction, detailsShown, onClose }) => {
  return (
    <ReactModal
      isOpen={detailsShown}
      onRequestClose={onClose}
      contentLabel="Attraction Details"
      style={customStyles}
      typeof="bot:Attraction"
    >
      <div className="attraction-popup border-2 border-white h-full relative">
        <button
          onClick={onClose}
          className="absolute top-2 right-2 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
        >
          Close
        </button>
        <div className="flex justify-end p-5"></div>
        <div className="p-10 overflow-auto">
          <div className="text-6xl mb-5">
            Name: <span className="italic" property='bot:hasAttractionName'>{attraction.attractionName}</span>
          </div>
          <div className="text-3xl mb-5">
            Short description: <span className="italic" property='bot:hasAttractionDescription'>{attraction.attractionDescription}</span>
          </div>
          <div className="text-3xl mb-5">
            Attraction type: <span className="italic" property='bot:hasAttractionType'>{attraction.attractionType}</span>
          </div>
        </div>
      </div>
    </ReactModal>
  );
};

export default AttractionPopup;
