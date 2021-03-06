import {combineReducers} from 'redux'
import errorReducer from './errorReducer'
import projectReducer from './projectReducer'
import backlogReducer from './backlogReducer'

// Assemble app reducers using
export default combineReducers({
    errors: errorReducer,
    project: projectReducer,
    backlog: backlogReducer
})