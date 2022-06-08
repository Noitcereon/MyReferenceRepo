import { createContext, useState } from "react";

export const UserContext = createContext();

const UserProvider = (props) => {

    const [user, setUser] = useState({});

    return (
        <UserContext.Provider value={[user, setUser]}>
            {props.children}
        </UserContext.Provider>
    )
}

export default UserProvider;