'use strict';

/**
 * 首页router
 * @author Architect.bian
 */

module.exports = function(router) {

    /*
    首页
     */
    router.get('/', (ctx, next) => {
        return 'index';
    });
}
