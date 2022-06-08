// React & 3rd party imports
import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

// Custom imports
import "./TranslationView.css";
import SingleInputForm from "../Components/SingleInputForm";
import { UserContext } from "../Components/UserContext";
import { patchTranslationsAsync } from "../TranslationApi";

function TranslationView() {
  //#region TranslationView local variables
  const [letters, setLetters] = useState([]); // array of letters
  const [signLanguage, setSignLanguage] = useState(); // JSX

  let uniqueKey = 1; // used with generateKey() for React's key attribute
  const [user] = useContext(UserContext); // This will replace the line above once "Login" works
  const navigate = useNavigate();
  // #endregion TranslationView local variables

  // #region Event Hooks
  useEffect(() => {
    // onMount
    if (user.id === undefined || user.id === 0) {
      navigate(process.env.PUBLIC_URL);
    }
  });
  // #endregion

  //#region TranslationView Functions
  // Update letter input (onChange)
  function updateLetters(event) {
    const newLetters = event.target.value;
    setLetters([...newLetters]);
  }
  // Make JSX for translated letters & patch the API
  function translateLettersToSignLanguage(clickEvent) {
    clickEvent.preventDefault(); // prevent page refresh
    // Generate JSX
    const signLanguageJSX = letters.map((letter) => {
      if (isLetter(letter)) {
        return (
          <div className="display-inline" key={generateKey()}>
            <img
              className="signLanguageLetter"
              src={`${process.env.PUBLIC_URL}/individual_signs/${letter}.png`}
              alt={`sign language for '${letter}'`}
            />
          </div>
        );
      } // end isLetter if check
      else {
        return (
          // create space when it isn't a translatable letter
          <div className="display-inline margin-x-2" key={generateKey()}></div>
        );
      }
    });
    setSignLanguage(signLanguageJSX);
    // convert letters array to a string
    const lettersAsString = letters.reduce((previous, current) => {
      return (previous += current);
    }, "");

    // Patches the API with translations done by the user
    patchTranslationsAsync(lettersAsString, user); // api call
  }

  // Generate a unique key for React's key attribute
  function generateKey() {
    uniqueKey++;
    return uniqueKey;
  }
  // Source: https://stackoverflow.com/questions/9862761/how-to-check-if-character-is-a-letter-in-javascript
  function isLetter(character) {
    // checks if the character is the NOT the same in both uppercase and lowercase
    return character.toLowerCase() !== character.toUpperCase();
  }
  //#endregion Functions
  return (
    <main>
      <div className="center-container bg-yellow">
        <section>
          <h1 className="hidden">Lost in Translation</h1>
          <SingleInputForm
            placeholderText="Write translation text here..."
            onInputChange={updateLetters}
            onSubmitClick={translateLettersToSignLanguage}
          ></SingleInputForm>
        </section>
      </div>
      <div className="center-container">
        <section className="Translation-result-container">
          <div>
            <h2 className="text-center">Translation</h2>
            {signLanguage}
          </div>
        </section>
      </div>
    </main>
  );
}

export default TranslationView;
