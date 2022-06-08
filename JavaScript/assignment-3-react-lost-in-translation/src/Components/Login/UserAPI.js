const API_BASE_URL = "https://noit-noroff-assignment-api.herokuapp.com";
const API_KEY = "105E23DD288A4ED9B869F385818AE928";

export async function userExists(username) {
    try {
        //fetch(`${apiURL}/translations?username=${username}`)
        const response = await fetch(`${API_BASE_URL}/translations?username=${username}`)

        if (!response.ok) {
            throw new Error('Could not complete request.')
        }
        const data = await response.json()
        // console.log("User check: ", data);
        if (data.length <= 0) {
            return [false, null]; // user doesn't exist
        }
        if (data[0].username === username) {
            console.warn("user with that name already exists");
            return [true, data[0]]; // user exists
        }
    }
    catch (error) {
        return [error.message, null]
    }
}


export async function createUser(username) {
    try {
        const userExistsArray = await userExists(username);
        if (userExistsArray[0] === true) {
            return userExistsArray[1]; // returns existing user object
        }
        const response = await fetch(`${API_BASE_URL}/translations`, {
            method: 'POST',
            headers: {
                'X-API-Key': API_KEY,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username,
                translations: []
            })
        })
        if (!response.ok) {
            throw new Error(`This username can not be created ${username}`)
        }
        const data = await response.json()
        console.log(data)
        return [null, data]
    } catch (error) {
        return [error.message, null]

    }

}