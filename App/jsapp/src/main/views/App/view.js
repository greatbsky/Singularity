import React, { Component } from 'react';
import {
  View,
  Text
} from 'react-native';

export default class App extends Component<{}> {
  render() {
    return (
      <View>
        <Text>
          hi, world! {this.props.flag}
        </Text>
      </View>
    );
  }
}
