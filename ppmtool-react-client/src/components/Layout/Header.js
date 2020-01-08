import React, {Component} from 'react'
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link,
    
  } from "react-router-dom";
import Dashboard from '../Dashboard';
  

class Header extends Component {
    render() {
        return(
            <Router>
                <nav className="navbar navbar-expand-sm navbar-dark mb-4">
                    <div className="container">
                        <Link className="navbar-brand" to="/">
                            Personal Project Management Tool
                        </Link>
                        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#mobile-nav">
                            <span className="navbar-toggler-icon" />
                        </button>
            
                        <div className="collapse navbar-collapse" id="mobile-nav">
                            <ul className="navbar-nav mr-auto">
                                <li className="nav-item">
                                    <Link className="nav-link" to="/">
                                        Dashboard
                                    </Link>
                                </li>
                            </ul>
            
                            <ul className="navbar-nav ml-auto">
                                <li className="nav-item">
                                    <Link className="nav-link " to="/register">
                                        Sign Up
                                    </Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/login">
                                        Login
                                    </Link>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>

                <Switch>
                    <Route exact path="/"><Dashboard /></Route>
                    <Route exact path="/dashboard"><Dashboard /></Route>
                </Switch>
            </Router>
        )
    }
}
export default Header