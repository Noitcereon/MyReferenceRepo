package dk.java.hvz.humansvszombiesbackend.controller;

public enum GameState {

    // Declare the possible game states (and their user-friendly names)
    Registration("registration"),
    InProgress("in progress"),
    Completed("completed");

    private final String userFriendlyName;

    // this constructor enables the use of the user-friendly name declaration.
    GameState(String name){ this.userFriendlyName = name; }

    // Return the user-friendly name of the enum.
    @Override
    public String toString() {
        return this.userFriendlyName;
    }
}


