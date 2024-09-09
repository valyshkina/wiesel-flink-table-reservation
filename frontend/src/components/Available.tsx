import React from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from 'react-router-dom';
import './NotAvailable.css';

const Available : React.FC = () => {

    const { restaurantName } = useParams<{ restaurantName: string }>();

    const navigate = useNavigate(); 
    return(
        <div className="container">
            <h2>
            ein passender Tisch ist verfügbar!
            </h2>
            <button className="button" onClick={() => navigate(`/reservation/${restaurantName}`)}>
            Jetzt Tisch reservieren
            </button>
            <button className="button" onClick={() => navigate('/')}>
            Zurück zur Startseite 
            </button>

        </div>

    );
}

export default Available;