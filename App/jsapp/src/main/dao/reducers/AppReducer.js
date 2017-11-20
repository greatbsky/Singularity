import A from '../ActionTypes';

export default (ele = {}, action) => {
    switch (action.type) {
            case A.updateWelcome:
            return {
                ...action.data
            }
        default:
            return ele;
    }
}
