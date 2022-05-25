import axios, {
    AxiosResponse,
    AxiosError
} from "../../node_modules/axios/index";

interface IBook {
    "id": number,
    "title": string,
    "author": string,
    "publisher": string,
    "price": number
}

let baseURL = "http://anbo-bookstorerest.azurewebsites.net/api/books/";

var app = new Vue({

    el: "#app",
    data:
    {
        books: [],
        errors: [],
        bookIdGet: "",
        booksLoading: false,
        getOneMessage: "",
        bookIdDelete: "",
        deleteMessage: "",
        oneBook: null,
        addBook: <IBook>{ id: 0, title: "", author: "", publisher: "", price: 0 },
        addMessage: "",
        updatedBook: <IBook>{ id: 0, title: "", author: "", publisher: "", price: 0 },
        updateMessage: ""
    },
    methods:
    {
        clearBooks: function () {
            this.books = [];
            this.oneBook = null;
            this.errors = [];
        },
        getAllBooks: function () {
            this.booksLoading = true;
            console.log("getAllBooks called.");
            axios.get<IBook[]>(baseURL)
                .then((response: AxiosResponse<IBook[]>) => {
                    // console.log(response.data);
                    this.books = response.data;
                    this.booksLoading = false;
                })
                .catch((error: AxiosError) => {
                    this.errors.push(error);
                    console.log(error)
                    this.booksLoading = false;
                });
        },
        getBookById: function (id: number) {
            let URI = baseURL + id;
            console.log(`getBookById(number) called with ${URI}`);
            axios.get<IBook>(URI)
                .then((response: AxiosResponse<IBook>) => {
                    this.oneBook = response.data;
                    this.updatedBook = response.data;
                    // console.log(this.oneBook);
                })
                .catch((e: AxiosError) => {
                    this.errors.push(e);
                    console.log(e)
                })
        },
        createBook: function () {
            console.log("createBook() called");

            axios.post<IBook>(baseURL, this.addBook)
                .then((response: AxiosResponse) => {
                    console.log("book created");
                    this.addMessage = `Added ${this.addBook.title} by ${this.addBook.author}`
                })
                .catch((e: AxiosError) => {
                    this.errors.push(e);
                    console.log(e);
                })
            console.log(`addBook: ${this.addBook.id}, ${this.addBook.title}, ${this.addBook.price}`);

        },
        updateBook: function (id: number) {
            console.log("updateBook(IBook) called");

            axios.put(baseURL + id, this.updatedBook)
                .then((httpResponse: AxiosResponse) => {
                    console.log(`Book with id ${id} was updated.`)
                    this.updateMessage = `Updated book with id ${id}.`
                })
                .catch((e: AxiosError) => {
                    this.errors.push(e);
                    console.log(e);
                })

        },
        deleteBook: function (id: number) {
            console.log("deleteBook(number) called");
            axios.delete(baseURL + id)
                .then((httpResponse: AxiosResponse) => {
                    console.log("Delete call: " + httpResponse.statusText + httpResponse.status);
                    this.deleteMessage = `Delete call successful. If book with id ${id} existed it now doesn't.`
                })
                .catch((e: AxiosError) => {
                    this.errors.push(e);
                    console.log(e);
                })
        }
    }
})
