import React, { Component } from 'react';
import {
  View,
  Text
} from 'react-native';

export default class App extends Component<{}> {
  render() {
    return (
      <View>
        <Text>hi, world!</Text>
        <Text>lastTime:{this.props.video.lastTime}</Text>
      </View>
    );
  }
}
