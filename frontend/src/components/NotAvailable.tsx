import React from "react";
import { useNavigate } from "react-router-dom";
import './NotAvailable.css';

const NotAvailable : React.FC = () => {
    const navigate = useNavigate(); 
    return(
        <div className="container">
            <h2>Kein passender Tisch verfügbar.</h2>
            <button className="button" onClick={() => navigate('/')}>
                Zurück zur Startseite
            </button>
        </div>
    );
}

export default NotAvailable; 