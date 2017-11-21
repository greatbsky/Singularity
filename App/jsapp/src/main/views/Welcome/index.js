import {connect} from 'react-redux';

import {initAppDataOnBoot, hideWelcome} from '../../dao/actions';
import v from './view';

const mapStateToProps = (rootState, ownProps) => {
    return {
        welcome: rootState.app.welcome ? 'true' : 'false'
    };
}
const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        hideWelcome: () => dispatch(hideWelcome()),
        // initAppDataOnBoot: () => dispatch(initAppDataOnBoot()),
    }
}
const mergeProps = (stateProps, dispatchProps, ownProps) => {
    return Object.assign({}, ownProps, stateProps, dispatchProps);
}
export default connect(mapStateToProps, mapDispatchToProps, mergeProps, {shouldComponentUpdate: true, withRef: false})(v);
