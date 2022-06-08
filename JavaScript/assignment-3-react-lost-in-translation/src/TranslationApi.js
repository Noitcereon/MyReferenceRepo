// TranslationApi.js Handles all translation interaction with the API

const API_BASE_URL = "https://noit-noroff-assignment-api.herokuapp.com";
const API_KEY = "105E23DD288A4ED9B869F385818AE928"; // secret (ought to be in an env variable or something)
// Updates the users translations in the api
export async function patchTranslationsAsync(newTranslations, user) {
    if (isValidUser(user) === false) {
        console.warn("Invalid user data");
        return; // exit function
    }

    const previousTranslations = await fetchTranslationsByUserAsync(user); // fetches previous translations
    const mergedTranslations = previousTranslations.concat(newTranslations); // combines previous translations with new

    // Api call to patch translations
    const response = await fetch(`${API_BASE_URL}/translations/${user.id}`, {
        method: "PATCH",
        headers: {
            "X-API-Key": API_KEY,
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            translations: mergedTranslations, // replace the old translations with the merged translation array
        }),
    }); // Patch api call end

    if (response.ok) {
        console.info("Translations updated succesfully");
        return; // exit function
    }
    // Should only reach here if it didn't succeed.
    console.error("Failed to patch translations");
}
// Fetches translations connected to a specific user from the API
export async function fetchTranslationsByUserAsync(user) {
    if (isValidUser(user) === false) {
        console.warn("Invalid user data");
        return; // exit function
    }
    const response = await fetch(`${API_BASE_URL}/translations/${user.id}`)
    const userData = await response.json();
    return userData.translations;
}

// Fetches the latest translation connected to a specific user from the API up to a specified limit.
export async function fetchLatestTranslationsByUserAsync(user, maxTranslations = 10) {
    if (isValidUser(user) === false) {
        console.warn("Invalid user data");
        return; // exit function
    }
    try {
        const allTranslations = await fetchTranslationsByUserAsync(user);
        for (const translation of allTranslations) {
            if (allTranslations.length <= maxTranslations) {
                // if allTranslations contains maxTranslations stop the loop. 
                break;
            }
            allTranslations.shift(); // remove first translation from allTranslations
            console.info(`removed ${translation}`)
        }
        // filter so there is, at most, maxTranslations in the array
        const filteredTranslations = allTranslations.filter((translation, index) => {
            return index < maxTranslations;
        });
        return filteredTranslations;
    }
    catch (error) {
        console.error(error);
    }
}
export async function clearUsersTranslationsAsync(user) {
    if (isValidUser(user) === false) {
        console.warn("Invalid user data");
        return; // exit function
    }
    // TODO: patch translations with deleted: true added to them instead of using empty array
    const response = await fetch(`${API_BASE_URL}/translations/${user.id}`, {
        method: "PATCH",
        headers: {
            "X-API-Key": API_KEY,
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            translations: [], // replace old translations with empty array
        }),
    });
    if (response.ok) {
        console.info("Successfully cleared translations");
        return true; // success
    }
    console.error("An error occurred while trying to clear translations.");
    return false // failure
}
// Returns true if user is valid, otherwise false.
function isValidUser(user) {
    // Very simple validity check
    return user.id > 0;
}
