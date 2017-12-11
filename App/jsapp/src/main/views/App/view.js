import React, { Component } from 'react';
import {
  View,
  Text
} from 'react-native';

import ImageView from '../../native/components/ImageView';

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
        <ImageView src={[{uri: 'https://www.baidu.com/img/bd_logo1.png'}]} onClick={this.onClick} resizeMode={"centerCrop"} style={{width: 200, height: 50}} />
      </View>
    );
  }
}
