import { createStore } from "vuex";
import counterModule from "./vuex-modules/counter-module";
import gameListModule from "./vuex-modules/game-list-module";
import gameRegistrationModule from './vuex-modules/game-registration-module';
import biteCodeModule from './vuex-modules/bite-code-module';
import gameDetailTitleFragmentModule from "./vuex-modules/game-detail-title-fragment-module";
import loginModule from "./vuex-modules/login-module";

const store = createStore({
  modules: {
    counterModule,
    gameListModule,
    gameRegistrationModule,
    biteCodeModule,
    gameDetailTitleFragmentModule,
    loginModule
  },
});

export default store;
