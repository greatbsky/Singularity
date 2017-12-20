import React, { Component } from 'react';
import {
  View,
  Text
} from 'react-native';

export default class App extends Component<{}> {

    constructor(props) {
        super(props);
        this.onClick = this.onClick.bind(this);
    }

    onClick(event) {
        console.log(event.nativeEvent.message);
    }

  render() {
    return (
      <View>
        <Text>hi, world!</Text>
        <Text>lastTime:{this.props.video.lastTime}</Text>
      </View>
    );
  }
}
