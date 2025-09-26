import React, { useState } from 'react';

const AdminDashboard = () => {
  const [activeSection, setActiveSection] = useState(null);

  const [expertForm, setExpertForm] = useState({
    name: '',
    email: '',
    contact: '',
    qualification: '',
    specialization: '',
    location: '',
    availability: '',
    password: '',
  });

  const [farmerForm, setFarmerForm] = useState({
    name: '',
    email: '',
    contact: '',
    location: '',
    password: '',
  });

  const handleExpertChange = (e) => {
    const { name, value } = e.target;
    setExpertForm((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleFarmerChange = (e) => {
    const { name, value } = e.target;
    setFarmerForm((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleExpertSubmit = (e) => {
    e.preventDefault();
    console.log('Expert Data:', expertForm);
    alert('✅ Expert added successfully!');
    // Optional: reset form or redirect
  };

  const handleFarmerSubmit = (e) => {
    e.preventDefault();
    console.log('Farmer Data:', farmerForm);
    alert('✅ Farmer added successfully!');
    // Optional: reset form or redirect
  };

  const renderDashboardMenu = () => (
    <div className="text-center">
      <h2 className="mb-4">Admin Dashboard</h2>
      <p>Select an action:</p>
      <div className="d-grid gap-3 mx-auto" style={{ maxWidth: '300px' }}>
        <button className="btn btn-primary" onClick={() => setActiveSection('expert')}>➕ Add Expert</button>
        <button className="btn btn-success" onClick={() => setActiveSection('farmer')}>➕ Add Farmer</button>
        <button className="btn btn-info" onClick={() => alert("Feature Coming Soon!")}>📅 Manage Appointments</button>
        <button className="btn btn-warning" onClick={() => alert("Feature Coming Soon!")}>📝 Post Knowledge Article</button>
        <button className="btn btn-secondary" onClick={() => alert("Feature Coming Soon!")}>📬 Manage Feedback / Queries</button>
        <button className="btn btn-dark" onClick={() => alert("Feature Coming Soon!")}>⚙️ Settings</button>
      </div>
    </div>
  );

  const renderExpertForm = () => (
    <div className="card shadow p-4 mx-auto mt-4" style={{ maxWidth: '500px' }}>
      <h3 className="text-center mb-3">Add Expert</h3>
      <form onSubmit={handleExpertSubmit}>
        {[
          ['name', 'Expert Name'],
          ['email', 'Email'],
          ['contact', 'Contact Number'],
          ['qualification', 'Qualification'],
          ['specialization', 'Specialization'],
          ['location', 'Location'],
          ['availability', 'Available Days / Time Slots'],
          ['password', 'Password', 'password'],
        ].map(([name, label, type = 'text']) => (
          <div className="mb-3" key={name}>
            <label className="form-label">{label}</label>
            <input
              type={type}
              className="form-control"
              name={name}
              value={expertForm[name]}
              onChange={handleExpertChange}
              required
            />
          </div>
        ))}
        <button type="submit" className="btn btn-primary w-100">Add Expert</button>
        <button className="btn btn-outline-secondary w-100 mt-3" onClick={() => setActiveSection(null)}>← Back</button>
      </form>
    </div>
  );

  const renderFarmerForm = () => (
    <div className="card shadow p-4 mx-auto mt-4" style={{ maxWidth: '500px' }}>
      <h3 className="text-center mb-3">Add Farmer</h3>
      <form onSubmit={handleFarmerSubmit}>
        {[
          ['name', 'Farmer Name'],
          ['email', 'Email'],
          ['contact', 'Contact Number'],
          ['location', 'Location'],
          ['password', 'Password', 'password'],
        ].map(([name, label, type = 'text']) => (
          <div className="mb-3" key={name}>
            <label className="form-label">{label}</label>
            <input
              type={type}
              className="form-control"
              name={name}
              value={farmerForm[name]}
              onChange={handleFarmerChange}
              required
            />
          </div>
        ))}
        <button type="submit" className="btn btn-success w-100">Add Farmer</button>
        <button className="btn btn-outline-secondary w-100 mt-3" onClick={() => setActiveSection(null)}>← Back</button>
      </form>
    </div>
  );

  return (
    <div className="container mt-5">
      {activeSection === null && renderDashboardMenu()}
      {activeSection === 'expert' && renderExpertForm()}
      {activeSection === 'farmer' && renderFarmerForm()}
    </div>
  );
};

export default AdminDashboard;
