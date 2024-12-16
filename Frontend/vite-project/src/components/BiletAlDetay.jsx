import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import "../css/BiletAlDetay.css";
import { RiFlightTakeoffFill, RiFlightLandLine } from "react-icons/ri";
import logo from "../image/logo.png";

function BiletAlDetay() {
    const location = useLocation(); //Bilet al sayfasından gelen query parametrelerini burada aldık
    const navigate = useNavigate();
    const [flights, setFlights] = useState([]); // uçuşları burada tuttuk
    const [error, setError] = useState("");

    // Query parametrelerini aldık (Nereden ve Nereye)
    const queryParams = new URLSearchParams(location.search);
    const from = queryParams.get("from");
    const to = queryParams.get("to");

    useEffect(() => {
        const fetchFlights = async () => {
            const token = localStorage.getItem("userToken");  // Kullanıcı token'ını localStorage'dan alıyoruz istek atarken kullanmak için
            if (!token) {
                setError("Giriş yapmadığınız için uçuş bilgileri yüklenemedi.");
                return;
            }

            try {
                const response = await axios.get("http://localhost:8080/api/v1/flights", { //get istegiyle secilen  uygun ucusları listeledik
                    headers: { Authorization: `Bearer ${token}` },
                });
               // 'from' ve 'to' parametrelerine göre uçuşları filtreledik
                console.log("API'den Gelen Uçuş Verisi:", response.data);
                const filteredFlights = response.data.filter(
                    (flight) =>
                        flight.departureAirportName === from &&
                        flight.arrivalAirportName === to
                );

                setFlights(filteredFlights);
            } catch (err) {
                console.error("Uçuşlar alınırken hata oluştu:", err);
                setError("Uçuş bilgileri alınırken bir hata oluştu.");
            }
        };

        fetchFlights();
    }, [from, to]);

    const handleBiletAl = (flightId) => {
        const token = localStorage.getItem("userToken");
        const passengerId = 1;

        if (!token) {
            setError("Bilgiler eksik: giriş yapmadınız.");
            return;
        }

         // Uçuş ID'si ve yolcu ID'si ile bilet oluşturma sayfasına yönlendirdik navigate sayesinde
        navigate(`/bilet-olusturma?flightId=${flightId}&passengerId=${passengerId}`);
    };

    return (
        <div className="content">
            <div className="detay-container">
                <h2 className="detay-title">Uçuş Detayları</h2>
                {error ? (
                    <div className="error-message">
                        <p>{error}</p>
                    </div>
                ) : flights.length > 0 ? (
                    <div className="flights-list">
                        {flights.map((flight) => (
                            <div className="flight-card" key={flight.id}>
                                {/* Here we make sure that `flight.id` is displayed */}
                                <h3 className="flight-id">
                                    <img src={logo} alt="Logo" className="flight-logo" />
                                    Uçuş ID: {flight.id}
                                </h3>
                                <p>
                                    <strong><RiFlightTakeoffFill /> Nereden:</strong>
                                    {flight.departureAirportName}
                                </p>
                                <p>
                                    <strong><RiFlightLandLine /> Nereye:</strong>
                                    {flight.arrivalAirportName}
                                </p>
                                <p>
                                    <strong>Kalkış Tarihi: </strong>{new Date(flight.departureTime).toLocaleString("tr-TR")}
                                </p>
                                <p>
                                    <strong>Varış Tarihi: </strong>{new Date(flight.arrivalTime).toLocaleString("tr-TR")}
                                </p>
                                <button
                                    className="btn-details"
                                    onClick={() => handleBiletAl(flight.id)} // Correctly passing flight ID
                                >
                                    Bilet Al
                                </button>
                            </div>
                        ))}
                    </div>
                ) : (
                    <div className="no-flights">
                        <p>Uygun uçuş bulunamadı.</p>
                    </div>
                )}
            </div>
        </div>
    );
}

export default BiletAlDetay;
