import PropTypes from 'prop-types';
import {requireNativeComponent, ViewPropTypes} from 'react-native';

var componentInterface = {
  name: 'ImageView',
  propTypes: {
    src: PropTypes.string,
    resizeMode: PropTypes.oneOf(['center', 'centerCrop', 'centerInside']),
    ...ViewPropTypes, // include the default view properties
  },
};

module.exports = requireNativeComponent('ImageView', componentInterface, {});
