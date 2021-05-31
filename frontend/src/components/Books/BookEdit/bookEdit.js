import React from 'react';
import {useHistory} from 'react-router-dom';
import bookTerm from "../BookTerm/bookTerm";

const BookEdit = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        bookTitle: "",
        ISBN: 0,
        author: 0,
        year: 0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const bookTitle = formData.bookTitle !== "" ? formData.bookTitle : props.book.bookTitle;
        const ISBN = formData.ISBN !== 0 ? formData.ISBN : props.book.ISBN;
        const year= formData.year !== 0 ? formData.year : props.book.year;
        const author = formData.author !== 0 ? formData.author : props.book.author.id;


        props.onEditBook(props.book.id, bookTitle, ISBN,year, author);
        history.push("/books");
    }

    return (
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book Title</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               placeholder={props.book.bookTitle}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="copies">ISBN</label>
                        <input type="text"
                               className="form-control"
                               id="ISBN"
                               name="isbn"
                               placeholder={props.book.ISBN}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="category">Year</label>
                        <input type="text"
                               className="form-control"
                               id="year"
                               name="year"
                               placeholder={props.book.year}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select  name="author" className="form-control" onChange={handleChange}>
                            {props.authors.map((term) =>
                                <option value={term.id}>{term.name}</option>
                            )}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default BookEdit;
