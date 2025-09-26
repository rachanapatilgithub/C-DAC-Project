import React from 'react';
import { useNavigate } from 'react-router-dom';


const Home = () => {
  const navigate = useNavigate();

  return (
    <div
      style={{
         backgroundImage: `url('/backG.jpg')`, // ✅ Image in public folder
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        backgroundRepeat: 'no-repeat',
        color: 'white',
        height: '100vh',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        padding: '20px',
        textAlign: 'center',
      }}
    >
      <div
        style={{
          backgroundColor: 'rgba(0, 0, 0, 0.6)',
          padding: '30px',
          borderRadius: '12px',
          maxWidth: '800px',
        }}
      >
        <h1 className="fw-bold mb-4">Empowering Farmers with Smart Farming</h1>
        <p className="lead mb-4">
          Leverage cutting-edge technology and expert insights to optimize your
          agricultural practices and yield.
        </p>
        <div className="d-flex justify-content-center gap-3 flex-wrap">
          <button className="btn btn-success px-4" onClick={() => navigate('/register')}>
            Get Started
          </button>
          <button className="btn btn-outline-light px-4" onClick={() => navigate('/knowledge-base')}>
            Explore Knowledge
          </button>
        </div>
      </div>
    </div>
  );
};

export default Home;
