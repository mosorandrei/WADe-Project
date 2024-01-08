import React from 'react';

const Login = ({isAuthenticated, setAuthenticated, setCurrentPage}) => {
  const loginUser = () => {
    if(!isAuthenticated) {
      setAuthenticated(true);
      setCurrentPage("GardensDropdown");
    }
  }
  
  return (
    <div className="max-w-sm mx-auto bg-white p-8 rounded shadow-md">
      <h2 className="text-2xl font-bold mb-4">Login</h2>
      <form className="flex flex-col">
        <div className="mb-4">
          <label htmlFor="email" className="block text-gray-700 font-bold mb-2">Email</label>
          <input type="email" id="email" className="w-full border-2 border-gray-300 p-2 rounded" />
        </div>
        <div className="mb-4">
          <label htmlFor="password" className="block text-gray-700 font-bold mb-2">Password</label>
          <input type="password" id="password" className="w-full border-2 border-gray-300 p-2 rounded" />
        </div>
        <button className="bg-green-500 text-white py-2 px-4 rounded-lg hover:bg-green-600 focus:outline-none focus:ring focus:border-green-400 mb-2" onClick={loginUser}>
          Sign In
        </button>
        <button className="bg-blue-500 text-white py-2 px-4 rounded-lg hover:bg-blue-600 focus:outline-none focus:ring focus:border-blue-400 mb-2" onClick={loginUser}>
          Sign in using Google
        </button>
      </form>
    </div>
  );
};

export default Login;
