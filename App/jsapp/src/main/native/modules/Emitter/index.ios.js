import { NativeEventEmitter, NativeModules } from 'react-native';
const { Emitter } = NativeModules;
module.exports = new NativeEventEmitter(Emitter);
