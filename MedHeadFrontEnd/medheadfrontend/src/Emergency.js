import {useState} from "react";

export default function Emergency({usernameP, passwordP}) {
    const [speciality, setSpeciality] = useState("");
    const [latitude, setLatitude] = useState("");
    const [longitude, setLongitude] = useState("");
    const [isSelectedHospital, setIsSelectedHospital] = useState(false);

    const username = usernameP;
    const password= passwordP;

    let base64 = require("base-64");

    const [hospital, setHospital] = useState({
        organisationName: "",
        address1: "",
        address2: "",
        address3: "",
        city: "",
        county: "",
        postcode: "",
        travelTime: 0,
        reservationNumber : 0
    });

    function emergencyInProgress() {
        setIsSelectedHospital(true);
    }

    function newEmergency() {
        setIsSelectedHospital(false);
    }

    async function findHospital() {
        console.log(username  + ":" +  password);

        const params = new URLSearchParams();
        params.append("speciality", speciality);
        params.append("latitude", latitude);
        params.append("longitude", longitude);

        const url = "http://" + process.env.REACT_APP_HOST + ":" + process.env.REACT_APP_PORT + "/emergency/hospital?" + params.toString();

        const response = await fetch(url, {
            headers: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Headers": "*",
                "Authorization": "Basic " + base64.encode(username + ":" + password),
                "Origin": "http://localhost:3000/"
            }
        });

        if(response.ok) {
            const medicalCenter = await response.json();
            console.log(medicalCenter);
            setHospital(medicalCenter);
            emergencyInProgress();
        } else {
            alert("Medical Center not Found");
        }
    }

    return (
        <div className="emergencyForm">
            <h1>Emergency</h1>
            <form id="emergencyForm" hidden={isSelectedHospital}>
                <label>Emergency Speciality
                    <br/>
                    <input id="speciality"
                           type="text"
                           name="speciality"
                           placeholder="Speciality"
                           value={speciality}
                           onChange={event => setSpeciality(event.target.value)}
                    />
                </label>
                <br/><br/>
                <label>Latitude
                    <br/>
                    <input id="latitude"
                           type="text"
                           name="latitude"
                           placeholder="Latitude"
                           value={latitude}
                           onChange={event => setLatitude(event.target.value)}
                    />
                </label>
                <br/><br/>
                <label>Longitude
                    <br/>
                    <input id="longitude"
                           type="text"
                           name="longitude"
                           placeholder="Longitude"
                           value={longitude}
                           onChange={event => setLongitude(event.target.value)}
                    />
                </label>
                <br/><br/>
                <button id="findHospital" type='button' onClick={findHospital}>Find hospital</button>
            </form>

            <div id="hospitalResponse" className="hospitalResponse" hidden={!isSelectedHospital}>
                <button id="newEmergency" type="button" onClick={newEmergency}>New emergency</button>
                <h2>Selected Hospital</h2>
                <h3 id="hospitalName">{hospital.organisationName}</h3>
                <p>Your reservation number is {hospital.reservationNumber}.<br/>
                    Present it upon arrival.<br/>
                    You arrival is expected in {hospital.travelTime} ms.</p>
                <label><b>Address</b>
                    <p id="address1">{hospital.address1}</p>
                    <p id="address2">{hospital.address2}</p>
                    <p id="address3">{hospital.address3}</p>
                    <p id="city">{hospital.city}</p>
                    <p id="county">{hospital.county}</p>
                    <p id="postcode">{hospital.postcode}</p>
                </label>
            </div>
        </div>
    );
}
