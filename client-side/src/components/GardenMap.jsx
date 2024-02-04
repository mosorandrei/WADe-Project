import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import GardenMatrix from './GardenMatrix';
import ToursPopup from './ToursPopup';

const GardenMap = ({isAuthenticated, selectedGarden}) => {
  const getSelectedGardenInformation = (selectedGardenName) => {
    return {
      name: selectedGardenName,
      attractions: [
        {
          id: 1,
          photo: "fotografie1",
          name: "nume1",
          type: "type1",
          description: "descriere1",
          comments: [
            {
              user: "user1",
              commentType: "text",
              commentContent: "comentariu1",
            },
            {
              user: "user2",
              commentType: "text",
              commentContent: "comentariu2",
            }
          ],
          reviews: [
            {
              user: "user1",
              commentType: "10"
            },
            {
              user: "user2",
              rating: "8"
            }
          ]
        },
        {
          id: 2,
          photo: "fotografie2",
          name: "nume2",
          type: "type2",
          description: "descriere2",
          comments: [
            {
              user: "user1",
              commentType: "text",
              commentContent: "comentariu1",
            },
            {
              user: "user2",
              commentType: "text",
              commentContent: "comentariu2",
            }
          ],
          reviews: [
            {
              user: "user1",
              commentType: "10"
            },
            {
              user: "user2",
              rating: "8"
            }
          ]
        },
        {
          id: 3,
          photo: "fotografie3",
          name: "nume3",
          type: "type3",
          description: "descriere3",
          comments: [
            {
              user: "user1",
              commentType: "text",
              commentContent: "comentariu1",
            },
            {
              user: "user2",
              commentType: "text",
              commentContent: "comentariu2",
            }
          ],
          reviews: [
            {
              user: "user1",
              commentType: "10"
            },
            {
              user: "user2",
              rating: "8"
            }
          ]
        }
      ],
      tours: [
        {
          id: 1,
          name: "famous tour2",
          description: "descriptionTour",
          guide: "vasile",
          startHour: "10:00",
          endHour: "11:00",
          availablePlaces: 4,
          attractionIds: [1, 2],
          comments: [
            {
              user: "user1",
              commentType: "text",
              commentContent: "AMAZING",
            },
            {
              user: "user2",
              commentType: "text",
              commentContent: "STUNNING",
            }
          ],
          reviews: [
            {
              user: "user1",
              commentType: "2"
            },
            {
              user: "user2",
              rating: "2"
            }
          ]

        },
        {
          id: 1,
          name: "famous tour",
          description: "descriptionTour",
          guide: "vasile",
          startHour: "10:00",
          endHour: "11:00",
          availablePlaces: 4,
          attractionIds: [1, 2],
          comments: [
            {
              user: "user1",
              commentType: "text",
              commentContent: "AMAZING",
            },
            {
              user: "user2",
              commentType: "text",
              commentContent: "STUNNING",
            }
          ],
          reviews: [
            {
              user: "user1",
              commentType: "2"
            },
            {
              user: "user2",
              rating: "2"
            }
          ]

        }
      ]
    }
  }

  const [gardenInfo, setGardenInfo] = useState(null);
  const [cellSize, setCellSize] = useState(180);
  const [showTours, setShowTours] = useState(false);

  useEffect(() => {
    const fetchedGardenInfo = getSelectedGardenInformation(selectedGarden);
    setGardenInfo(fetchedGardenInfo);
  }, [selectedGarden]);

  useEffect(() => {
    const handleResize = () => {
      const newCellSize = (window.innerHeight * 15) / 100;
      setCellSize(newCellSize);
    };

    handleResize();
    window.addEventListener('resize', handleResize);

    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, []);

  return isAuthenticated && gardenInfo && (
    <section typeof="bot:Garden">
      <h2 className="mb-10 text-4xl">Map of <p className='italic mt-2 font-bold text-6xl' property='bot:hasGardenName'>{selectedGarden}</p>
        <div>
        <Link to="/gardens">
        <button className="mt-10 mr-4">
          <span className="text-white text-lg md:text-xl lg:text-2xl hover:underline bg-red-800 rounded-full px-8 p-3 cursor-pointer">
            Explore other gardens!
          </span>
        </button>
      </Link>
        <button className="mt-10 mr-4" onClick={()=>setShowTours(true)}>
          <span className="text-white text-lg md:text-xl lg:text-2xl hover:underline bg-red-800 rounded-full px-8 p-3 cursor-pointer">
            Check available tours!
          </span>
        </button>
        </div>
      </h2>
      <GardenMatrix gardenInfo={gardenInfo} cellSize={cellSize} />
      <ToursPopup 
          tours={gardenInfo.tours}
          showTours={showTours}
          onClose={() => setShowTours(false)}
        />
    </section>
  );
};

export default GardenMap;