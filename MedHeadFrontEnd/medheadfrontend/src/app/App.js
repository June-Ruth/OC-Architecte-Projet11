import './App.css';
import logo from "../resources/logo.png";
import Login from "../login/Login";

export default function App() {
  return (
      <div>
          <header>
              <img src={logo} alt="Logo" className="Logo"/>
          </header>
          <Login/>
      </div>

  );
}
