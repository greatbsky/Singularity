import {connect} from 'react-redux';

import {initialDataAsync, hideWelcome} from '../../dao/actions';
import v from './view';

const mapStateToProps = (rootState, ownProps) => {
    return rootState;
}
const mapDispatchToProps = (dispatch, ownProps) => {
    return {
    }
}
const mergeProps = (stateProps, dispatchProps, ownProps) => {
    return Object.assign({}, ownProps, stateProps, dispatchProps);
}
export default connect(mapStateToProps, mapDispatchToProps, mergeProps, {shouldComponentUpdate: true, withRef: false})(v);
