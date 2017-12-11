import { AppRegistry } from 'react-native';
import App from './jsapp/src/main/SetUp';
import Tester from './jsapp/src/test/main';

AppRegistry.registerComponent('Singularity', () => App);
AppRegistry.registerComponent('Tester', () => Tester);
AppRegistry.registerHeadlessTask('MyTask', () => require('./jsapp/src/main/MyTask'));
