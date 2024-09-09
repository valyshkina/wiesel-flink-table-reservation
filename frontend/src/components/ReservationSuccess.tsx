import React from "react";
import { useParams, useNavigate } from "react-router-dom";
import './NotAvailable.css';

const ReservationSuccess: React.FC = () => {
  const { reservationNumber } = useParams<{ reservationNumber: string }>();
  const navigate = useNavigate();

  return (
    <div className="container">
      <h2>Reservierung erfolgreich!</h2>
      <p>Ihre Reservierungsnummer lautet: #{reservationNumber}</p>
      <button className="button" onClick={() => navigate('/')}>Zurück zur Startseite</button>
    </div>
  );
};

export default ReservationSuccess;
