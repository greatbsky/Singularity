import React, { Component } from 'react';
import {
    View,
    Text,
    StatusBar,
    AsyncStorage
} from 'react-native';
import {Provider} from 'react-redux';
import {PersistGate} from 'redux-persist/lib/integration/react';
//import codePush from "react-native-code-push";

import _ from './Global';
import {integrateRedux} from './Integrator';
import {initAppDataOnBoot} from './dao/actions';
import Welcome from './views/Welcome';
import App from './views/App';

export default class extends Component<{}> {

    constructor() {
        super();
        this.state = {
            loading: true
        };

        const {store, persistor} = integrateRedux(() => {
            this.setState({loading: false});
        });
        this.store = store;
        this.persistor = persistor;
        this.store.subscribe(() => {
            this.setState({welcome: this.store.getState().app.welcome});
        });
    }

    componentDidMount() {
        this.store.dispatch(initAppDataOnBoot());
    }

    render() {
        if(this.state.loading) {
            return <View><Text>loading......</Text></View>;
        }
        let content =  this.store.getState().app.welcome ? <Welcome /> : <App />;
        return (
            <Provider store={this.store}>
                <PersistGate persistor={this.persistor}>
                    <StatusBar backgroundColor='#1c73b6' translucent={false} />
                    {content}
                </PersistGate>
            </Provider>
        );
    }

}
