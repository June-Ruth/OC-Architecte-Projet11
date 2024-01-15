import {useEffect, useState} from "react";
import Emergency from "./Emergency";

export default function Login() {
    const [isLogin, setIsLogin] = useState(false);
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    async function signIn() {
        const params = new URLSearchParams;
        params.append('username', username);
        params.append('password', password);

        const response = await fetch("http://localhost:8080/login?" + params.toString());

        if(response.ok) {
            setIsLogin(true);
            const user = await response.json();
            console.log(user);
            const auth = response.headers.get("WWW-Authenticate");
            console.log(auth);
        } else {
            alert("Identification failed")
        }
    }

    const loginForm = (
        <div className="form">
            <h1>Please sign in</h1>
            <form>
                <label>Username
                    <input type='text'
                           name='username'
                           placeholder="Username"
                           value={username}
                           onChange={event => setUsername(event.target.value)}/>
                </label>
                <br/>
                <label>Password
                    <input type='text'
                           name='password'
                           placeholder="Password"
                           value={password}
                           onChange={event => setPassword(event.target.value)}/>
                </label>
                <br/>
                <button type='button' onClick={signIn}>Sign in</button>
            </form>
        </div>
    )

    return (isLogin ? <Emergency/> : loginForm)
}
