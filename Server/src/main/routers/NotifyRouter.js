'use strict';

/**
 * 消息router
 * @author Architect.bian
 */

const log = Log(__filename);

module.exports = function(router) {

    // android
    router.get('/notify/:userid/:lastTime', (ctx, next) => {
        let data = require('../mocks/notify');
        return data;
    });
}
