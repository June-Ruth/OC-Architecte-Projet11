import {useEffect, useState} from "react";

function Login() {
    const [isLogin, setIsLogin] = useState(false);
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    async function signIn() {
        const data = new URLSearchParams;
        data.append('username', username);
        data.append('password', password);

        await fetch("http://localhost:8080/perform_login", {
            method: "GET",
            mode: "no-cors",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: data,
        })
            .then((response) => {
                if (response.status === 200) {
                    setIsLogin(true)
                    return Promise.all([response.json(), response.headers]);
                } else return Promise.reject("Invalid login attempt");
            })
            .catch((message) => {
                alert(message);
            })
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
                <button type='submit' onClick={signIn}>Sign in</button>
            </form>
        </div>
    )

    return (isLogin ? <div>Login successfully</div> : loginForm)
}

export default Login