import React, {Component} from 'react'
import { 
        BrowserRouter as Router 
       } from 'react-router-dom'
import ProjectButton from './Project/ProjectButton'
import ProjectItem from './Project/ProjectItem'

class Dashboard extends Component {
    render() {
        return(
            <Router>
                <div className="projects">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-12">
                                <h1 className="display-4 text-center">Projects</h1>
                                <br />
                                <ProjectItem />
                                <br />
                                <hr />
                                <ProjectButton />
                            </div>
                        </div>
                    </div>
                </div>

               
            </Router>
        )
    }
}

export default Dashboard