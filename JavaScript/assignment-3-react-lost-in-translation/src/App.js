// 3rd party imports
import { Link, Outlet } from "react-router-dom";
// custom imports
import "./App.css";
import LoginDisplay from "./Components/LoginDisplay";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <div className="App-header-container">
          <span className="App-logo-text">Lost in Translation</span>
          {/* <img className="App-logo" src={logo} alt="react logo" /> */}
          <nav className="App-main-nav">
            <ul>
              <li><Link to={process.env.PUBLIC_URL}>Home</Link></li>
              <li><Link to={`${process.env.PUBLIC_URL}/translation`}>Translation</Link></li>
              <li><Link to={`${process.env.PUBLIC_URL}/profile`}>Profile</Link></li>
            </ul>
          </nav>
          <LoginDisplay />
        </div>
      </header>
      <Outlet />
      {/* <footer className="App-footer"></footer> */}
    </div>
  );
}

export default App;
