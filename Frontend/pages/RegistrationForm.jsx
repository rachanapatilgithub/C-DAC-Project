import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const RegistrationForm = () => {
  const [selectedRole, setSelectedRole] = useState(null);
  const [formData, setFormData] = useState({
    fullName: '',
    email: '',
    password: '',
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const dataToSubmit = {
      ...formData,
      role: selectedRole,
    };
    console.log('Form Data:', dataToSubmit);
    alert(`${selectedRole} Registered Successfully!`);

    if (selectedRole === 'Admin') {
      navigate('/admin-dashboard');
    }
  };

  return (
    <div className="d-flex justify-content-center align-items-center min-vh-100 bg-light">
      <div className="card shadow p-4" style={{ width: '100%', maxWidth: '500px' }}>
        <h2 className="mb-4 text-center">Register</h2>

        {/* Role Selection Buttons */}
        {!selectedRole && (
          <div className="text-center">
            <button
              type="button"
              className="btn btn-outline-primary me-2"
              onClick={() => setSelectedRole('Admin')}
            >
              Admin Register
            </button>
            <button
              type="button"
              className="btn btn-outline-primary me-2"
              onClick={() => setSelectedRole('Expert')}
            >
              Expert Register
            </button>
            <button
              type="button"
              className="btn btn-outline-primary"
              onClick={() => setSelectedRole('Farmer')}
            >
              Farmer Register
            </button>
          </div>
        )}

        {/* Form appears only after selecting role */}
        {selectedRole && (
          <>
            <p className="text-center text-muted">
              Role: <strong>{selectedRole}</strong>
            </p>
            <form onSubmit={handleSubmit}>

              {/* ✅ Show full name only if not Admin */}
              {selectedRole !== 'Admin' && (
                <div className="mb-3">
                  <label htmlFor="fullName" className="form-label">Full Name</label>
                  <input
                    type="text"
                    className="form-control"
                    id="fullName"
                    name="fullName"
                    value={formData.fullName}
                    onChange={handleChange}
                    required
                  />
                </div>
              )}

              <div className="mb-3">
                <label htmlFor="email" className="form-label">Email address</label>
                <input
                  type="email"
                  className="form-control"
                  id="email"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="mb-4">
                <label htmlFor="password" className="form-label">Password</label>
                <input
                  type="password"
                  className="form-control"
                  id="password"
                  name="password"
                  value={formData.password}
                  onChange={handleChange}
                  required
                />
              </div>

              <button type="submit" className="btn btn-primary w-100">
                Register as {selectedRole}
              </button>
            </form>
          </>
        )}
      </div>
    </div>
  );
};

export default RegistrationForm;
