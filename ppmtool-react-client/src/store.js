import {createStore, applyMiddleware, compose} from 'redux'
import thunk from 'redux-thunk'
import rootReducer from './reducers'
/*

    Holds application state
    Allows access to state via getState()
    Allows state to be updated via dispatch(action)
    Registers listeners via subscribe(listener)
    Handles unregistering of listeners via the function returned by subscribe(listener)

*/

const initialState = {}
const middleware = [thunk] 

let store
// creates the store, telling it what root reducer to use
if (window.navigator.userAgent.includes("Chrome")) {
    store=createStore(rootReducer, 
                      initialState, 
                      compose(applyMiddleware(...middleware), 
                      window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
                    ))    
} else {
        store=createStore(rootReducer, initialState, compose(applyMiddleware(...middleware))) 
}
export default store