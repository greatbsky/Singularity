import React, { Component } from 'react';
import {
  Text,
  View
} from 'react-native';

import styles from './styles/index';

export default class extends Component<{}> {

    constructor(props) {
        super(props);
        // this.props.initAppDataOnBoot();

        setTimeout(() => {this.props.hideWelcome()}, 9000);
    }

    render() {
        return (
            <View style={styles.container}>
                <Text style={styles.welcome}>
                    Welcome to React Native!
                </Text>
            </View>
        );
    }
}
