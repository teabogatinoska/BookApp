import React from "react";

const authors = (props) => {
    return (
        <div className="container mm-4 mt-5">
            <h1>Authors List</h1>
            <table border="1" className="table table-striped table-responsive-md">
                <thead>
                <tr>
                    <th scope={"col"}>Name</th>
                    <th scope={"col"}>Surname</th>
                    <th scope={"col"}>Birth Year</th>
                </tr>
                </thead>
                <tbody>
                {props.authors.map((term, index) => {
                    return (
                        <tr>
                            <td>{term.firstName}</td>
                            <td>{term.lastName}</td>
                            <td>{term.yearOfBirth}</td>
                        </tr>
                    );
                })}
                </tbody>
            </table>
        </div>

    );
}

export default authors;