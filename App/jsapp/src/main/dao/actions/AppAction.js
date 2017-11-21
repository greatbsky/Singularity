import A from '../ActionTypes';
import ServConf from '../../conf/ServConf';
import WebUtil from '../../utils/WebUtil';
import {videoInit} from './VideoAction';

/*
应用启动时调用接口获取初始化数据
*/
export const initAppDataOnBoot = () => {
    return (dispatch) => {
        WebUtil.getJSON(ServConf.api.initialData, (data) => {
            // console.log(`data: ${data}`);
            dispatch(appInit(data.app));
            dispatch(videoInit(data.videos));
        }, (e) => {console.log(e);});
    };
}

/*
显示welcome界面
*/
export const showWelcome = () => {
    return (dispatch) => {
        dispatch({
    		type: A.updateWelcome,
    		data: {welcome: true}
    	});
    }
}

/*
隐藏welcome界面
*/
export const hideWelcome = () => {
    return (dispatch, getState) => {
        console.log(getState());
        dispatch({
    		type: A.updateWelcome,
    		data: {welcome: false}
    	});
    }
}

/*
初始化store.app数据
*/
export const appInit = (data) => {
    return {
		type: A.appInit,
		data
	};
}
