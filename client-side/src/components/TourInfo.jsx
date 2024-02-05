import React, {useState, useEffect} from 'react';
import ReactModal from 'react-modal';
import Cookies from 'js-cookie';
import { COMMENTS_ENDPOINT, REVIEWS_ENDPOINT, TOURS_ENDPOINT } from '../constants';
const customStyles = {
  content: {
    width: '40%',
    height: '85%',
    margin: 'auto',
  },
};


const TourInfo = ({ shownTour, onClose, tour }) => {
  console.log(tour);
  const [commentInput, setCommentInput] = useState('');
  const [reviewInput, setReviewInput] = useState(5);
  const firstName = Cookies.get('loggedInFirstName');
  const lastName = Cookies.get('loggedInLastName');

  const [tourComments, setTourComments] = useState(tour.tourComments || []);
  const [addedComment, setAddedComment] = useState(null);
  const [addedReview, setAddedReview] = useState(null);
  const [addedParticipant, setAddedParticipant] = useState(null);
  const [canBook, setCanBook] = useState(!tour.tourParticipants.some(participant =>
    participant.firstName === firstName && participant.lastName === lastName));

  const handleBookReservation = async () => {
    try {
      const response = await fetch(TOURS_ENDPOINT, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          firstName: firstName, 
          lastName: lastName,
          tourName: tour.tourName,
        }),
      });
      setAddedParticipant(prevParticipants => (
        <>
          {prevParticipants}
          <div className="text-xl" typeof="bot:hasParticipant">
            <div property="bot:User">
            Participant: <span property="bot:hasFirstName" className="font-bold mr-1">{firstName}</span>
              <span property="bot:hasLastName" className="font-bold mr-2">{lastName}</span>
            </div>
          </div>
        </>
      ))
      setCanBook(!canBook);
      if (!response.ok) {
        throw new Error('Failed to add comment');
      }
    } catch (error) {
      console.error('Error adding comment:', error);
    }
  };

  const handleAddComment = async () => {
    if(!commentInput) return;
    try {
      const response = await fetch(COMMENTS_ENDPOINT, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          firstName: firstName, 
          lastName: lastName,
          tourName: tour.tourName,
          content: commentInput,
        }),
      });
      setAddedComment(prevComments => (
        <>
          {prevComments}
          <div className="text-xl" typeof="bot:hasComment">
            <div property="bot:User">
              <span property="bot:hasFirstName" className="font-bold mr-1">{firstName}</span>
              <span property="bot:hasLastName" className="font-bold mr-2">{lastName}</span>
              has commented: <span typeof="bot:Comment" property="bot:hasCommentContent">{commentInput}</span>
            </div>
          </div>
        </>
      ))
      if (!response.ok) {
        throw new Error('Failed to add comment');
      }
      setCommentInput('');
    } catch (error) {
      console.error('Error adding comment:', error);
    }
  };

  const handleAddReview = async () => {
    try {
      const response = await fetch(REVIEWS_ENDPOINT, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          firstName: firstName,
          lastName: lastName,
          tourName: tour.tourName,
          score: parseInt(reviewInput, 10),
        }),
      });
      setAddedReview(prevReviews => (
        <>
          {prevReviews}
          <div className="text-xl" typeof='bot:hasReview'>
                <div property='bot:User'>
                <span property='bot:hasLastName'className="font-bold mr-2">{firstName} {lastName} </span>   
                has commented: <span typeof='bot:Review' property='bot:hasReviewContent'>{reviewInput}/10</span>
                </div>
              </div>
        </>
      ));
      if (!response.ok) {
        throw new Error('Failed to add review');
      }
      setReviewInput(5);
    } catch (error) {
      console.error('Error adding review:', error);
    }
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
            Name: <span className="italic" property='bot:hasTourName'>{tour.tourName}</span>
          </div>
          <div className="text-3xl mb-5">
            Short description: <span className="italic" property='bot:hasTourDescription'>{tour.tourDescription}</span>
          </div>
          <div className="text-3xl mb-5">
            Tour Guide: <span className="italic" property='bot:hasTourGuideName'>{tour.tourGuideName}</span>
          </div>
          <div className="text-3xl mb-5 text-red-500">
            Remaining places: <span className="italic" property='bot:hasTourTotalSeats'>{tour.tourTotalSeats.split("^^")[0]}</span>
          </div>
          <div className="text-3xl mb-5">
            Duration: <span className="italic"><span property='bot:hasTourStartHour'>{tour.tourStartHour.split("^^")[0]}:00</span>-<span property='bot:hasTourEndHour'>{tour.tourEndHour.split("^^")[0]}:00</span></span>
          </div>
          <div className="mb-5">
            <div className="text-2xl mb-2 underline">Comments:</div>
            {tourComments.map((comment, index) => (
              <div key={`comment-${index}`} className="text-xl" typeof='bot:hasComment'>
                <div property='bot:User'>
                <span property='bot:hasFirstName'className="font-bold mr-1">{comment.commentAuthor.split(" ")[0]}</span>

                <span property='bot:hasLastName'className="font-bold mr-2">{comment.commentAuthor.split(" ")[1]}</span>   
                has commented: <span typeof='bot:Comment' property='bot:hasCommentContent'>{comment.commentContent}</span>
                </div>
              </div>
            ))}
            {addedComment}
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
            {tour.tourReviews.map((review, index) => (
              <div key={`review-${index}`} className="text-xl" typeof='bot:hasReview'>
                <div property='bot:User'>
                <span property='bot:hasFirstName'className="font-bold mr-1">{review.reviewAuthor.split(" ")[0]}</span>

                <span property='bot:hasLastName'className="font-bold mr-2">{review.reviewAuthor.split(" ")[1]}</span>  
                has reviewed: <span typeof='bot:Review' property='bot:hasReviewContent'>{review.reviewContent.split("^^")[0]}/10</span>
                </div>
              </div>
            ))}
            {addedReview}
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
          {tour.tourAttractionNames.map((name, index) => (
              <div key={`attr-${index}`} className="text-xl mb-2" typeof='bot:hasAttraction'>
                <div property='bot:Attraction'>
                <span property='bot:hasAttractionName'>Available attraction: <span className='font-bold'>{name}</span></span>
                </div>
              </div>
            ))}
            {tour.tourParticipants.map((name, index) => (
              <div key={`participant-${index}`} className="text-xl mb-2">
                <div property='bot:User'>
                <span property='bot:hasParticipant'>Participant: <span className='font-bold'>{name.firstName} {name.lastName}</span></span>
                </div>
              </div>
            ))}
            {addedParticipant}
            {canBook && (<button
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" onClick={handleBookReservation}
              >
                Book Reservation!
              </button>)}
        </div>
      </div>
    </ReactModal>
  );
};

export default TourInfo;