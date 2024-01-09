import {useState} from "react";

function Emergency() {
    /*const [speciality, setSpeciality] = useState("");
    const [latitude, setLatitude] = useState("");
    const [longitude, setLongitude] = useState("");*/

    const speciality = "ALLERGY";
    const latitude = "53.135489";
    const longitude = "-2.556205";

    async function test()
    {
        await fetch(
            "http://localhost:8080/user",
            {
                method: "GET",
                mode: "no-cors",
            }
        )
            .then((response) =>
                {
                    console.log(response.text);
                    if (response.ok) {
                        return response.text;
                    }
                    else {
                        console.log("Test NOK")
                        return Promise.reject("Test NOK");
                    }
                }
            )
            .catch((message) => {
                    alert(message);
                }
            )
    }

    async function findHospital() {
        const params = new URLSearchParams;
        params.append('speciality', speciality);
        params.append('latitude', latitude);
        params.append('longitude', longitude);
        /*{
            speciality: speciality.toString(),
            latitude: latitude.toString(),
            longitude: longitude.toString()
        }*/

        await fetch("http://localhost:8080/emergency/hospital?" + params.toString(), {
            method: "GET",
            mode: "no-cors",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then((response) => {
                return Promise.all([response.json(), response.headers]);
            })
            .catch((message) => {
                alert(message);
            })
    }

    const emergencyForm = (
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
                        /*
                        value={speciality}
                        onChange={event => setSpeciality(event.target.value)}
                        */
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
                        /*
                        value={latitude}
                        onChange={event => setLatitude(event.target.value)}
                         */
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
                        /*
                        value={longitude}
                        onChange={event => setLongitude(event.target.value)}
                         */
                    />
                </label>
                <br/><br/>
                <button type='submit' onClick={test}>Find hospital</button>
            </form>
        </div>
    )
    return emergencyForm;
}

export default Emergency