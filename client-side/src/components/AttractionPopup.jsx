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
  const [commentInput, setCommentInput] = useState('');
  const [reviewInput, setReviewInput] = useState(5);

  const handleAddComment = () => {
    console.log('Adding comment:', commentInput);
    setCommentInput('');
  };

  const handleAddReview = () => {
    console.log('Adding review:', reviewInput);
  };

  return (
    <ReactModal
      isOpen={detailsShown}
      onRequestClose={onClose}
      contentLabel="Attraction Details"
      style={customStyles}
      vocab="http://schema.org/"
    >
      <div className="attraction-popup border-2 border-white h-full relative">
        <button
          onClick={onClose}
          property="closeButton"
          className="absolute top-2 right-2 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
        >
          Close
        </button>
        <div className="flex justify-end p-5"></div>
        <div className="p-10 overflow-auto">
          <div className="text-6xl mb-5" property="name">
            Name: <span className="italic">{attraction.name}</span>
          </div>
          <div className="text-3xl mb-5" property="description">
            Short description: <span className="italic">{attraction.description}</span>
          </div>
          <img src="" alt="Attraction" className="rounded mb-4" property="attractionImage" />
          <div className="text-2xl mt-5" property="findOutMore">Find out more at: <span property="attractionURL" href="URL">URL</span></div>
        </div>
      </div>
    </ReactModal>
  );
};

export default AttractionPopup;
