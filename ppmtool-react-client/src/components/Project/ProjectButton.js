import React from 'react'
import { BrowserRouter as Router, Link, Switch, Route } from 'react-router-dom'
import AddProject from './AddProject'
 const CreateProjectButton = () => {
    return(
        <Router>
            <Link to="/addProject" className="btn btn-lg btn-info">
                Create a Project
            </Link>

            <Switch>
                <Route exact path="/addProject"><AddProject /></Route>
            </Switch>
        </Router>
    )
 }
 export default CreateProjectButton