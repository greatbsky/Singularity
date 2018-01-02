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

import G from './Global';
import {integrateRedux} from './Integrator';
import {initAppDataOnBoot} from './dao/actions';
import Welcome from './views/Welcome';
import App from './views/App';

export default class extends Component<{}> {

    constructor() {
        super();
        this.state = {
            loading: true,
            newMsg: {}
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
        G.init();
        G.startNotify(this);
        // G.imagePicker();
    }

    render() {
        if(this.state.loading) {
            return <View><Text>loading......</Text></View>;
        }
        let content =  this.store.getState().app.welcome ? <Welcome /> : <App />;
        let newMsg = <View><Text>no message!</Text></View>;
        if (this.state.newMsg.title) {
            newMsg = (<View>
            <Text>Title: {this.state.newMsg.title}</Text>
            <Text>content: {this.state.newMsg.content}</Text>
            <Text>createTime: {this.state.newMsg.createTime}</Text>
            </View>);
        }
        return (
            <Provider store={this.store}>
                <PersistGate persistor={this.persistor}>
                    <View style={{ flex: 1, flexGrow: 1, paddingTop: 30, flexDirection: 'column'}}>
                        <StatusBar backgroundColor='#000000' translucent={false} />
                        {content}
                        {newMsg}
                    </View>
                </PersistGate>
            </Provider>
        );
    }

}
