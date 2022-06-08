// 3rd party imports
import { useContext } from "react";
import { Link, useNavigate } from "react-router-dom";

// custom imports
import "./LoginDisplay.css";
import { UserContext } from "./UserContext";

export default function LoginDisplay() {
  // #region Variables
  const [user, setUser] = useContext(UserContext);
  const navigate = useNavigate();
  // #endregion Variables

  // #region Hooks

  // #endregion Hooks

  // #region Functions
  function logout() {
    setUser({}); // reset user object
    navigate("/");
  }
  function generateLoginDisplay() {
    if (user.id === undefined || user.id === 0) {
      return <></>;
    } else {
      return (
        <div className="login-display">
          Current user: <Link className="link" to="profile">{user.username}</Link>
          <button onClick={logout}>Log out</button>
          </div>
      );
    }
  }
  // #endregion

  return generateLoginDisplay();
}
