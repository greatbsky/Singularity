import { requireNativeComponent, ViewPropTypes } from 'react-native';
import PropTypes from 'prop-types';

var componentInterface = {
    name: 'Scroll',
    propTypes: {
        /**
         * A Boolean value that determines whether the user may use pinch
         * gestures to zoom in and out of the map.
         */
        bounces: PropTypes.bool,
        onClickEvent: PropTypes.func,
        // ...ViewPropTypes, // include the default view properties
    }
};
module.exports = requireNativeComponent('Scroll', componentInterface, {
  nativeOnly: {}
});
