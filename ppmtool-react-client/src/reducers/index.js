import {combineReducers} from 'redux'
import errorReducer from './errorReducer'
import projectReducer from './projectReducer'

// Assemble app reducers using
export default combineReducers({
    errors: errorReducer,
    project: projectReducer
})