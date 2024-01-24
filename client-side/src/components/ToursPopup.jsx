import React, { useState } from 'react';
import ReactModal from 'react-modal';

const customStyles = {
  content: {
    width: '40%',
    height: '70%',
    margin: 'auto',
  },
};

const ToursPopup = ({ gardenInfo, showTours, onClose }) => {
    return (
    <ReactModal
      isOpen={showTours}
      onRequestClose={onClose}
      contentLabel="Tours"
      style={customStyles}
    >
      //to do tours booking
    </ReactModal>
  );
};

export default ToursPopup;
