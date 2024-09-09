import React, { useState } from "react";
import { useParams, useNavigate } from 'react-router-dom';
import { ReservationControllerApi } from '../api/apis/ReservationControllerApi';
import './IsTableAvailable.css'; 

const IsTableAvailable: React.FC = () => {
  const { restaurantName } = useParams<{ restaurantName: string }>(); 
  const [guests, setGuests] = useState<number>(0);
  const [message, setMessage] = useState<string>(''); 
  const reservationApi = new ReservationControllerApi(); 
  const navigate = useNavigate();

  const handleAvailabilityCheck = async () => {
    if (guests <= 0) {
      setMessage("Bitte geben Sie eine gültige Anzahl an Gästen ein.");
      return;
    }

    try {
      const response = await reservationApi.isTableAvailable({
        restaurantName: restaurantName!, 
        capacity: guests, 
      });

      setMessage(response);
      if(response === "kein passender Tisch ist verfügbar."){
        navigate("/not-available");
      }

      if(response === "ein passender Tisch ist verfügbar.") {
        navigate(`/available/${restaurantName}`);
      }
    } catch (error) {
      console.error('Fehler bei der Verfügbarkeit:', error);
      setMessage('Es gab ein Problem bei der Überprüfung der Verfügbarkeit.');
    }
  };

  return (
    <div className="container">
      <h2>Verfügbarkeit bei {restaurantName}</h2>
      <input
        type="number"
        className="input-field"
        placeholder="Anzahl der Gäste"
        value={guests}
        onChange={(e) => setGuests(Number(e.target.value))} 
      />
      <button className="button" onClick={handleAvailabilityCheck}>Verfügbarkeit prüfen</button>
      <button onClick={() => navigate('/')}>Zurück zur Startseite</button>
      {message && <p>{message}</p>}
    </div>
  );
};

export default IsTableAvailable;
