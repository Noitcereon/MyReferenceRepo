import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import KeycloakService from "./Services/KeycloakService";
import Router from "./router";


const renderApp = () => ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <Router/>
  </React.StrictMode>
);

KeycloakService.initKeycloak(renderApp);
