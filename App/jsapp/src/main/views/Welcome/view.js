import React, { Component } from 'react';
import {
  Platform,
  Text,
  View
} from 'react-native';

import styles from './styles/index';

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' +
    'Cmd+D or shake for dev menu',
  android: 'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

export default class extends Component<{}> {

    constructor(props) {
        super(props);
        // this.props.getInitialData();

        setTimeout(() => {this.props.hideWelcome()}, 3000);
    }

    render() {
        return (
            <View style={styles.container}>
                <Text style={styles.welcome}>
                    Welcome to React Native!
                </Text>
                <Text style={styles.instructions}>
                    To get started, edit App.js
                </Text>
                <Text style={styles.instructions}>
                    {instructions}
                </Text>
            </View>
        );
    }
}
