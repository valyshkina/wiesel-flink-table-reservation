import React from 'react';
import { useNavigate } from 'react-router-dom';
import './HomePage.css';

const HomePage: React.FC = () => {
  const navigate = useNavigate(); 

  const handleSelectRestaurant = (restaurantName: string) => {
    navigate(`/options/${restaurantName}`);  
  };

  return (
    <div className ='container'>
      <h2>Bitte wählen Sie Ihr Restaurant aus</h2>
      <button className="button"onClick={() => handleSelectRestaurant('Wiesel-Flink-Food Hamburg')}>
        Wiesel-Flink-Food Hamburg
      </button>
      <button onClick={() => handleSelectRestaurant('Wiesel-Flink-Food Lünen')}>
        Wiesel-Flink-Food Lünen
      </button>
      <button onClick={() => handleSelectRestaurant('Wiesel-Flink-Food Dresden')}>
        Wiesel-Flink-Food Dresden
      </button>
    </div>
  );
};

export default HomePage;
