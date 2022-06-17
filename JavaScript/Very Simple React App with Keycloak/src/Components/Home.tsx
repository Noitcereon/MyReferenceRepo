import { MouseEvent } from "react";
import ActorService from "../Services/ActorService";

function retrieveActors(event: MouseEvent) {
  console.log("retrieveActors called");
  event.preventDefault();
  ActorService.retrieveActors();
}

export default function Home() {
  return (
    <>
      <h1>Home</h1>
      <p>This is the home page</p>
      <button
        className="py-1 px-3 bg-blue-500 text-gray-200 rounded-sm"
        onClick={retrieveActors}
      >
        Retrieve Actors
      </button>
    </>
  );
}
