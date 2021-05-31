import axios from '../custom-axios/axios';

const BookShopService = {
    fetchAuthors: () => {
        return axios.get("/authors");
    },
    fetchBooks: () => {
        return axios.get("/books");
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    addBook: (bookTitle,ISBN,year,author) => {
        return axios.post("/books/add", {
            "bookTitle" : bookTitle,
            "ISBN" : ISBN,
            "year" : year,
            "author" : author

        });
    },
    editBook: (id, bookTitle,ISBN, year, author) => {
        return axios.put(`/books/edit/${id}`, {
            "bookTitle" : bookTitle,
            "ISBN" : ISBN,
            "year" : year,
            "author" : author,
        });
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    }
}
export default BookShopService;
