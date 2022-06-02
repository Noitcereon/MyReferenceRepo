import { Module } from "vuex";
import baseApiUrl from "../../api";
import { ICommonResponse } from "../../models/ICommonResponse";
import { IGame } from "../../models/IGame";

const gameListModule : Module<any, any>= {
  namespaced: true,
  state() {
    return {
      games: [], // expects IGame[]
      game: undefined, // expects IGame
    };
  },
  mutations: {
    setGame: (state: any, payload: IGame) => {
      state.game = payload;
    },
    setGames: (state: any, payload: IGame[]) => {
      state.games = payload;
    },
    addGame: (state: any, payload: any) => {
      state.games.push(...payload);
    },
  },
  getters: {
    getGames: (state: any): IGame[] => {
      return state.games;
    },
  },
  actions: {
    async fetchGames(context: any /* payload: any */) {
      // fetch the API data
      const response = await fetch(`${baseApiUrl}game`); // hardcoded url string to get all existing games (you may need to add games to the sytem for testing purposes).

      // Makes use of the ICommonResponse<T> (custom TypeScript interface so we have intellisense)
      const listOfGames: ICommonResponse<IGame[]> = await response.json();

      context.commit("setGames", listOfGames.payload); // payload contains the data from our api.
    },
    async fetchGameById(context: any, payload: any) {
      try {
        // payload should contain a JWT token & the gameId (payload.token, payload.gameId)
        const response: Response = await fetch(`${baseApiUrl}game/${payload.gameId}`, {
          headers: {
            Authorization: "Bearer " + payload.token,
            "Content-Type": "application/json",
          },
        });

        const data: ICommonResponse<IGame> = await response.json();

        context.commit("setGame", data.payload);
      } catch (error) {
        alert("An error occured, when fetching game data");
        console.error(error);
      }
    },
    /*
     * Using POST method's presented in the gitlab repository for the vue based game Trivia API fetch statements at:
     * https://github.com/dewald-els/noroff-assignment-api/blob/master/README.md
     * Credit to Dewald Els
     */
    async createGame(context: any, newGame: any) {
      // make a POST request to the back-end part of the application with the new game info.
      console.log(newGame);
      await fetch(`${baseApiUrl}admin/game`, {
        method: "POST",
        headers: {
          //'X-API-Key': apiKey,
          Authorization: "Bearer " + newGame.token,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          game_id: 0,
          name: newGame.name,
          game_state: newGame.gameState,
          game_description: newGame.gameDescription,
          nw_lat: 0,
          nw_lng: 0,
          se_lat: 0,
          se_lng: 0,
          rules: "",
        }),
      })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Could not create new game");
        }
        return response.json();
      })
      .then((newGame) => {
        // newGame can be grabbed onto from here
        alert("You have successfully added a new game.")
        context.commit("addGame", newGame);
      })
      .catch((error) => {});
    },
  },
};

export default gameListModule;
