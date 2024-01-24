import React, { useState } from 'react';
import TourInfo from './TourInfo';

const TourLine = ({ x1, y1, x2, y2, tour }) => {
    const [shownTour, setShownTour] = useState(false);
    return (<svg className="absolute inset-0">
        <line
            x1={x1}
            y1={y1}
            x2={x2}
            y2={y2}
            stroke={tour.color}
            strokeWidth="13"
            onClick={() => setShownTour(true)}
        />
        <TourInfo
            shownTour={shownTour}
            onClose={() => setShownTour(false)}
            tour={tour}
        />
    </svg>);
};

export default TourLine;