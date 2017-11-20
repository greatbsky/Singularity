import {connect} from 'react-redux';

import {initialDataAsync, hideWelcome} from '../../dao/actions';
import v from './view';

const mapStateToProps = (rootState, ownProps) => {
    console.log('store.state changed..............................');
    console.log(rootState);
    return rootState;
}
const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        hideWelcome: () => dispatch(hideWelcome()),
        // getInitialData: () => dispatch(initialDataAsync()),
    }
}
const mergeProps = (stateProps, dispatchProps, ownProps) => {
    return Object.assign({}, ownProps, stateProps, dispatchProps);
}
export default connect(mapStateToProps, mapDispatchToProps, mergeProps, {shouldComponentUpdate: true, withRef: false})(v);
