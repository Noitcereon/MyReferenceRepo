import { Module } from "vuex";
import baseApiUrl from "../../api";
const gameDetailTitleFragmentModule: Module<any, any> = {
  namespaced: true,
  state() {
    return {
      gameTitle: "Game title",
      gameDescription: "Game description",
      gameRules: "Game rules",
    };
  },
  mutations: {
    updateGameTitle(state: any, payload: any) {
      // Her siger ts "Parameter 'payload' implicitly has an 'any' type". Virker både med og uden guickfix (som er at tilføje ": any")
      state.gameTitle = payload;
    },
    updateGameDescription(state: any, payload: any) {
      state.gameDescription = payload;
    },
    updateGameRules(state: any, payload: any) {
      state.gameRules = payload;
    },
  },
  actions: {
    async fetchAndUpdateGameDetails(context: any, payload: any) {
      const response = await fetch(`${baseApiUrl}game/${payload.gameId}`, {
        headers: {
          Authorization: "Bearer " + payload.token,
          "Content-Type": "application/json",
        },
      });
      const data = await response.json();
      // console.log(data);
      const title = await data.payload.name; //TODO: Opdatér til det at tage game id'et (istedet for indgang 0) for det game, som vores user har klikket på
      const description = await data.payload.game_description; //TODO: Skift body ud med description
      const rules = await data.payload.rules; //TODO: Skift body ud med rules
      context.commit("updateGameTitle", title);
      context.commit("updateGameDescription", description);
      context.commit("updateGameRules", rules);
    },
  },
};

export default gameDetailTitleFragmentModule;
