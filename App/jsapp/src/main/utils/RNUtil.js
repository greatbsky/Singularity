
import React, { Component } from 'react';
import {
    ToastAndroid
} from 'react-native';
import AppConf from '../conf/AppConf';

//React native相关的util方法
export default class {

    /**
    获取位置
    */
    // static getGeoLocation() {
    //     /*navigator.geolocation.getCurrentPosition(
    //       (initialPosition) => {
    //         console.log(initialPosition.coords.latitude + "|" + initialPosition.coords.longitude);
    //         this.props.onGetCoords(initialPosition.coords.latitude, initialPosition.coords.longitude);
    //       },
    //       (error) => {alert(error)},
    //       {enableHighAccuracy: false, timeout: 60000, maximumAge: 1000}
    //   );*/
    // }

    //相册
    // static CameraRoll() {
    //     /*
    //     CameraRoll.getPhotos({first: 6}).done((data) => {
    //             console.log(data);
    //             this.setState({
    //               photoSource: {uri: data.edges[5].node.image.uri}
    //             })},(error) => {
    //             console.warn(error);
    //           });
    //           */
    // }

    static getData(k) { //todo...
        return AsyncStorage.getItem(k).then((result) => {return result}).catch();
    }

    static setData(k, v) { //todo...
        AsyncStorage.setItem(k, v);
    }

    static delData(k) { //todo...
        AsyncStorage.delItem(k);
    }

    /**
    按返回键时处理navigator
    */
    static goBackNavigator(navigator) {
        if (navigator && navigator.getCurrentRoutes().length > 1) {
            navigator.pop();
            return true;
        }
        return false;
    }

    /**
    按返回键时处理drawer
    */
    static goBackDrawer(drawer) {
        if (drawer && drawer.closeDrawer()) {
            return true;
        }
        return false;
    }

    /**
    调用body的backHandler方法
    */
    static invokeBodyBackHandler(body) {
        if (body) {
            if (body.backHandler) {
                return body.backHandler();
            }
            if (body.getWrappedInstance && body.getWrappedInstance().backHandler) { //for redux component
                return body.getWrappedInstance().backHandler();
            }
        }
        return false;
    }

    /**
    点击两次返回键退出程序
    */
    static toastQuit = function() {
        var flag = false;
        return () => {
            // console.log(flag);
            if (flag) {
                return false;
            } else {
                ToastAndroid.show('再按一次退出程序', ToastAndroid.SHORT);
                setTimeout(function(){flag = false;}, AppConf.toastShortTime ? AppConf.toastShortTime : 2000);
                flag = true;
                return true;
            }
        };
    }();

    /**
    向下滑, y > 0, x/y绝对值 < 1
    */
    static isDownGesture(x, y) {
        if (Math.abs(x) / Math.abs(y) <= 1) { //纵向
            if (y > 0) {
                return true;
            }
        }
        return false;
    }

    /**
    向上滑, y < 0, x/y绝对值 < 1
    */
    static isUpGesture(x, y) {
        if (Math.abs(x) / Math.abs(y) <= 1) { //纵向
            if (y < 0) {
                return true;
            }
        }
        return false;
    }

    /**
    向左滑, x < 0, x/y绝对值 > 1
    */
    static isLeftGesture(x, y) {
        if (Math.abs(x) / Math.abs(y) >= 1) { //横向
            if (x < 0) {
                return true;
            }
        }
        return false;
    }

    /**
    向右滑, x > 0, x/y绝对值 > 1
    */
    static isRightGesture(x, y) {
        if (Math.abs(x) / Math.abs(y) >= 1) { //横向
            if (x > 0) {
                return true;
            }
        }
        return false;
    }
}
