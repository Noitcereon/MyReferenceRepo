// 3rd party imports
import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

// custom imports
import TranslationList from "../Components/Profile/TranslationList";
import { UserContext } from "../Components/UserContext";
import {
  fetchLatestTranslationsByUserAsync,
  clearUsersTranslationsAsync,
} from "../TranslationApi";

function ProfileView() {
  // #region Variables
  const [user] = useContext(UserContext);
  const navigate = useNavigate();
  const [translations, setTranslations] = useState([]);
  const [hidden, setHidden] = useState("hidden"); // a class given to elements that need to hide
  // #endregion

  // #region Hooks
  useEffect(() => {
    // onMount
    if (user.id === undefined || user.id === 0) {
      navigate(process.env.PUBLIC_URL); // go to front page.
      return;
    }
    retrieveLatestTranslationsAsync(user);
    // Previously a memory leak was here, because useEffect was called when changes to any variables (including translations) was detected
    // Since translations are updated in here, it resulted in an endless loop.
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [user.id]);
  useEffect(() => {
    if (translations.length === 0) {
      setHidden("hidden");
    } else {
      setHidden(""); // not hidden
    }
  }, [translations]);
  // #endregion Hooks

  // #region Functions
  // retrives the latest translations for logged in user.
  async function retrieveLatestTranslationsAsync() {
    const userTranslations = await fetchLatestTranslationsByUserAsync(user);
    setTranslations(userTranslations);
    console.info("Retrieved translations");
  }
  // Clears the users translations
  async function clearTranslationsClickAsync() {
    const success = await clearUsersTranslationsAsync(user);
    if (success) {
      setTranslations([]);
    }
  }
  // #endregion Functions

  return (
    <main>
      <div className="center-container bg-yellow">
        <div>
          <h1>Profile</h1>
          <h2>Your latest translations</h2>
          <TranslationList translations={translations} />
          <button className={hidden} onClick={clearTranslationsClickAsync}>
            Clear translations
          </button>
        </div>
      </div>
    </main>
  );
}

export default ProfileView;
