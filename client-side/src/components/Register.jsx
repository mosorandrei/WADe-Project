import React from 'react';
import { Link } from 'react-router-dom';

const Register = ({ isAuthenticated, setAuthenticated }) => {

  const registerUser = () => {
    if (!isAuthenticated) {
      setAuthenticated(true);
    }
  }

  return (
    <div className="max-w-sm mx-auto bg-white p-8 rounded shadow-md" vocab="http://schema.org/" typeof="Person">
      <h2 property="name" className="text-2xl font-bold mb-4">Register</h2>
      <form className="flex flex-col">
        <div className="mb-4">
          <label htmlFor="firstName" className="block text-gray-700 font-bold mb-2">First Name</label>
          <input type="text" id="firstName" className="w-full border-2 border-gray-300 p-2 rounded" property="firstName" />
        </div>
        <div className="mb-4">
          <label htmlFor="lastName" className="block text-gray-700 font-bold mb-2">Last Name</label>
          <input type="text" id="lastName" className="w-full border-2 border-gray-300 p-2 rounded" property="lastName" />
        </div>
        <div className="mb-4">
          <label htmlFor="email" className="block text-gray-700 font-bold mb-2">Email</label>
          <input type="email" id="email" className="w-full border-2 border-gray-300 p-2 rounded" property="email" />
        </div>
        <div className="mb-4">
          <label htmlFor="password" className="block text-gray-700 font-bold mb-2">Password</label>
          <input type="password" id="password" className="w-full border-2 border-gray-300 p-2 rounded" property="password" />
        </div>
        <div className="mb-4">
          <label htmlFor="confirmPassword" className="block text-gray-700 font-bold mb-2">Confirm Password</label>
          <input type="password" id="confirmPassword" className="w-full border-2 border-gray-300 p-2 rounded" property="password" />
        </div>
        <div className="mb-4">
          <label className="flex items-center">
            <input type="checkbox" className="mr-2" />
            <span className="text-gray-700" property="termsAndConditions">I accept the terms and conditions</span>
          </label>
        </div>
        <div className="flex justify-between items-center mb-4">
          <Link
            to="/"
            className="bg-green-500 text-white py-2 px-4 rounded-lg hover:bg-green-600 focus:outline-none focus:ring focus:border-green-400 mb-2"
            onClick={registerUser}
          >
            Register Now
          </Link>
          <button
            className="bg-blue-500 text-white py-2 px-4 rounded-lg hover:bg-blue-600 focus:outline-none focus:ring focus:border-blue-400 mb-2"
            onClick={registerUser}
          >
            Register with Google
          </button>
        </div>
      </form>
    </div>
  );
};

export default Register;
