import { Module } from 'vuex';
import { IPlayer } from "../../models/IPlayer";
import { computed } from "vue";
import { ICommonResponse } from "../../models/ICommonResponse"
import baseApiUrl from '../../api';

const biteCodeModule : Module<any, any> = {
  namespaced: true,
  state: { 
    player: {
      playerId: Number,
      isHuman: String, // should this be a boolean in both backend and frontend?
      isPatientZero: String, // should this be a boolean in both backend and frontend?
      biteCode: String
    }
  },
  mutations: {
    // part of alternative solution to get bite code
    // setBiteCode(state: any, payload: String) {
    //   state.player.biteCode = payload;
    // },
    setPlayers: (state: any, payload: IPlayer[]) => {
      state.player.push(payload);
    },
    setPlayer: (state: any, payload: IPlayer) => {
      state.player = payload;
    }
  },
  getters: {
    // part of alternative solution to get bite code
    // getBiteCode: (state: any) => {
    //   return state.player.biteCode;
    // }
  },
  actions: {
    // part of alternative solution to get bite code
    // async retrieveBiteCode(context: any, activeUser: any){
    //   const apiURL = "https://dk-hvz-backend.herokuapp.com";
    //   let playerId = 0;
    //   // Makes use of the ICommonResponse<T> (custom TypeScript interface so we have intellisense)
    //   //const playerByteCode: ICommonResponse<IPlayer> = await response.json();

    //   //newPlayer.currentUser.value.payload.user_id

    //   if (activeUser.currentUser.value.payload.players.length != 0) {
    //     for (const player of activeUser.currentUser.value.payload.players) {
    //       // check to see if player exist within specific game
    //       if (player.game_id === activeUser.gameID){
    //         playerId = player.player_id
    //         // check to see if player isn't already a zombie
    //         if (player.is_human === "n")
    //         {
    //           alert("You are a zombie player, so you're bite code won't be shown")
    //           return; // revert back the function, and break it so it won't show the bite code.
    //         }
    //       }  
    //     }
    //   }

    //   await fetch(`${apiURL}/api/game/${activeUser.gameID}/player/${playerId}`, {
    //   method: "GET",
    //     headers: {
    //       //'X-API-Key': apiKey,
    //       "Authorization": "Bearer " + activeUser.keycloakToken.value,
    //       "Content-Type": "application/json",
    //     },}) // active user with user_id or player_id?
    //   .then(response => response.json())
    //   .then(results => {
    //     context.commit("setBiteCode", results.payload.bite_code)
    //     return results.payload.bite_code;
    //   })
    //   .catch(error => {
    //     alert("you are not registered in this particular game, so no bite code is shown")
    //   })
    // },
    /*
    * Using POST method's presented in the gitlab repository for the vue based game Trivia API fetch statements at:
    * https://github.com/dewald-els/noroff-assignment-api/blob/master/README.md
    * Credit to Dewald Els 
    */
    async addKillInGame(context: any, killData: any) {
      // make a POST request to the back-end part of the application with the new game info. /api/admin/game/{gameId}/player
      let players: any[] = []
      let killersPlayerId = 0
      //console.log(activeUser.keycloakToken)
      // retrieve alle players in specific game
      await fetch(`${baseApiUrl}game/${killData.gameId}/player` , {
      method: "GET",
        headers: {
          //'X-API-Key': apiKey,
          "Authorization": "Bearer " + killData.keycloakToken,
          "Content-Type": "application/json",
        }})
      .then(response => response.json())
      .then(results => {
        players = results.payload; 
      })
      .catch(error => {
      })

      for (const player of players) {
        // check to see if entered bit code exists for a player in the game
        if (player.bite_code === killData.biteCode){
          // check to see if player isn't already a zombie
          if (player.is_human === "n")
          {
            alert("You have entered the bite code of a zombie player, try again")
            return; // revert back the function, and break it so it won't show the bite code.
          }
        }
      }

      for (const player of killData.theKiller.value.players){
        if (player.game_id == killData.gameId)
        {
          killersPlayerId = player.player_id
        }
      }
      
      await fetch(`${baseApiUrl}game/${killData.gameId}/kill`, {
        method: "POST",
        headers: {
          //'X-API-Key': apiKey,
          "Authorization": "Bearer " + killData.keycloakToken,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          time_of_death: killData.timestamp,
          story: killData.story, 
          lat: 0,
          lng: 0,
          killer_id: killersPlayerId,
          victim: {
            player_id: 0,
            is_human: "y",
            is_patient_zero: "n",
            bite_code: killData.biteCode,
            user_id: 0,
            game_id: killData.gameId
          }
        }),
      })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Could not create new kill");
        }
        return response.json();
      })
      .then((newGame) => {
        // newGame can be grabbed onto from here
        // context.commit("addKill", chosenPlayer);
        alert("Successfully added new kill, you Zombie maniac!")
      })
      .catch((error) => { alert("Error when trying to add a kill, something went wrong") });
    },
  },
};

export default biteCodeModule;
