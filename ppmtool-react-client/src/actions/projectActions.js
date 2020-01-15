import axios from 'axios'
import {GET_ERRORS, GET_PROJECTS, GET_PROJECT, DELETE_PROJECT} from './types'

/*
    Actions only describe what happened, but don't describe how the application's state changes
    Actions are payloads of information that send data from your application to your store. They are the only source of information for the store. You send them to the store using store.dispatch()
*/

export const createProject=(project, history) => async dispatch => {
    try {
        await axios.post('/api/project', project)
        history.push('/dashboard')
    } catch (error) {
        dispatch({
            type: GET_ERRORS,
            payload: {}
        })
    }
}

export const getProjects = () => async dispatch => {
    const res = await axios.get("/api/project/all")
    dispatch({
        type: GET_PROJECTS,
        payload: res.data
    })
}

export const getProject = (id, history) => async dispatch => {   
    try {
        const res = await axios.get(`/api/project/${id}`)
        dispatch({
            type: GET_PROJECT,
            payload: res.data
        })
    } catch (error) {
        history.push('/dashboard')
    }        
}

export const deleteProject = (id, history) => async dispatch => {
    if(window.confirm("Are you sure you want to delete this project and all its data?")) {
        await axios.delete(`/api/project/${id}`)
        dispatch({
            type: DELETE_PROJECT,
            payload: id
        })
    }
}