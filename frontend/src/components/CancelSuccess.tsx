import React from "react";
import { useNavigate } from "react-router-dom";
import './NotAvailable.css';

const CancelSuccess : React.FC = () => {
    const navigate = useNavigate(); 
    return(
        <div className="container">
            <h2>Ihre Reservierung wurde erfolgreich storniert!</h2>
            <button className="button" onClick={() => navigate('/')}>
                Zurück zur Startseite
            </button>
        </div>
    );
}

export default CancelSuccess; 