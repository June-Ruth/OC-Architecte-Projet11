import {useState} from "react";

export default function Emergency() {
    /*const [speciality, setSpeciality] = useState("");
    const [latitude, setLatitude] = useState("");
    const [longitude, setLongitude] = useState("");*/

    let base64 = require("base-64");

    const speciality = "ALLERGY"; // for test only
    const latitude = "53.135489"; // for test only
    const longitude = "-2.556205"; // for test only

    const username = "user"; // for test only
    const password = "user"; // for test only

    const [hospital, setHospital] = useState({
        organisationName: "",
        address1: "",
        address2: "",
        address3: "",
        city: "",
        county: "",
        postcode: "",
        specialities: [],
        travelTime: 0
    });

    async function findHospital() {
        const params = new URLSearchParams;
        params.append("speciality", speciality);
        params.append("latitude", latitude);
        params.append("longitude", longitude);

        const response = await fetch("http://localhost:8080/emergency/hospital?" + params.toString(), {
            //mode:"no-cors",
            credentials: "include",
            headers: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Headers": "*"
            }
        });

        if(response.ok) {
            const medicalCenter = await response.json();
            console.log(medicalCenter);
            setHospital(medicalCenter);
        } else {
            alert("Medical Center not Found");
        }
    }

    return (
        <div className="emergencyForm">
            <h1>Emergency</h1>
            <form>
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
            <div className="hospitalResponse" hidden={hospital.organisationName === ""}>
                <h2>Your Hospital</h2>
                <p>{hospital.organisationName}</p>
                <label><b>Address</b>
                    <p>{hospital.address1}</p>
                    <p>{hospital.address2}</p>
                    <p>{hospital.address3}</p>
                    <p>{hospital.city}</p>
                    <p>{hospital.county}</p>
                    <p>{hospital.postcode}</p>
                </label>
                <label><b>Specialities</b>
                    <p>{hospital.specialities}</p>
                </label>
                <label><b>Travel Time</b>
                    <p>{hospital.travelTime} ms</p>
                </label>
            </div>
        </div>
    );
}
