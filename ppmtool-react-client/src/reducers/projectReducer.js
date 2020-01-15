import {GET_PROJECTS, GET_PROJECT, DELETE_PROJECT} from '../actions/types'

/*

    Reducers are pure functions that take the previous state and an action, and return the next state as a new object
    They don't mutate the current state, but return a new one instead

    Reducers specify how the application's state changes in response to actions sent to the store

    Reducers describe how to update state in response to an action, but they can’t modify
    state directly
    
*/

const initialState = {
    projects:[],
    project: {}
}

export default function(state = initialState, action) {
    switch(action.type) {
        case GET_PROJECTS: return {
            ...state,
            projects: action.payload
        }
        
        case GET_PROJECT: return {
            ...state,
            project: action.payload
        }

        case DELETE_PROJECT: return {
            ...state,
            projects: state.projects.filter(project => project.projectIdentifier !== action.payload)
        }

        default: return state
    }
}