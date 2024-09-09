import React from 'react';
import { useParams, useNavigate } from 'react-router-dom'; 

const OptionPage: React.FC = () => {
  const { restaurantName } = useParams<{ restaurantName: string }>();  
  const navigate = useNavigate(); 

  return (
    <div className='container'>
      <h2>Schön, dass Sie bei {restaurantName} sind</h2> 
      <button onClick={() => navigate(`/availability/${restaurantName}`)}>Verfügbarkeit prüfen</button>
      <button onClick={() => navigate(`/reservation/${restaurantName}`)}>Jetzt Tisch reservieren</button>
      <button onClick={() => navigate(`/cancle/${restaurantName}`)}>Reservierung stornieren</button>
      <button onClick={() => navigate('/')}>Zurück zur Startseite</button>
    </div>
  );
};

export default OptionPage;
