import React from 'react';

const Home = ({isAuthenticated, setCurrentPage}) => {
  function changePage() {
    if(isAuthenticated) {
      setCurrentPage("GardensDropdown");
    } else setCurrentPage("Login");
  }
  return (
    <div>
      <section className="bg-gray-100 p-8">
        <div className="max-w-screen-xl mx-auto flex justify-between">
          <div className="w-1/2 mr-4">
            <h2 className="text-4xl font-bold mb-8">Welcome to Botanical Garden Guide!</h2>
            <button className="bg-green-500 text-white ml-4 py-2 px-4 rounded-full text-lg md:text-xl lg:text-2xl hover:bg-green-600 focus:outline-none focus:ring focus:border-green-400" onClick={() => changePage()}>
                Dive in!
            </button>
          </div>
          <div className="w-1/2 ml-4">
            <h2 className="text-4xl font-bold">Join us in a marvelous adventure where you can explore and find out about 10,000+ species from the botanic field!</h2>
          </div>
        </div>
      </section>
      <section className="bg-white p-8">
        <div className="max-w-screen-xl mx-auto">
          <h2 className="text-3xl font-bold mb-8">About Us</h2>
          <p className='text-xl mb-8 text-justify'>
            Welcome to the Botanical Garden Guide, your digital passport to the captivating world of plants. Our mission is to inspire and educate, providing a platform where enthusiasts and curious minds alike can delve into the rich tapestry of botanical wonders.
          </p>
          <p className='text-xl mb-8 text-justify'>
            Immerse yourself in the diversity of plant life, from the intricate details of each leaf to the broader ecological significance of entire ecosystems. Join us on a journey that goes beyond the surface, unlocking the secrets of 10,000+ plant species through insightful tours and engaging content.
          </p>
          <p className='text-xl mb-8 text-justify'>
            At Botanical Garden Guide, we believe that understanding and appreciating the botanical realm is key to fostering a deeper connection with nature. Let your curiosity bloom as you explore the pages of our guide, and embark on an adventure where knowledge and discovery intertwine.
          </p>
        </div>
      </section>
      <section className="bg-gray-100 p-8">
      <h2 className="text-3xl font-bold mb-16">Contact</h2>
        <div className="max-w-screen-xl mx-auto flex justify-around">
          <div className="w-1/4">
            <img src="" alt="Smau" className="rounded mb-4" />
            <p className="font-bold text-xl mb-2">Smau Adrian</p>
            <p>Email: adriansmau@yahoo.com</p>
            <p>GitHub: <a href="https://github.com/AdrianSmau">github.com/AdrianSmau</a></p>
            <p>Linkedin: <a href="https://www.linkedin.com/in/smauadrianconstantin/">Smau Adrian-Constantin</a></p>
          </div>
          <div className="w-1/4">
            <img src='' alt="Mosor" className="rounded mb-4" />
            <p className="font-bold text-xl mb-2">Mosor Andrei</p>
            <p>Email: mosorandrei49@gmail.com</p>
            <p>GitHub: <a href="https://github.com/mosorandrei">github.com/mosorandrei</a></p>
            <p>Linkedin: <a href="https://www.linkedin.com/in/andrei-mosor-27961b202/">Andrei Mosor</a></p>
          </div>
        </div>
      </section>
    </div>
  );
};

export default Home;
