import UserService from '../Services/KeycloakService';

export default function Login() {
  console.log(UserService.isLoggedIn(), "isLoggedIn");
  return (
    <section>
      <button
        className={`bg-blue-600 rounded-lg px-5 py-1 my-2 
        ${UserService.isLoggedIn() ? "hidden" : ""}`}
        onClick={() => UserService.login()}
      >
        Login
      </button>
      <button
        className={`bg-blue-600 rounded-lg px-5 py-1 my-2 
        ${UserService.isLoggedIn() ? "" : "hidden"}`}
        onClick={() => UserService.logout()}
      >
        Logout
      </button>
      <span className={UserService.isLoggedIn() ? "" : "hidden"}>Current user: {UserService.getUsername()}</span>
      
    </section>
  );
}
