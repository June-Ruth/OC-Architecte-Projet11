import {useState} from "react";
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
            console.log(username + ":" + password)
            const auth = response.headers.get("WWW-Authenticate");
        } else {
            alert("Identification failed")
        }
    }

    const loginForm = (
        <div className="loginForm">
            <h1>Please sign in</h1>
            <form id="loginForm">
                <label>Username
                    <input id="username"
                           type="text"
                           name="username"
                           placeholder="Username"
                           value={username}
                           onChange={event => setUsername(event.target.value)}/>
                </label>
                <br/>
                <label>Password
                    <input id="password"
                           type="text"
                           name="password"
                           placeholder="Password"
                           value={password}
                           onChange={event => setPassword(event.target.value)}/>
                </label>
                <br/>
                <button id="signIn" type="button" onClick={signIn}>Sign in</button>
            </form>
        </div>
    )

    return (isLogin ? <Emergency usernameP={username} passwordP={password}/> : loginForm)
}