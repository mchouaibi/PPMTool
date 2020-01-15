import React from 'react'
import { BrowserRouter as Router, Link, Switch, Route } from 'react-router-dom'
import AddProject from './AddProject'
 const CreateProjectButton = () => {
    return(
            <Link to="/addProject" className="btn btn-lg btn-info">
                Create a Project
            </Link>

    )
 }
 export default CreateProjectButton