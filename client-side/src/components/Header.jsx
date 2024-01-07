import React from 'react';
import { Link } from 'react-router-dom';

const Header = ({ isAuthenticated }) => {
  return (
    <header className="bg-green-500 p-4 fixed left-0 top-0 w-full">
      <nav className="flex justify-between items-center">
        <div>
          <h1 className="text-white text-xl md:text-2xl lg:text-3xl font-bold italic">Botanical Garden Guide</h1>
        </div>
        <ul className="flex flex-wrap md:space-x-24">
          <li><Link to="/" className="text-white text-lg md:text-xl lg:text-2xl hover:underline">Home</Link></li>
          {isAuthenticated ? (
            <>
              <li><p className="text-white text-lg md:text-xl lg:text-2xl hover:underline">Hello John</p></li>
              <li><Link to="/logout" className="text-white text-lg md:text-xl lg:text-2xl hover:underline">Logout</Link></li>
            </>
          ) : (
            <>
              <li className="mr-4"><Link to="/login" className="text-white text-lg md:text-xl lg:text-2xl hover:underline bg-red-800 rounded-full p-3">Log In</Link></li>
              <li><Link to="/register" className="text-white text-lg md:text-xl lg:text-2xl hover:underline bg-red-800 rounded-full p-3">Register</Link></li>
            </>
          )}
        </ul>
      </nav>
    </header>
  );
};

export default Header;
