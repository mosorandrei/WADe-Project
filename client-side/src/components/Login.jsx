import React, {useState} from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { LOGIN_ENDPOINT } from '../constants';
import Cookies from 'js-cookie';
const Login = ({ isAuthenticated, setAuthenticated }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [possibleErrors, setPossibleErrors] = useState(null);

  const navigate = useNavigate();

  const loginUser = async () => {
    if (!isAuthenticated) {
      try {
        const response = await fetch(LOGIN_ENDPOINT, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ email:email, password: password }),
        });
        const responseData = await response.json();
        if (response.ok) {
          setAuthenticated(true);
          console.log(response.firstName);
          Cookies.set('loggedInFirstName', responseData.firstName, { expires: Infinity });
          Cookies.set('loggedInLastName', responseData.lastName, { expires: Infinity });
          navigate('/');
        } else {
          setPossibleErrors(responseData.errorMessage);
        }
      } catch (error) {
        console.error('Error during login:', error);
      }
    }
  };
  return !isAuthenticated && (
    <div className="max-w-sm mx-auto bg-white p-8 rounded shadow-md" vocab="http://schema.org/" typeof="Person">
      <h2 property="name" className="text-2xl font-bold mb-4">Login</h2>
      <form className="flex flex-col">
        <div className="mb-4">
          <label htmlFor="email" className="block text-gray-700 font-bold mb-2">Email</label>
          <input type="email" id="email" className="w-full border-2 border-gray-300 p-2 rounded" property="email" 
          value={email} onChange={(e) => setEmail(e.target.value)} />
        </div>
        <div className="mb-4">
          <label htmlFor="password" className="block text-gray-700 font-bold mb-2">Password</label>
          <input type="password" id="password" className="w-full border-2 border-gray-300 p-2 rounded" property="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)} />
          {possibleErrors && (<div className="mb-4 mt-4 text-red-600 font-bold underline underline-offset-4">
          <p>{possibleErrors}</p>
        </div>)}
        </div>
        <Link
          to="#"
          className="bg-green-500 text-white mx-auto py-2 px-4 rounded-lg hover:bg-green-600 focus:outline-none focus:ring focus:border-green-400 mb-2"
          onClick={loginUser}
        >
          Sign In
        </Link>
      </form>
    </div>
  );
};
export default Login;