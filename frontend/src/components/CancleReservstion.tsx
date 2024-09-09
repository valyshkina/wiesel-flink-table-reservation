import React, { useState } from "react";
import { useNavigate, useParams } from 'react-router-dom';
import { ReservationControllerApi } from '../api/apis/ReservationControllerApi';
import './IsTableAvailable.css'; 

const CancelReservation: React.FC = () => {
  const { restaurantName } = useParams<{ restaurantName: string }>();
  const [reservedBy, setReservedBy] = useState<string>(''); 
  const [reservationNumber, setReservationNumber] = useState<number | null>(null); 
  const [message, setMessage] = useState<string>(''); 
  const reservationApi = new ReservationControllerApi(); 
  const navigate = useNavigate();


  const handleCancelReservation = async () => {
    if (!reservedBy || reservationNumber === null) {
      setMessage("Bitte geben Sie alle erforderlichen Daten ein.");
      return;
    }

    if (reservationNumber <= 0) {
        setMessage("Bitte geben Sie eine gültige Reservierungsnummer ein.");
        return;
      }
  

    try {
      const response = await reservationApi.cancelReservation({
        restaurantName: restaurantName!,
        reservedBy: reservedBy,
        reservationNumber: reservationNumber
      });

      setMessage(response);
      if(response === "Reservierung wurde erfolgreich storniert."){
        navigate(`/cancle-success/${restaurantName}`);
      }
    } catch (error) {
      console.error('Fehler bei der Stornierung:', error);
      setMessage('Es gab ein Problem bei der Stornierung der Reservierung.');
    }
  };

  return (
    <div className="container">
      <h2>Reservierung stornieren für {restaurantName}</h2>
      
      <input
        type="text"
        placeholder="Ihr Name"
        value={reservedBy}
        onChange={(e) => setReservedBy(e.target.value)} 
        className="input-field"
      />
      
      <input
        type="number"
        placeholder="Reservierungsnummer"
        value={reservationNumber || ''} 
        onChange={(e) => setReservationNumber(Number(e.target.value))} 
        className="input-field"
      />

      <button className="button" onClick={handleCancelReservation}>Reservierung stornieren</button>
      <button className="button" onClick={() => navigate('/')}>Zurück zur Startseite</button>

      {message && <p>{message}</p>}
    </div>
  );
};

export default CancelReservation;
