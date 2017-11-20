import {
    Dimensions,
    PixelRatio,
    StatusBar
} from 'react-native';

export default {
    appuid: 'aslkklwdilkklasdkfemlasdkjfdas',
    pixel: 1 / PixelRatio.get(), //单位像素
    screen: {
        width: Dimensions.get('window').width, //480
        height: Dimensions.get('window').height //732
    },
    statusBarHeight: StatusBar.currentHeight,
    toastShortTime: 2000
}
