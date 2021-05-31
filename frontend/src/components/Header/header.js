import React from 'react';
import {Link} from 'react-router-dom';

const header = (props) => {
    return (
        <header>
            <nav className="navbar navbar-expand-md navbar-dark navbar-fixed bg-dark">
                <a className="navbar-brand" href="/books">Book-Shop Application</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                        aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse justify-content-end" id="navbarCollapse">
                    <ul className="navbar-nav ml-md-auto">
                        <li className="nav-item m-auto" style={{padding: 15}}>
                            <Link className="nav-link active" to={"/books"} style={{fontWeight: 'bold'}}> Books </Link>
                        </li>
                        <li className="nav-item m-auto" style={{padding: 15}}>
                            <Link className="nav-link active" to={"/authors"} style={{fontWeight: 'bold'}}> Authors </Link>
                        </li>
                    </ul>
                </div>
            </nav>

        </header>

    );
}

export default header;