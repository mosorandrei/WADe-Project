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
          <div className="mb-5">
            <div className="text-2xl mb-2 underline" property="commentsHeader">Comments:</div>
            {attraction.comments.map((comment, index) => (
              <div key={`comment-${index}`} className="text-xl">
                <span className="font-bold" property="commentUser">{comment.user}</span> has commented: <span property="commentContent">{comment.commentContent}</span>
              </div>
            ))}
            <div className="flex items-center mt-2">
              <input
                type="text"
                value={commentInput}
                onChange={(e) => setCommentInput(e.target.value)}
                placeholder="Add a comment..."
                className="mr-2 px-2 py-1 border border-gray-300 rounded"
                property="commentInput"
              />
              <button
                onClick={handleAddComment}
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                property="addCommentButton"
              >
                Add Comment
              </button>
            </div>
          </div>
          <div className="mb-5">
            <div className="text-2xl mb-2 underline" property="reviewsHeader">Reviews:</div>
            {attraction.reviews.map((review, index) => (
              <div key={`review-${index}`} className="text-xl">
                <span className="font-bold" property="reviewUser">{review.user}</span> has rated: <span property="reviewRating">{review.rating}</span>
              </div>
            ))}
            <div className="flex items-center mt-2">
              <input
                type="number"
                value={reviewInput}
                onChange={(e) => setReviewInput(parseInt(e.target.value))}
                min="1"
                max="10"
                placeholder="Rating (1-10)"
                className="mr-2 px-2 py-1 border border-gray-300 rounded"
                property="reviewInput"
              />
              <button
                onClick={handleAddReview}
                property="addReviewButton"
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
              >
                Add Review
              </button>
            </div>
          </div>
          <img src="" alt="Attraction" className="rounded mb-4" property="attractionImage" />
          <div className="text-2xl mt-5" property="findOutMore">Find out more at: <span property="attractionURL" href="URL">URL</span></div>
        </div>
      </div>
    </ReactModal>
  );
};

export default AttractionPopup;
