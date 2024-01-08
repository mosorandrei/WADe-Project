import React from 'react';

const Header = ({ isAuthenticated, setAuthenticated, setCurrentPage }) => {
  return (
    <header className="bg-green-500 p-4 fixed left-0 top-0 w-full">
      <nav className="flex justify-between items-center">
        <div>
          <h1 className="text-white text-xl md:text-2xl lg:text-3xl font-bold italic">Botanical Garden Guide</h1>
        </div>
        <ul className="flex flex-wrap md:space-x-24">
          <li><span className="text-white text-lg md:text-xl lg:text-2xl hover:underline cursor-pointer" onClick={() => setCurrentPage('Home')}>Home</span></li>
          {isAuthenticated ? (
            <>
              <li><p className="text-white text-lg md:text-xl lg:text-2xl hover:underline">Hello John</p></li>
              <li><span className="text-white text-lg md:text-xl lg:text-2xl hover:underline cursor-pointer" onClick={() => {setCurrentPage('Home'); setAuthenticated(false);}}>Logout</span></li>
            </>
          ) : (
            <>
              <li className="mr-4"><span className="text-white text-lg md:text-xl lg:text-2xl hover:underline bg-red-800 rounded-full px-8 p-3 cursor-pointer" onClick={() => setCurrentPage('Login')}>Log In</span></li>
              <li><span className="text-white text-lg md:text-xl lg:text-2xl hover:underline bg-red-800 rounded-full px-8 p-3 cursor-pointer" onClick={() => setCurrentPage('Register')}>Register</span></li>
            </>
          )}
        </ul>
      </nav>
    </header>
  );
};

export default Header;
