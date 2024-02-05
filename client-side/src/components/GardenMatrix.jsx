import React, { useEffect, useState } from 'react';
import AttractionCircle from './AttractionCircle';
import TourLine from './TourLine';

const GardenMatrix = ({ gardenInfo, cellSize }) => {
  if (!gardenInfo || !gardenInfo.attractions) return null;
  const gridSize = 6;

  const [matrix, setMatrix] = useState([]);

  useEffect(() => {
    const generateMatrix = () => {
      const newMatrix = Array.from({ length: gridSize }, () => Array(gridSize).fill(null));

      for (const attraction of gardenInfo.attractions) {
        let x, y;
        do {
          x = Math.floor(Math.random() * gridSize);
          y = Math.floor(Math.random() * gridSize);
        } while (newMatrix[x][y] !== null);

        newMatrix[x][y] = attraction;
      }

      setMatrix(newMatrix);
    };

    generateMatrix();
  }, [gardenInfo]);

  const createAttractionCircle = (i, j, outerRadius, innerRadius, attraction) => {
    const cx = (i + 0.5) * cellSize;
    const cy = (j + 0.5) * cellSize;
    const isTopMargin = j === 0;
    return (
      <AttractionCircle
        attraction={attraction}
        key={`attraction-${i}-${j}`}
        id={`attraction-${i}-${j}`}
        cx={cx}
        cy={cy}
        outerRadius={outerRadius}
        innerRadius={innerRadius}
        isTopMargin={isTopMargin}
      />
    );
  };

  const createTourLine = (id, coordinates1, coordinates2, tour) => {
    return (
      <TourLine
        id={id}
        x1={coordinates1.cx}
        y1={coordinates1.cy}
        x2={coordinates2.cx}
        y2={coordinates2.cy}
        tour={tour}
        key={`tour-line-${id}`}
      />
    );
  };

  const attractionsSVG = [];
  const coordinates = matrix.reduce((acc, row, i) => {
    row.forEach((cell, j) => {
      if (cell !== null) {
        attractionsSVG.push(createAttractionCircle(i, j, cellSize / 2.5, cellSize / 4, cell));
        acc.push({ id: cell.attractionName, cx: i * cellSize + cellSize / 2, cy: j * cellSize + cellSize / 2 });
      }
    });
    return acc;
  }, []);

  const colors = ['blue', 'yellow', 'orange', 'white', 'purple', 'brown'];

  const coloredTourList = gardenInfo.tours.map((object, index) => ({
    ...object,
    color: colors[index % colors.length],
  }));

  for (const tour of coloredTourList) {
    const attractionIds = tour.tourAttractionNames;

    for (let i = 0; i < attractionIds.length - 1; i++) {
      const attractionId1 = attractionIds[i];

      for (let j = i + 1; j < attractionIds.length; j++) {
        const attractionId2 = attractionIds[j];

        const coordinates1 = coordinates.find((item) => item.id === attractionId1);
        const coordinates2 = coordinates.find((item) => item.id === attractionId2);

        if (coordinates1 && coordinates2) {
          attractionsSVG.push(createTourLine(attractionId1 + attractionId2, coordinates1, coordinates2, tour));
        }
      }
    }
  }

  return (
    <div className="flex justify-center items-center bg-green-800 h-screen">
      <svg width={gridSize * cellSize} height={gridSize * cellSize}>
        {attractionsSVG}
      </svg>
    </div>
  );
};

export default GardenMatrix;
