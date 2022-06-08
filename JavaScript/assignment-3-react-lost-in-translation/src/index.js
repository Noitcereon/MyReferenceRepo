// 3rd party imports
import React from 'react';
import { render } from 'react-dom';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import reportWebVitals from './reportWebVitals';

// custom imports
import './index.css';
import App from './App';
import UserProvider from './Components/UserContext';
import TranslationView from './Views/TranslationView';
import LandingView from './Views/LandingView';
import ProfileView from './Views/ProfileView';

const rootElement = document.getElementById('root');
render(
  <React.StrictMode>
    <UserProvider>
      <BrowserRouter>
        <Routes>
          <Route path={process.env.PUBLIC_URL} element={<App />} > {/* The Route elements inside this Route uses the <App/> as a base Layout) */}
            <Route path="" element={<LandingView />} />
            <Route path="translation" element={<TranslationView />} />
            <Route path="profile" element={<ProfileView />} />
            <Route path="*" element={<p>There is nothing at this address</p>} />
          </Route>
        </Routes>
      </BrowserRouter>
    </UserProvider>
  </React.StrictMode>
  , rootElement
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
