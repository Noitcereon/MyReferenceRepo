export const LoginAPI = {
    login(credentials) {
        return fetch('https://jah-noroff-api.herokuapp.com/translations', { //requesting the server
            method: 'POST',
            headers: {
                'content-Type': 'application/json'
            },
            body: JSON.stringify(credentials) //Passing the credentials
        })
        .then(async (response) => {
            if (!response.ok) {
                const {error = 'An error occurred'} = await response.json()
                throw new Error(error)
            }
            return response.json()
        })
    }
}

//Dispatch is used in redux to execute new actions.