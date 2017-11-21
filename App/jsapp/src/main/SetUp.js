import React, { Component } from 'react';
import {
    View,
    Text,
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
        let loading = <View><Text>loading......</Text></View>;
        let content = this.state.loading ? loading : (this.store.getState().app.welcome ? <Welcome /> : <App />);
        return (
            <Provider store={this.store}>
                <PersistGate persistor={this.persistor}>
                    {content}
                </PersistGate>
            </Provider>
        );
    }

}
