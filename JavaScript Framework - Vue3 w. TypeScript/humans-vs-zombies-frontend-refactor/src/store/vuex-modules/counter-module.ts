// counter-module is an example module for how to setup a Vuex namespaced module

import { Module } from "vuex";



const counterModule : Module<any, any> = {
  namespaced: true, 
  state() {
    return {
      counter: 0,
    };
  },
  mutations: {
    updateCounter(state: any, payload: number) {
      state.counter += payload;
    },
  },
};

export default counterModule;
