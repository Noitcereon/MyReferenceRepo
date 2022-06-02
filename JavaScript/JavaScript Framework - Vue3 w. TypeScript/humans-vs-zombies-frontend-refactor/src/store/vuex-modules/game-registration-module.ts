import { IPlayer } from '../../models/IPlayer';
import { ICommonResponse } from "../../models/ICommonResponse";
import { IGame } from "../../models/IGame";
import { Module } from 'vuex';
import baseApiUrl from '../../api';

const gameRegistrationModule : Module<any, any>= {
    namespaced: true,
    state() {
      return {
        players: [{}], // expects IGame[]
      };
    },
    mutations: {
      setPlayers: (state: any, payload: IPlayer[]) => {
        state.players.push(payload);
      },
      addPlayer: (state: any, payload: any) => {
        state.players.push(...payload);
      }
    },
    actions: {
    /*
    * Using POST method's presented in the gitlab repository for the vue based game Trivia API fetch statements at:
    * https://github.com/dewald-els/noroff-assignment-api/blob/master/README.md
    * Credit to Dewald Els 
    */
    async addPlayerInGame(context: any, newPlayer: any) {
      // make a POST request to the back-end part of the application with the new game info. /api/admin/game/{gameId}/player
      ///user/{userId}/game/{gameId}/player"
      await fetch(`${baseApiUrl}user/${newPlayer.currentUser.value.user_id}/game/${newPlayer.gameID}/player`, {
        method: "POST",
        headers: {
          "Authorization": "Bearer " + newPlayer.token,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          player_id: 0,
          is_human: newPlayer.playerType,
          is_patient_zero: newPlayer.playerZero,
          bite_code: "",
          game_id: newPlayer.gameID
        }),
      })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Could not create new player");
        }
        return response.json();
      })
      .then((newGame) => {
        // newGame can be grabbed onto from here
        alert("You're now a player in the game. Good luck.")
      })
      .catch((error) => {});
    },
    }
};

export default gameRegistrationModule;
