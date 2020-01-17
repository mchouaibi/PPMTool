import axios from 'axios'
import { GET_ERRORS, GET_BACKLOG, GET_PROJECT_TASK } from './types'

export const createTask=(backlogId, projectTask, history) => async dispatch => {
    try {
        await axios.post(`/api/backlog/${backlogId}`, projectTask)
        history.push(`/projectBoard/${backlogId}`)
        dispatch({
            type: GET_ERRORS,
            payload: {}
        })
    } catch (error) {
        dispatch({
            type: GET_ERRORS,
            payload: error.response.data
        })
    }
}

export const getBacklog=(backlogId) => async dispatch => {
    try {
        const res = await axios.get(`/api/backlog/${backlogId}`);
        dispatch({
            type: GET_BACKLOG,
            payload: res.data
        })
    }
    catch(error) {
        dispatch({
            type: GET_ERRORS,
            payload: error.response.data
        })
    }
}

export const getProjectTask = (id, projectTaskId, history) => async dispatch => {
    try {
        const res = await axios.get(`/api/backlog/${id}/${projectTaskId}`);
        dispatch({
            type: GET_PROJECT_TASK,
            payload: res.data
        })
    } catch (error) {
        history.push(`/api/backlog/${id}`)
    }
}

export const updateProjectTask = (id, projectTaskId, project_task, history) => async dispatch => {
    try {
        await axios.patch(`/api/backlog/${id}/${projectTaskId}`, project_task)
        history.push(`/projectBoard/${id}`)
        dispatch({
            type: GET_ERRORS,
            payload: {}
        })
    } catch (error) {
        dispatch({
            type: GET_ERRORS,
            payload: error.response.data
        })
    }
} 