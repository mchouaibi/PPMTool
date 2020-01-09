import React, {Component} from 'react'

import { Link } from 'react-router-dom'
class ProjectItem extends Component { 
    render() {
        const {project} = this.props;
        return(
            <div className="container">
                <div className="card card-body bg-light mb-3">
                    <div className="row">
                        <div className="col-2">
                            <span className="mx-auto">{project.projectIdentifier}</span>
                        </div>
                        <div className="col-lg-6 col-md-4 col-8">
                            <h3>{project.projectName}</h3>
                            <p>{project.description}</p>
                        </div>
                        <div className="col-md-4 d-none d-lg-block">
                            <ul className="list-group">
                                <Link to="#">
                                    <li className="list-group-item board">
                                        <i className="fa fa-flag-checkered pr-1">Project Board </i>
                                    </li>
                                </Link>
                                <Link to="#">
                                    <li className="list-group-item update">
                                        <i className="fa fa-edit pr-1">Update Project Info</i>
                                    </li>
                                </Link>
                                <Link to="">
                                    <li className="list-group-item delete">
                                        <i className="fa fa-minus-circle pr-1">Delete Project</i>
                                    </li>
                                </Link>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default ProjectItem