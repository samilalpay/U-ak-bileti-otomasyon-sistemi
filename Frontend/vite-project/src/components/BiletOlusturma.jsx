import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import axios from "axios";
import "../css/BiletOlusturma.css";

function BiletOlusturma() {
    const location = useLocation(); // URL'deki parametreleri aldık
    const [ticket, setTicket] = useState(null); // Bilet bilgilerini tuttuk
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");
    // URL parametrelerini al passengerId ve flightId ye göre
    const queryParams = new URLSearchParams(location.search);
    const passengerId = queryParams.get("passengerId");
    const flightId = queryParams.get("flightId");

    useEffect(() => {
        const createOrFetchTicket = async () => {
            const localStorageKey = `ticket_${passengerId}_${flightId}`;
            const storedTicket = localStorage.getItem(localStorageKey);
              // Eğer bilet LocalStorage'da varsa, bilet bilgilerini aldık
            if (storedTicket) {
                console.log("LocalStorage'dan mevcut bilet alındı:", JSON.parse(storedTicket));
                setTicket(JSON.parse(storedTicket));
                setLoading(false);
                return;
            }
// token kontrolü
            const token = localStorage.getItem("userToken");
            if (!token) {
                setError("Yetkilendirme hatası: Giriş yapmadınız.");
                setLoading(false);
                return;
            }

            try {
                const response = await axios.post(
                    "http://localhost:8080/api/v1/tickets", // Bilet oluşturma API'si
                    { passengerId: parseInt(passengerId, 10), flightId: parseInt(flightId, 10) },
                    { headers: { Authorization: `Bearer ${token}` } }
                );

                console.log("API'den bilet oluşturuldu:", response.data);
                setTicket(response.data);
                localStorage.setItem(localStorageKey, JSON.stringify(response.data));

                // Kaydedilen bilet bilgilerini 'Biletlerim' sayfasında gösterebilmek için 
                // LocalStorage'da bunları kaydettik
                let tickets = JSON.parse(localStorage.getItem("tickets")) || [];
                tickets.push(response.data);
                localStorage.setItem("tickets", JSON.stringify(tickets)); // Biletleri güncelle
            } catch (err) {
                console.error("Bilet oluşturma hatası:", err.response?.data || err.message);
                setError(err.response?.data?.message || "Bilet oluşturulurken bir hata oluştu.");
            } finally {
                setLoading(false);
            }
        };
 // Eğer passengerId ve flightId varsa, bilet oluşturduk 
        if (passengerId && flightId) {
            createOrFetchTicket();
        }
    }, [passengerId, flightId]);

    return (
        <div className="container">
            <h2 className="page-title">Bilet Bilgileri</h2>
            {loading ? (
                <p className="loading-message">Bilet oluşturuluyor...</p>
            ) : error ? (
                <p className="error-message">{error}</p>
            ) : ticket ? (
                <div className="ticket-details">
                    <p><strong>Koltuk Numarası:</strong> {ticket.seatNumber}</p>
                    <p><strong>PNR Kodu:</strong> {ticket.pnrCode}</p>
                    <p><strong>Yolcu ID:</strong> {ticket.passengerId}</p>
                    <p><strong>Uçuş ID:</strong> {ticket.flightId}</p>
                </div>
            ) : (
                <p className="no-ticket">Bilet bilgisi alınamadı.</p>
            )}
        </div>
    );
}

export default BiletOlusturma;
