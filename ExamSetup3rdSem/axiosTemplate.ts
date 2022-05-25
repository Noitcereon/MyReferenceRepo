// Rest consumption with Axios and Vue TEMPLATE

import axios, {
    AxiosResponse,
    AxiosError
} from "../../node_modules/axios/index";

// Delete the IBook interface when using this template.
interface IBook {
    "id": number,
    "title": string,
    "author": string,
    "publisher": string,
    "price": number
}
interface IProjectSpecificItem{
    "id": number,
    "name": string,
    "hasRecentlyBeenChanged" : boolean
}

// URL to rest service
let baseURL = "http://anbo-bookstorerest.azurewebsites.net/api/books/";

var app = new Vue({

    el: "#wrapper",
    data:
    {
        books: [], //  <-- delete when using template.
        items: [],
        getSpecificItem: {},
        // For input values you need either an additional full object or single properties (e.g. IdForGettingItem: "")
        // Possibly add objects for each call. E.g. addObject/addItem, updateObject/updateItem
        // A loading boolean could also prove useful if there is time.
        getMessage: "",
        addMessage: "",
        updateMessage: "",
        deleteMessage: ""
    },
    methods:
    {
        ClearEverything: function () {
            this.items = [];
            this.getMessage = null;
            this.addMessage = null;
            this.updateMessage = null;
            this.deleteMessage = null;
        },
        GetAll: function () {
            //this.xLoading = true;
            console.log("GetAll called.");
            axios.get<IBook[]>(baseURL)
                .then((response: AxiosResponse<IBook[]>) => {
                    console.log(response.data);
                    this.books = response.data;
                    // this.xLoading = false;
                })
                .catch((error: AxiosError) => {
                    // Error Handling
                    console.log(error)
                });
        },
        // GetSpecific: function () {
        //     let URI = baseURL + this.object.id;
        //     console.log(`getBookById(number) called with ${URI}`);
        //     axios.get<ITemplate>(URI)
        //         .then((response: AxiosResponse<ITemplate>) => {
        //             console.log(response.data)                    
        //         })
        //         .catch((e: AxiosError) => {
        //             // Error Handling
        //             console.log(e)
        //         })
        // },
        // CreateObject: function () {
        //     console.log("createBook() called");

        //     axios.post<ITemplate>(baseURL, this.addObject)
        //         .then((response: AxiosResponse) => {
        //             console.log("book created");
        //             this.addMessage = `Added ${this.addBook.title} by ${this.addBook.author}`
        //         })
        //         .catch((e: AxiosError) => {
        //             // Error Handling
        //             console.log(e);
        //         })
        //     console.log(`addBook: ${this.addBook.id}, ${this.addBook.title}, ${this.addBook.price}`);

        // },
        // UpdateObject: function () {
        //     console.log("updateBook(ITemplate) called");

        //     axios.put(baseURL + this.updateObject.id, this.updateObject)
        //         .then((httpResponse: AxiosResponse) => {
        //             // display action in UI
        //         })
        //         .catch((e: AxiosError) => {
        //             // Error handling
        //             console.log(e);
        //         })

        // },
        // DeleteObject: function () {
        //     console.log("deleteBook(number) called");
        //     axios.delete(baseURL + this.object.id)
        //         .then((httpResponse: AxiosResponse) => {
        //             // Display action in UI
        //         })
        //         .catch((e: AxiosError) => {
        //             // Error handling
        //             console.log(e);
        //         })
        // }
    }
})
