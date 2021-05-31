import './App.css'
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom';
import Header from '../Header/header'
import Authors from '../Authors/authors';
import BookShopService from "../../repository/BookRepository";
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";
import Books from "../Books/BookList/books";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      authors: [],
      books: [],
      selectedBook: {}
    }
  }

  render() {
    return (

        <Router>
          <Header/>
          <main>
            <div className={"container"}>
              <Route path={"/authors"} exact render={() =>
                  <Authors authors={this.state.authors}/>}/>
              <Route path={"/books/add"} exact render={() =>
                  <BookAdd authors={this.state.authors}
                           onAddBook={this.addBook}/>}/>
              <Route path={"/books/edit/:id"} exact render={() =>
                  <BookEdit authors={this.state.authors}
                            onEditBook={this.editBook}
                            book={this.state.selectedBook}/>}/>
              <Route path={"/books"} exact render={() =>
                  <Books books={this.state.books}
                         onDelete={this.deleteBook}
                         onEdit={this.getBook}/>}/>
              <Redirect to={"/books"}/>

            </div>
          </main>
        </Router>

    )
  }

  componentDidMount() {
    this.loadAuthors();
    this.loadBooks();
  }

  loadAuthors = () => {
    BookShopService.fetchAuthors()
        .then((data) => {
          this.setState({
            authors: data.data
          })
        });
  }

  loadBooks = () => {
    BookShopService.fetchBooks()
        .then((data) => {
          this.setState({
            books: data.data
          })
        });
  }

  deleteBook = (id) => {
    BookShopService.deleteBook(id)
        .then(() => {
          this.loadBooks();
        });
  }

  addBook = (bookTitle, ISBN, year,author) => {
    BookShopService.addBook(bookTitle, ISBN, year,author)
        .then(() => {
          this.loadBooks();
        });
  }
  getBook = (id) => {
    BookShopService.getBook(id)
        .then((data) => {
          this.setState({
            selectedBook: data.data
          })
        })
  }

  editBook = (id, bookTitle, ISBN, year,author) => {
    BookShopService.editBook(id, bookTitle, ISBN, year,author)
        .then(() => {
          this.loadBooks();
        });
  }


}

export default App;