import React, {useState} from 'react';
import ReactModal from 'react-modal';
const customStyles = {
  content: {
    width: '40%',
    height: '85%',
    margin: 'auto',
  },
};


const TourInfo = ({ shownTour, onClose, tour }) => {
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
      isOpen={shownTour}
      onRequestClose={onClose}
      contentLabel="Tour Individual Details"
      style={customStyles}
      typeof="bot:Tour"
    >
      <div className="border-2 border-white h-full relative">
        <button
          onClick={onClose}
          className="absolute top-2 right-2 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
        >
          Close
        </button>
        <div className="flex justify-end p-5"></div>
        <div className="p-10 overflow-auto">
          <div className="text-6xl mb-5">
            Name: <span className="italic" property='bot:hasTourName'>{tour.name}</span>
          </div>
          <div className="text-3xl mb-5">
            Short description: <span className="italic" property='bot:hasTourDescription'>{tour.description}</span>
          </div>
          <div className="text-3xl mb-5">
            Tour Guide: <span className="italic" property='bot:hasTourGuideName'>{tour.guide}</span>
          </div>
          <div className="text-3xl mb-5 text-red-500">
            Remaining places: <span className="italic" property='bot:hasTourTotalSeats'>{tour.availablePlaces}</span>
          </div>
          <div className="text-3xl mb-5">
            Duration: <span className="italic"><span property='bot:hasTourStartHour'>{tour.startHour}</span>-<span property='bot:hasTourEndHour'>{tour.endHour}</span></span>
          </div>
          <div className="mb-5">
            <div className="text-2xl mb-2 underline">Comments:</div>
            {tour.comments.map((comment, index) => (
              <div key={`comment-${index}`} className="text-xl" typeof='bot:hasComment'>
                <div property='bot:User'>
                <span property='bot:hasFirstName'className="font-bold">{comment.user}</span>

                <span property='bot:hasLastName'className="font-bold">{comment.user}</span>   
                has commented: <span typeof='bot:Comment' property='bot:hasCommentContent'>{comment.commentContent}</span>
                </div>
              </div>
            ))}
            <div className="flex items-center mt-2">
              <input
                type="text"
                value={commentInput}
                onChange={(e) => setCommentInput(e.target.value)}
                placeholder="Add a comment..."
                className="mr-2 px-2 py-1 border border-gray-300 rounded"
              />
              <button
                onClick={handleAddComment}
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
              >
                Add Comment
              </button>
            </div>
          </div>
          <div className="mb-5">
            <div className="text-2xl mb-2 underline">Reviews:</div>
            {tour.reviews.map((review, index) => (
              <div key={`review-${index}`} className="text-xl" typeof='bot:hasReview'>
                <div property='bot:User'>
                <span property='bot:hasFirstName'className="font-bold">{review.user}</span>

                <span property='bot:hasLastName'className="font-bold">{review.user}</span>   
                has commented: <span typeof='bot:Review' property='bot:hasReviewContent'>{review.rating}</span>
                </div>
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
              />
              <button
                onClick={handleAddReview}
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
              >
                Add Review
              </button>
            </div>
          </div>
          <button
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
              >
                Book Reservation!
              </button>
        </div>
      </div>
    </ReactModal>
  );
};

export default TourInfo;