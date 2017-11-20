import A from '../ActionTypes';

export const showWelcome = () => {
    return (dispatch) => {
        dispatch({
    		type: A.updateWelcome,
    		data: {welcome: true}
    	});
    }
}

export const hideWelcome = () => {
    return (dispatch, getState) => {
        console.log(getState());
        dispatch({
    		type: A.updateWelcome,
    		data: {welcome: false}
    	});
    }
}
