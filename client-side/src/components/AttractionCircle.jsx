import React, { useState } from 'react';
import AttractionPopup from './AttractionPopup';

const AttractionCircle = ({ attraction, cx, cy, outerRadius, innerRadius, isTopMargin  }) => {
    const [isHovered, setIsHovered] = useState(false);
    const [detailsShown, setDetailsShown] = useState(false);

    const multiplier = isTopMargin ? -1 : 1;
    return (
      <g
        key={`attraction-${attraction.id}`}
      >
        <circle cx={cx} cy={cy} r={outerRadius} fill="red" className="attraction-outer"  />
        <circle cx={cx} cy={cy} r={innerRadius} fill="#8B0000" className="attraction-inner" onMouseEnter={() => setIsHovered(true)}
        onMouseLeave={() => setIsHovered(false)}
        onClick={()=> setDetailsShown(true)} />

        {isHovered && (
          <text x={cx} y={cy - multiplier * 2 * innerRadius + 10} textAnchor="middle" fill="white" fontSize="17">
            Check this attraction!
          </text>
        )}
        <AttractionPopup
          attraction={attraction}
          detailsShown={detailsShown}
          onClose={() => setDetailsShown(false)}
        />
      </g>
    );
  };

export default AttractionCircle;