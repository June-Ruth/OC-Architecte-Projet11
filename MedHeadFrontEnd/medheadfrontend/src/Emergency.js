import {useState} from "react";

export default function Emergency({usernameP, passwordP}) {
    /*const [speciality, setSpeciality] = useState("");
    const [latitude, setLatitude] = useState("");
    const [longitude, setLongitude] = useState("");*/
    const [isSelectedHospital, setIsSelectedHospital] = useState(false);

    const username = usernameP;
    const password= passwordP;

    let base64 = require("base-64");

    const speciality = "ALLERGY"; // for test only
    const latitude = "53.135489"; // for test only
    const longitude = "-2.556205"; // for test only


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

        const params = new URLSearchParams;
        params.append("speciality", speciality);
        params.append("latitude", latitude);
        params.append("longitude", longitude);

        const response = await fetch("http://localhost:8080/emergency/hospital?" + params.toString(), {
            //mode:"no-cors",
            credentials: "include",
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
            <form hidden={isSelectedHospital}>
                <label>Emergency Speciality
                    <br/>
                    <input type="text"
                           name="speciality"
                           placeholder="Speciality"
                           defaultValue={speciality} //for tests only
                           readOnly={true} //for tests only
                        //value={speciality}
                        //onChange={event => setSpeciality(event.target.value)}
                    />
                </label>
                <br/><br/>
                <label>Latitude
                    <br/>
                    <input type="text"
                           name="latitude"
                           placeholder="Latitude"
                           defaultValue={latitude} //for tests only
                           readOnly={true} //for tests only
                        //value={latitude}
                        //onChange={event => setLatitude(event.target.value)}
                    />
                </label>
                <br/><br/>
                <label>Longitude
                    <br/>
                    <input type="text"
                           name="longitude"
                           placeholder="Longitude"
                           defaultValue={longitude} //for tests only
                           readOnly={true} //for tests only
                        //value={longitude}
                        //onChange={event => setLongitude(event.target.value)}
                    />
                </label>
                <br/><br/>
                <button type='button' onClick={findHospital}>Find hospital</button>
            </form>

            <div className="hospitalResponse" hidden={!isSelectedHospital}>
                <button type='button' onClick={newEmergency}>New emergency</button>
                <h2>Selected Hospital</h2>
                <h3>{hospital.organisationName}</h3>
                <p>Your reservation number is {hospital.reservationNumber}.<br/>
                    Present it upon arrival.<br/>
                    You arrival is expected in {hospital.travelTime} ms.</p>
                <label><b>Address</b>
                    <p>{hospital.address1}</p>
                    <p>{hospital.address2}</p>
                    <p>{hospital.address3}</p>
                    <p>{hospital.city}</p>
                    <p>{hospital.county}</p>
                    <p>{hospital.postcode}</p>
                </label>
            </div>
        </div>
    );
}
