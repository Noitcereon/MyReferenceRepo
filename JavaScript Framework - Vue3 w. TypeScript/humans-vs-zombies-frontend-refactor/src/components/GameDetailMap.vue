<script>
/* eslint-disable no-undef */
import { computed, onMounted, ref } from 'vue';
import { useGeolocation } from '../useGeolocation';
import { Loader } from '@googlemaps/js-api-loader';
import graveOne from '../assets/map_markers/grave1.png';
import graveTwo from '../assets/map_markers/grave2.png';
import graveThree from '../assets/map_markers/grave3.png';
import graveFour from '../assets/map_markers/grave4.png';

const GOOGLE_MAPS_API_KEY = import.meta.env.VITE_GOOGLE_MAPS_API_KEY;
let map;

export default {

    name: 'GameDetailMap',
    setup() {
        const { coords } = useGeolocation();
        const currentPosition = computed(() => ({
            lat: coords.value.latitude,
            lng: coords.value.longitude
        }));
        const mapDiv = ref(null);
        const loader = new Loader({ apiKey: GOOGLE_MAPS_API_KEY });
        const myStyles = [
            {
                featureType: "all",
                elementType: "labels",
                stylers: [
                    { visibility: "off" }
                ]
            },
        ];
        const displayGraveOne = ref(graveOne);
        const displayGraveTwo = ref(graveTwo);
        const displayGraveThree = ref(graveThree);
        const displayGraveFour = ref(graveFour);

        //const iconBase = "../../assets/map_markers/";



        onMounted(async () => {
            console.log("I am onMounted GameDetailMap");
            await loader.load();
            map = new google.maps.Map(mapDiv.value, {
                center: { lat: 55.64282929623354, lng: 12.271863063655186 },
                zoom: 16,
                mapTypeId: "hybrid",
                styles: myStyles
            });
            const rectangleCoords = [
                { lat: 55.643039411363254, lng: 12.270215128474867 },
                { lat: 55.64304546614951, lng: 12.272950981754455 },
                { lat: 55.642131182826965, lng: 12.272897337572504 },
                { lat: 55.64195558954726, lng: 12.27010784011096 },
                { lat: 55.643039411363254, lng: 12.270215128474867 }
            ];
            const playArea = new google.maps.Polyline({
                path: rectangleCoords,
                geodesic: true,
                strokeColor: "#FF0000",
                strokeOpacity: 1,
                strokeWeight: 2
            });
            playArea.setMap(map);

            const features = [
                {
                    position: new google.maps.LatLng(55.642950231412, 12.272532703721474),
                    type: "graveOne",
                },
                {
                    position: new google.maps.LatLng(55.64224807707821, 12.271318874535854),
                    type: "graveTwo"
                },
                {
                    position: new google.maps.LatLng(55.6425820300993, 12.270689200645815),
                    type: "graveThree"
                },
                {
                    position: new google.maps.LatLng(55.642594874389424, 12.270924380050527),
                    type: "graveFour"
                }
            ]

            const iconOne = {
                url: graveOne, // url
                scaledSize: new google.maps.Size(30, 30), // scaled size
                origin: new google.maps.Point(0, 0), // origin
                anchor: new google.maps.Point(0, 0) // anchor
            };
            const iconTwo = {
                url: graveTwo, // url
                scaledSize: new google.maps.Size(30, 30), // scaled size
                origin: new google.maps.Point(0, 0), // origin
                anchor: new google.maps.Point(0, 0) // anchor
            };
            const iconThree = {
                url: graveThree, // url
                scaledSize: new google.maps.Size(30, 30), // scaled size
                origin: new google.maps.Point(0, 0), // origin
                anchor: new google.maps.Point(0, 0) // anchor
            };
            const iconFour = {
                url: graveFour, // url
                scaledSize: new google.maps.Size(30, 30), // scaled size
                origin: new google.maps.Point(0, 0), // origin
                anchor: new google.maps.Point(0, 0) // anchor
            };

            const icons = {
                graveOne: {
                    icon: iconOne
                },
                graveTwo: {
                    icon: iconTwo,
                    scaledSize: new google.maps.Size(10, 10)
                },
                graveThree: {
                    icon: iconThree,
                    scaledSize: new google.maps.Size(10, 10)
                },
                graveFour: {
                    icon: iconFour,
                    scaledSize: new google.maps.Size(10, 10)
                }
            }

            for (let i = 0; i < features.length; i++) {
                const marker = new google.maps.Marker({
                    position: features[i].position,
                    icon: icons[features[i].type].icon,
                    map: map,
                    title: "Someone died here.",

                });
            }
        })
        return { currentPosition, mapDiv, displayGraveOne, displayGraveTwo, displayGraveFour, displayGraveThree }
    }
}



</script>



<template>
    <div ref="mapDiv" class="w-auto h-96"></div>
    <div class="w-auto text-center">
        <h1 class="my-6">Map Legend</h1>
        <span class="grid gap-1 grid-cols-2 mt-4 mb-4">
            <div id="rectangle"></div>
            <div
                id="play-area-description"
                class="text-center"
            >The play area. Please remain in this area, for the duration of the game!</div>
        </span>

        <span class="grid gap-1 grid-cols-6">
            <img :src="displayGraveOne" />
            <img :src="displayGraveTwo" />
            <img :src="displayGraveThree" />
            <img :src="displayGraveFour" />
            <div></div>
            <div class="mt-4" id="marker-description">These markers represent places where players have been killed. Beware!</div>
        </span>
        <div></div>
    </div>
</template>

<style scoped>
h1 {
    -webkit-text-stroke: red;
    color: black;
    -webkit-text-stroke-width: 1px;
    font-size: 3em;
    text-decoration: underline;
}
#rectangle {
    width: 150px;
    height: 100px;
    border: 10px solid;
    border-color: red;
    background-color: solid rgb(red, green, blue, 0);
}
#play-area-description {
    font-size: x-large;
    -webkit-text-stroke: red;
    -webkit-text-stroke-width: 1px;
    color: black;
}
#marker-description {
    font-size: x-large;
    -webkit-text-stroke: red;
    -webkit-text-stroke-width: 1px;
    color: black;
}
</style>
