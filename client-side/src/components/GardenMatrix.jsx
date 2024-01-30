import React from 'react';
import AttractionCircle from './AttractionCircle';
import TourLine from './TourLine';

const GardenMatrix = ({ gardenInfo, cellSize }) => {
    if (!gardenInfo) return null;
    const gridSize = 6;

    const createAttractionCircle = (i, j, outerRadius, innerRadius, attraction) => {
        const cx = (i + 0.5) * cellSize;
        const cy = (j + 0.5) * cellSize;
        const isTopMargin = j == 0;
        return (
            <AttractionCircle
                attraction={attraction}
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
            tour={tour} /> 
        )
    }

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
    const coordinates = [];
    for (let i = 0; i < gridSize; i++) {
        for (let j = 0; j < gridSize; j++) {
            const cell = matrix[i][j];
            if (cell !== null) {
                attractionsSVG.push(createAttractionCircle(i, j, cellSize / 2.5, cellSize / 4, cell));
                coordinates.push({ id: cell.id, cx: i * cellSize + cellSize/2,  cy: j * cellSize + cellSize/2 });
            }
        }
    }

    const colors = ['blue', 'yellow', 'orange', "white", "purple", "brown"];

    const coloredTourList = gardenInfo.tours.map((object, index) => ({
        ...object,
        color: colors[index % colors.length],
    }));
    for (const tour of coloredTourList) {
        for (const attractionId1 of tour.attractionIds) {
            for (const attractionId2 of tour.attractionIds) {
                if (attractionId1 !== attractionId2) {
                    const coordinates1 = coordinates.find(item => item.id == attractionId1);
                    const coordinates2 = coordinates.find(item => item.id == attractionId2);
                    attractionsSVG.push(createTourLine(attractionId1+attractionId2, coordinates1, coordinates2, tour));
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