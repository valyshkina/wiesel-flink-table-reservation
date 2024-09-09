import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';  
import HomePage from './components/HomePage';  
import OptionPage from './components/OptionPage';
import IsTableAvailable from './components/IsTableAvailable';
import NotAvailable from './components/NotAvailable';
import Available from './components/Available';
import Reservation from './components/Reservation'; 
import ReservationSuccess from './components/ReservationSuccess';
import CancelReservation from './components/CancleReservstion';
import CancelSuccess from './components/CancelSuccess';

const App: React.FC = () => {
  return (
    <Router> 
      <Routes>  
        <Route path="/" element={<HomePage />} />  
        <Route path="/options/:restaurantName" element={<OptionPage />} />
        <Route path="/availability/:restaurantName" element={<IsTableAvailable />} />
        <Route path="/not-available" element = {<NotAvailable />} />
        <Route path="/available/:restaurantName" element ={<Available/>} />
        <Route path="/reservation/:restaurantName" element ={<Reservation/>} />
        <Route path="/reservation-success/:reservationNumber" element={<ReservationSuccess />} />
        <Route path="/cancle/:restaurantName" element={<CancelReservation />} />
        <Route path="/cancle-success/:restaurantName" element={<CancelSuccess />} />
      </Routes>
    </Router>
  );
};

export default App;
