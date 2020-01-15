import React, { Component } from 'react';
import PropTypes from 'prop-types'
import {connect} from 'react-redux'
import {createProject} from '../../actions/projectActions'

class AddProject extends Component {
    constructor() {
        super()
        this.state={
            "projectName": "",
            "projectIdentifier": "",
            "description": "",
            "startDate": "",
            "endDate": "",
            errors: {}
        }

        this.onChange = this.onChange.bind(this)
        this.onSubmit = this.onSubmit.bind(this)
    }

    onChange(e) {
        this.setState(
            {
                [e.target.name]: e.target.value
            }
        )
    }

    componentWillReceiveProps(nextProps) {
        if(nextProps.errors) {
            this.setState({errors: nextProps.errors})
        }
    }

    onSubmit(e) {
        e.preventDefault()
        const newProject = {
            "projectName": this.state.projectName,
            "projectIdentifier": this.state.projectIdentifier,
            "description": this.state.description,
            "startDate": this.state.startDate,
            "endDate": this.state.endDate
        }
        this.props.createProject(newProject, this.props.history)
    }
    
    render() {
        const {errors} = this.state

        return (
            <div className="project">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h5 className="display-4 text-center">Create / Edit Project form</h5>
                            <hr />
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                    <input type="text" className="form-control form-control-lg"
                                            placeholder="Project Name" 
                                            name="projectName" 
                                            value={this.state.projectName} 
                                            onChange={this.onChange}/>
                                            <small>{errors.projectName}</small>
                                </div>
                                <div className="form-group">
                                    <input type="text" className="form-control form-control-lg" placeholder="Unique Project ID" name="projectIdentifier" value={this.state.projectIdentifier} onChange={this.onChange}/>
                                    <small>{errors.projectIdentifier}</small>
                                </div>
                                <div className="form-group">
                                    <textarea className="form-control form-control-lg" placeholder="Project Description" name="description" value={this.state.description} onChange={this.onChange}></textarea>
                                    <small>{errors.description}</small>
                                </div>
                                <h6>Start Date</h6>
                                <div className="form-group">
                                    <input type="date" className="form-control form-control-lg" name="startDate"  value={this.state.startDate} onChange={this.onChange}/>
                                </div>
                                <h6>Estimated End Date</h6>
                                <div className="form-group">
                                    <input type="date" className="form-control form-control-lg" name="endDate"  value={this.state.endDate} onChange={this.onChange}/>
                                </div>
                                <input type="submit" className="btn btn-dark btn-block mt-4" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

// Ensures that the props we're receiving have the correct type
AddProject.propTypes={
    createProject: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
}

/*
    Everytime the store is updated, mapStateToProps is called
    Compares old state to new props passed and updates as necessary
*/

const mapStateToProps = state => ({
    errors: state.errors
})

// Connects a react component to a redux store
export default connect(mapStateToProps, {createProject})(AddProject)