import { PropsWithChildren } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import App from "./App";
import Home from "./Components/Home";

const Router = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />}>
          <Route index element={<Home />} />
          <Route path="/movies" element={<><h1>Movies</h1> Make a movie component when login is working</>}/>
        </Route>
      </Routes>
    </BrowserRouter>
  );
};

export default Router;
