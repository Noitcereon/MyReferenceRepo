import axios, {
    AxiosResponse,
    AxiosError
} from "../../node_modules/axios/index"

interface IMusicRecord {
    title: string
    artist: string
    durationInSeconds: number
    yearOfPublication: number
    isCertifiedPlatinum: boolean
}

let baseURL = "http://drmusicrest.azurewebsites.net/MusicRecord/";

new Vue({
    // TypeScript compiler complains about Vue because the CDN link to Vue is in the html file.
    // Before the application runs this TypeScript file will be compiled into bundle.js
    // which is included at the bottom of the html file.
    el: "#app",
    data: {
        fileName: "",
        musicRecords: [],
        addMusicRecord: {
            title: "", artist: "", durationInSeconds: 0,
            yearOfPublication: 0, isCertifiedPlatinum: false
        },
        addSuccessful: false,
        searchInput: "",
        searchOption: "",
        deleteTitle: "",
        deleteArtist: "",
        deleteSuccessful: false,
        updateTitle: "",
        updateArtist: "",
        updateMessage: "",
        updateMusicRecord: {
            title: "", artist: "", durationInSeconds: 0,
            yearOfPublication: 0, isCertifiedPlatinum: false
        },
        updateSuccessful: false
    },
    methods: {
        GetAll(): void {
            axios.get<IMusicRecord[]>(baseURL)
                .then((response: AxiosResponse<IMusicRecord[]>) => {
                    this.musicRecords = response.data;
                    console.log(response.data);
                })
                .catch((error: AxiosError) => {
                    console.log(error.message);
                });

        },
        SearchRecords(): void {
            let searchQuery = "search?" + this.searchOption + "=" + this.searchInput;
            axios.get<IMusicRecord[]>(baseURL + searchQuery)
                .then((response: AxiosResponse<IMusicRecord[]>) => {
                    this.musicRecords = response.data;
                    console.log(response.data);
                })
                .catch((error: AxiosError) => {

                    console.log(error.message);
                });
        },
        AddMusicRecord(): void {
            axios.post<IMusicRecord>(baseURL, this.addMusicRecord)
                .then((Response: AxiosResponse) => {
                    console.log(Response.data);
                    console.log(this.addMusicRecord);
                    this.addSuccessful = true;
                })
                .catch((error: AxiosError) => {
                    console.log(this.addMusicRecord);
                    console.log(error.message);
                })
        },

        //?artist={artist}&title={title}
        DeleteMusicRecord(deleteArtist: string, deleteTitle: string): void {
            let deleteQuery = `?artist=${this.deleteArtist}&title=${this.deleteTitle}`;
            axios.delete<IMusicRecord>(baseURL + deleteQuery)
                .then((Response: AxiosResponse) => {
                    console.log(Response.data);
                    console.log("Deleted: " + this.deleteArtist + " - " + this.deleteTitle);
                    this.deleteSuccessful = true;
                })
                .catch((error: AxiosError) => {
                    console.log("Something went wrong during deletion...");
                    console.log(error.message);
                })
        },
        UpdateMusicRecord(updateArtist: string, updateTitle: string): void {
            let updateQuery = `?artist=${updateArtist}&title=${updateTitle}`;
            axios.put<IMusicRecord>(baseURL + updateQuery, this.updateMusicRecord)
                .then((response: AxiosResponse) => {
                    console.log(response.data);
                    this.updateSuccessful = true;
                    this.updateMessage = `Updated: ${updateArtist} - ${updateTitle} => ${this.updateMusicRecord.artist} - ${this.updateMusicRecord.title}`;
                })
                .catch((error: AxiosError) => {
                    console.log(error.message);
                })
        }
    }
})