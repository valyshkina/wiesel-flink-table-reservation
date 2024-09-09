import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { ReservationControllerApi } from '../api/apis/ReservationControllerApi';
import './IsTableAvailable.css'; 


const Reservation: React.FC = () => {

  const [name, setName] = useState<string>(''); 
  const [guests, setGuests] = useState<number>(0); 
  const [message, setMessage] = useState<string>(''); 
  const reservationApi = new ReservationControllerApi(); 
  const navigate = useNavigate();
  const { restaurantName } = useParams<{ restaurantName: string }>();

  const handleReservation = async () => {
    if (guests <= 0 || name === '') {
      setMessage("Bitte geben Sie einen gültigen Namen und eine gültige Anzahl an Gästen ein.");
      return;
    }

    try {
    
      const response = await reservationApi.reserve({
        restaurantName: restaurantName!,
        capacity: guests,
        reservedBy: name,
      });

      setMessage(response); 

      if(response === "Kein passender Tisch verfügbar."){
        navigate("/not-available");
      }

      if(response.startsWith("Reservierung erfolgreich")) {
        const reservationNumber = response.split(":")[1].trim();
        navigate(`/reservation-success/${reservationNumber}`);

      }

    } catch (error) {
      console.error('Fehler bei der Reservierung:', error);
      setMessage('Es gab ein Problem bei der Reservierung.');
    }
  };

  return (
    <div className="container">
      <h1>Bitte Daten für die Reservierung eingeben</h1>
      
      <input
        type="text"
        placeholder="Ihrer Name"
        value={name}
        onChange={(e) => setName(e.target.value)} 
        className="input-field"
      />

      <input
        type="number"
        placeholder="Anzahl der Gäste"
        value={guests}
        onChange={(e) => setGuests(Number(e.target.value))} 
        className="input-field"
      />

      <button className="button" onClick={handleReservation}>
        Jetzt Tisch reservieren
      </button>

      <button className="button" onClick={() => navigate('/')}>
        Zurück zur Startseite
      </button>

      {message && <p>{message}</p>}
    </div>
  );
};

export default Reservation;
