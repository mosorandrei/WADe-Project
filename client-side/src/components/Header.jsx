import React from 'react';
import { Link } from 'react-router-dom';
import Cookies from 'js-cookie';

const Header = ({ isAuthenticated, setAuthenticated }) => {
  const logout = () => {
    Cookies.remove('loggedInUserName');
    setAuthenticated(false);
  }

  const loggedInUserName = Cookies.get('loggedInUserName');
  return (
    <header className="bg-green-500 p-4 fixed left-0 top-0 w-full" vocab="http://schema.org/" typeof="WebSite">
      <nav className="flex justify-between items-center">
        <div>
          <h1 property="name" className="text-white text-xl md:text-2xl lg:text-3xl font-bold italic">Botanical Garden Guide</h1>
        </div>
        <ul className="flex flex-wrap md:space-x-24">
        <li><Link to="/" className="text-white text-lg md:text-xl lg:text-2xl hover:underline cursor-pointer">Home</Link></li>
          {isAuthenticated ? (
            <>
              <li><p className="text-white text-lg md:text-xl lg:text-2xl">Hello {loggedInUserName}!</p></li>
              <li><Link to="/" className="text-white text-lg md:text-xl lg:text-2xl hover:underline cursor-pointer"  onClick={logout}>Logout</Link></li>
            </>
          ) : (
            <>
              <li className="mr-4"><Link to="/login" className="text-white text-lg md:text-xl lg:text-2xl hover:underline bg-red-800 rounded-full px-8 p-3 cursor-pointer">Log In</Link></li>
              <li><Link to="/register" className="text-white text-lg md:text-xl lg:text-2xl hover:underline bg-red-800 rounded-full px-8 p-3 cursor-pointer">Register</Link></li>
            </>
          )}
        </ul>
      </nav>
    </header>
  );
};

export default Header;
