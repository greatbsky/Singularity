import A from '../ActionTypes';

export default (ele = {}, action) => {
    switch (action.type) {
            case A.updateWelcome:
            case A.appInit:
            return Object.assign({}, ele, action.data);
        default:
            return ele;
    }
}
