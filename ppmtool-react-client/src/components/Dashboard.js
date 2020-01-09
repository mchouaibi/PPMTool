import React, {Component} from 'react'
import { 
        BrowserRouter as Router 
       } from 'react-router-dom'
import ProjectButton from './Project/ProjectButton'
import ProjectItem from './Project/ProjectItem'
import {connect} from 'react-redux'
import {getProjects} from '../actions/projectActions'
import Proptypes from 'prop-types'

class Dashboard extends Component {
    componentDidMount() {
        this.props.getProjects()
    }

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

Dashboard.propTypes = {
    project: Proptypes.object.isRequired,
    getProjects: Proptypes.func.isRequired
}

const mapStateToProps = state =>({
    project: state.project
})

export default connect(mapStateToProps, {getProjects})(Dashboard)