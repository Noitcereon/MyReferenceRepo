import "./LandingView.css";
import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../Components/UserContext";
import SingleInputForm from "../Components/SingleInputForm";
import { createUser } from "../Components/Login/UserAPI";

function LandingView() {
  const [user, setUser] = useContext(UserContext);
  const navigate = useNavigate();

  const updateUsername = (event) => {
    user.username = event.target.value;
    setUser(user);
  };
  const loginWithUsername = async (clickEvent) => {
    clickEvent.preventDefault();
    const tempUser = await createUser(user.username); // creates user if it doesn't exist otherwise fetches the existing user
    setUser(tempUser);
    console.log("Logged in with: ", user.username);
    navigate("translation");
  };

  return (
    <main>
      <div className="center-container bg-yellow">
        <section>
          <h1>Lost in Translation</h1>
          <h2>Get Started</h2>
          <SingleInputForm
            placeholderText="Write name here..."
            onInputChange={updateUsername}
            onSubmitClick={loginWithUsername}
          ></SingleInputForm>
        </section>
      </div>
    </main>
  );
}

export default LandingView;
