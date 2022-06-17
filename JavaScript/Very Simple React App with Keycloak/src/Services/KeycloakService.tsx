import Keycloak from "keycloak-js";

const keycloak = new Keycloak("./src/keycloak.json"); // relative path from index.html

const initKeycloak = (renderApp: CallableFunction) => {
  keycloak
    .init({
      onLoad: "check-sso",
      silentCheckSsoRedirectUri:
        window.location.origin + "/silent-check-sso.html",
      pkceMethod: "S256",
    })
    .then(function (authenticated) {
      renderApp();
      if (authenticated) {
        console.info("Authenticated");
      } else {
        console.info("Not authenticated");
      }
    })
    .catch(function () {
      console.error("Failed to initialize Keycloak");
    });
};

const getUsername = () => String(keycloak.tokenParsed?.preferred_username);
const getToken = () => {
  if (!keycloak.isTokenExpired()) return keycloak.token;
  keycloak
    .updateToken(0)
    .then((refreshed) => {
      if (refreshed) alert("Token was refreshed");
      else alert("Token is still valid");
    })
    .catch((error) => alert("Failed to refresh token or session has expired"));
};

const login = () => keycloak.login();
const logout = () => keycloak.logout();
const isLoggedIn = () => keycloak.authenticated;

export default {
  initKeycloak,
  keycloak,
  getToken,
  getUsername,
  isLoggedIn,
  login,
  logout,
};
