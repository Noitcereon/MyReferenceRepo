<script setup lang="ts">
import { inject, onMounted, computed } from "vue";
import { useRoute } from "vue-router";
import { mapState, mapMutations, mapActions, useStore } from "vuex";

const store = useStore();
const route = useRoute();
const keycloak: any = inject("kc");
const fetchAndUpdateGameDetailsPayload = {
  gameId: route.params.gameId,
  token: keycloak.token,
};

const gameTitle = computed(() => store.state.gameDetailTitleFragmentModule.gameTitle);
const gameDescription = computed(() => store.state.gameDetailTitleFragmentModule.gameDescription);
// Custom game rules are not implemented (but could be used for future extendability)
// const gameRules = computed(() => store.state.gameDetailTitleFragmentModule.gameRules);

onMounted(() => {
  store.dispatch(
    "gameDetailTitleFragmentModule/fetchAndUpdateGameDetails",
    fetchAndUpdateGameDetailsPayload
  );
});
</script>

<template>
  <article class="bg-white bg-opacity-60 p-3">
    <h1 class="text-center">{{ gameTitle }}</h1>
    <h2>Description</h2>
    <p>{{ gameDescription }}</p>
    <h2>Rules</h2>
    <p>
      Modified slightly from the official rules on
      <a  href="http://humansvszombies.org/" class="text-blue-800 font-semibold">humansvszombies.org/</a>
      (modifications marked with a #). These are the official ”Goucher-Style”
      Humans vs. Zombies rules as invented by Brad Sappington and Chris Weed in
      2005, with a few changes since to make the game more balanced and safe. We
      encourage you to adapt the rules to fit your game - account for what will
      keep your players safe and your game exciting. You can see dozens of
      alternate rule sets on the
      <a href="http://wiki.humansvszombies.org/" class="text-blue-800 font-semibold">wiki</a> or talk to players
      about
      <a href="http://forums.humansvszombies.org/" class="text-blue-800 font-semibold"
        >what works best in their games</a
      >.
    </p>
    <h3>Overview</h3>
    <p>
      Humans vs. Zombies is a game of tag. All players begin as humans, and one
      is randomly chosen to be the “Original Zombie.” The Original Zombie tags
      human players and turns them into zombies. Zombies must tag and eat a
      human every 48 hours or they starve to death and are out of the game.
    </p>

    <h3>Objective</h3>
    <p>
      The Zombies win when all human players have been tagged and turned into
      zombies. The Humans win by surviving long enough for all of the zombies to
      starve.
    </p>
    <h3>Equipment</h3>
    <ul>
      <li>Bandana</li>
      <li>Foam Dart Blaster, Marshmallow Launcher and/or socks</li>
      <li>One 3x5 index card</li>
    </ul>
    <h3>Safe Zones</h3>
    <p>
      Some areas on campus are “no play zones,” where the game is permanently
      suspended. Blasters must be concealed and no players may be stunned or
      tagged. These areas include:
    </p>
    <ul>
      <li>Bathrooms</li>
      <li>Health Centers</li>
      <li>Libraries</li>
      <li>Indoor Athletic Facilities</li>
      <li>Academic buildings</li>
    </ul>

    <p>
      Other areas on campus are merely "safe zones", where gameplay continues
      but humans can't be tagged (unless a zombie has both of their feet outside
      the safe zone). These areas include: Dorm rooms and Dining Halls
    </p>

    <h3>Safety Rules</h3>
    <p>
      Rules created for the safety of all players are strictly enforced.
      Violation of safety rules will result in a ban from the game.
    </p>
    <ul>
      <li>
        No realistic looking weaponry. Blasters must be brightly colored and
        have blaze-orange tips.
      </li>
      <li>
        Blasters may not be visible inside of academic buildings or jobs on
        campus.
      </li>
      <li>Players may not use cars or play where there is traffic.</li>
      <li>Socks, Darts or Marshmallows must not hurt on impact.</li>
      <li>
        Foam dart blasters and marshmallow launchers are children’s toys – it
        should be obvious that your blaster won’t hurt anyone. If you modify
        your blaster or are somehow not sure if it is safe, speak with a
        moderator and ask them to check your blaster.
      </li>
    </ul>

    <h3>Human Rules</h3>
    <h4>Wearing a Bandanna:</h4>
    <p>
      Humans must wear a headband around an arm or leg to identify them as
      players of the game. (This headband will come in handy when you become a
      zombie!)
    </p>
    <h4>Stunning a Zombie:</h4>
    <p>
      Humans may stun a Zombie for 15 minutes by blasting them with a blaster or
      throwing a sock at them.
    </p>
    <h4>When Tagged By a Zombie:</h4>
    <p>
      When tagged by a Zombie, a Human is required to (#) give its bite code to
      the zombie who will use it to register the kill via the app. One hour
      after being tagged, tie your bandanna around your head – you are now a
      member of the Zombie team! Go tag some Humans.
    </p>
    <h4>Staying On Campus:</h4>
    <p>
      Humans must sleep on campus (#or whereever the game is ). If you need to
      leave campus for longer than 24 hours, contact the game moderators and
      remove yourself from the game.
    </p>

    <h3>Zombie Rules</h3>
    <h4>Feeding:</h4>
    <p>
      Zombies must feed every 48 hours. A zombie feeds by reporting their tag on
      the website.
    </p>
    <h4>Tagging:</h4>
    <p>
      A tag is a firm touch to any part of a Human. After tagging a Human the
      Zombie must (#) enter the bite code collected from the Human thereby
      registering the kill.
    </p>
    <h4>Getting Shot:</h4>
    <p>
      When hit with a dart, a marshmallow, or a sock, a Zombie is stunned for 15
      minutes. A stunned zombie may not interact with the game in any way. This
      includes shielding other zombies from bullets or continuing to run toward
      a human. If shot while stunned, the zombie’s stun timer is reset back to
      15 minutes.
    </p>
    <h4>Wearing A Headband:</h4>
    <p>
      Zombies must wear a bandanna around their heads at all times. The Original
      Zombie does not need to wear a headband.
    </p>

    <h3>Other Rules</h3>
    <h4>Blasting Non-Players:</h4>
    <p>Blasting non-players is a bannable offense.</p>

    <h4>Non-Player Interference:</h4>
    <p>
      People who are not registered participants may not directly interact with
      the game. This includes bringing food to humans or spying for either team.
    </p>
    <h4>Safe Zones:</h4>
    <p>
      A zombie must have both feet outside of a safe zone to tag a human. Humans
      can stun zombies from inside of a safe-zone.
    </p>
    <h4>No Shields:</h4>
    <p>
      Zombies may not use shields to deflect foam darts, marshmallows or socks.
    </p>
    <h4>Athletes:</h4>
    <p>
      Athletes are safe during official practices, but not on the way to or from
      practice.
    </p>
    <h4>Required Academic Events:</h4>
    <p>
      Similarly, students (#and players in general) at required academic events
      are safe for the duration of the event (even if this event is in a
      free-play zone), but they are not safe on the way to or from the event.
    </p>

    <h3>DBag Clause</h3>
    <p>
      Don’t be a douchebag. Everyone plays Humans vs. Zombies to have fun, and
      the rules of HvZ only exist because we agree they do. That’s why the most
      important rule of Humans vs. Zombies is to treat your fellow players with
      respect, and gracefully accept when you have been tagged or stunned.
    </p>
  </article>
</template>

<style scoped></style>
