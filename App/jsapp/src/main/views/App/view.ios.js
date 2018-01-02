import React, { Component } from 'react';
import {
  View,
  Text
} from 'react-native';

import Calendar from '../../native/modules/Calendar';
import Emitter from '../../native/modules/Emitter';
import Scroll from '../../native/components/Scroll';

export default class App extends Component<{}> {

    constructor(props) {
        super(props);
        this.state = {
            timeZone: ""
        };
        this.onClick = this.onClick.bind(this);
        //Calendar.addEvent('Birthday Party', '4 Privet Drive, Surrey', 1512469324797);

        //promise
        // (async () => {
        //     var result = await Calendar.timeZone();
        //     console.log(`result: ${result}`);
        //     this.setState({
        //         timeZone: result
        //     });
        // }) ();

        //promise.then
        // Calendar.timeZone().then((result) => {
        //     console.log(`result: ${result}`);
        //     this.setState({
        //         timeZone: result
        //     });
        //     console.log(Calendar.someKey);
        // })

        //事件
        const subscription = Emitter.addListener(
          'EventResult',
          (result) => {
              console.log(`result: ${result}`);
              this.setState({
                  timeZone: result
              });
          }
        );
        // subscription.remove();
        Calendar.timeZone();



    }

    onClick(event) {
        console.log(event.nativeEvent);
    }

  render() {
    return (
      <View style={{ flex: 1, flexGrow: 1, flexDirection: 'column'}}>
        <Text>hi, world!</Text>
        <Text>Time Zone: {this.state.timeZone}</Text>
        <Text>lastTime:{this.props.video.lastTime}</Text>
        <Scroll bounces={false} style={{  height: 100, flexDirection: 'column'}} onClickEvent={this.onClick} >
          <Text>hi, world! ^_^</Text>
          <Text>hi, world! ^_^</Text>
          <Text>hi, world! ^_^</Text>
          <Text>hi, world! ^_^</Text>
            <Text>hi, world! ^_^</Text>
            <Text>hi, world! ^_^</Text>
            <Text>hi, world! ^_^</Text>
            <Text>hi, world! ^_^</Text>
              <Text>hi, world! ^_^</Text>
              <Text>hi, world! ^_^</Text>
              <Text>hi, world! ^_^</Text>
              <Text>hi, world! ^_^</Text>
              <Text>hi, world! ^_^</Text>
              <Text>hi, world! ^_^</Text>
              <Text>hi, world! ^_^</Text>
              <Text>hi, world! ^_^</Text>
                <Text>hi, world! ^_^</Text>
                <Text>hi, world! ^_^</Text>
                <Text>hi, world! ^_^</Text>
                <Text>hi, world! ^_^</Text>
                  <Text>hi, world! ^_^</Text>
                  <Text>hi, world! ^_^</Text>
                  <Text>hi, world! ^_^</Text>
                  <Text>hi, world! ^_^</Text>
                  <Text>hi, world! ^_^</Text>
                  <Text>hi, world! ^_^</Text>
                  <Text>hi, world! ^_^</Text>
                  <Text>hi, world! ^_^</Text>
                    <Text>hi, world! ^_^</Text>
                    <Text>hi, world! ^_^</Text>
                    <Text>hi, world! ^_^</Text>
                    <Text>hi, world! ^_^</Text>
                      <Text>hi, world! ^_^</Text>
                      <Text>hi, world! ^_^</Text>
                      <Text>hi, world! ^_^</Text>
                      <Text>hi, world! ^_^</Text>
        </Scroll>
      </View>
    );
  }
}
