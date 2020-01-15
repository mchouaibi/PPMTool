import React from 'react';
import './App.css';
import Dashboard from './components/Dashboard';
import "bootstrap/dist/css/bootstrap.min.css"
import Header from './components/Layout/Header';
import {BrowserRouter as Router, Route} from 'react-router-dom'
import {Provider} from 'react-redux'
import AddProject from './components/Project/AddProject';
import store from './store'
import UpdateProject from './components/Project/UpdateProject';

function App() {
  return (
    
  // The <Provider /> makes the Redux store available to any nested components that have been wrapped in the connect() function
    <Provider store={store}>
      <Router>
        <Header />
        <Route exact path="/dashboard" component={Dashboard}/>
        <Route exact path="/addProject" component={AddProject}/>        
        <Route exact path="/updateProject/:id" component={UpdateProject}/>        
      </Router>
    </Provider>
  );
}

export default App;