import React, { useState, useEffect } from "react";
import "../css/Biletlerim.css";
import Footer from "../components/Footer";
import { useNavigate } from "react-router-dom";

function Biletlerim() {
    const [tickets, setTickets] = useState([]);  // Biletleri tuttugumuz state
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem("userToken");

        // Token kontrolü yaptık, giriş yapılmamışsa login sayfasına yönlendirdik
        if (!token) {
            navigate("/login"); 
            return;
        }

        // LocalStorage'dan saklanan biletleri aldık yoksa bos gönderdik
        const storedTickets = JSON.parse(localStorage.getItem("tickets")) || [];
        setTickets(storedTickets);
    }, [navigate]);

    return (
        <div className="biletlerim-container">
            <div className="ticket-header">
                <h2>Biletlerim</h2>
            </div>
            <div className="ticket-content">
                {tickets.length === 0 ? (
                    <p className="no-ticket">Henüz biletiniz yok.</p>
                ) : (
                    <div className="ticket-cards">
                        {tickets.map((ticket, index) => (
                            <div className="ticket-card" key={index}>
                                <h3>Uçuş Bilgileri</h3>
                                <div className="ticket-info">
                                    <div className="ticket-detail">
                                        <strong>Koltuk Numarası:</strong>
                                        <p>{ticket.seatNumber}</p>
                                    </div>
                                    <div className="ticket-detail">
                                        <strong>PNR Kodu:</strong>
                                        <p>{ticket.pnrCode}</p>
                                    </div>
                                    <div className="ticket-detail">
                                        <strong>Yolcu ID:</strong>
                                        <p>{ticket.passengerId}</p>
                                    </div>
                                    <div className="ticket-detail">
                                        <strong>Uçuş ID:</strong>
                                        <p>{ticket.flightId}</p>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                )}
            </div>
        </div>
    );
}

export default Biletlerim;
