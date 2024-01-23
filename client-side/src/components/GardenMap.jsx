import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

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

  const renderMatrix = () => {
    if (!gardenInfo) return null;

    const gridSize = 6;

    const createAttractionCircle = (key, i, j, outerRadius, innerRadius) => {
      const cx = (i + 0.5) * cellSize;
      const cy = (j + 0.5) * cellSize;
      return (
        <g key={key}>
          <circle cx={cx} cy={cy} r={outerRadius} fill="red" className="attraction-outer" />
          <circle cx={cx} cy={cy} r={innerRadius} fill="#8B0000" className="attraction-inner" />
        </g>
      );
    };

    const createRoad = (key, i, j) => {
      const x = i * cellSize + cellSize / 4;
      const y = j * cellSize + cellSize / 4;
      const width = cellSize / 2;
      const height = cellSize / 2;
      return (
        <rect key={key} x={x} y={y} width={width} height={height} fill="#D3D3D3" className="road" />
      );
    };

    const matrix = Array.from({ length: gridSize }, () => Array(gridSize).fill(null));

    for (const attraction of gardenInfo.attractions) {
      let x, y;
      do {
        x = Math.floor(Math.random() * gridSize);
        y = Math.floor(Math.random() * gridSize);
      } while (matrix[x][y] !== null);

      matrix[x][y] = attraction;
    }

    const attractionsSVG = [];
    for (let i = 0; i < gridSize; i++) {
      for (let j = 0; j < gridSize; j++) {
        const cell = matrix[i][j];
        if (cell === null) {
          attractionsSVG.push(createRoad(`road-${i}-${j}`, i, j));
        } else {
          attractionsSVG.push(createAttractionCircle(`attraction-${cell.id}`, i, j, cellSize / 2.5, cellSize / 4));
        }
      }
    }

    const toursSVG = [];
    for (const tour of gardenInfo.tours) {
      const tourPath = tour.attractionIds.map((attractionId) => {
        const attraction = gardenInfo.attractions.find((a) => a.id === attractionId);
        return {
          x: (attraction.x + 0.5) * cellSize,
          y: (attraction.y + 0.5) * cellSize,
        };
      });

      toursSVG.push(
        <polyline
          key={`tour-${tour.id}`}
          points={tourPath.map(point => `${point.x},${point.y}`).join(' ')}
          fill="none"
          stroke={`#${((Math.random() * 0xffffff) << 0).toString(16).padStart(6, '0')}`} // Random color
          strokeWidth="5"
        />
      );
    }

    return (
      <div className="flex justify-center items-center bg-green-800 h-screen">
      <svg width={gridSize * cellSize} height={gridSize * cellSize}>
        {attractionsSVG}
        {toursSVG}
      </svg>
    </div>
    );
  };

  return isAuthenticated && gardenInfo && (
    <section>
      <h2 className="mb-10 text-4xl">Map of <p className='italic mt-2 font-bold text-6xl'>{selectedGarden}</p> </h2>
      {renderMatrix()}
      <Link to="/gardens">
        <button className="mt-10 mr-4">
          <span className="text-white text-lg md:text-xl lg:text-2xl hover:underline bg-red-800 rounded-full px-8 p-3 cursor-pointer">
            Explore other gardens
          </span>
        </button>
      </Link>
    </section>
  );
};

export default GardenMap;