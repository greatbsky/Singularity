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
        <ImageView src={[{uri: 'http://wx3.sinaimg.cn/crop.0.0.2000.1124.1000/75894373gy1ffeqzm3kydj21jk0v97wh.jpg'}]} onClick={this.onClick} resizeMode={"centerCrop"} radius={300} style={{width: 200, height: 50}} />
      </View>
    );
  }
}
