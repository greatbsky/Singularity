'use strict';

/**
 * 消息router
 * @author Architect.bian
 */

const log = Log(__filename);

module.exports = function(router) {

    router.get('/api/:version/app/initialdata', (ctx, next) => {
        let data = require('../mocks/initialdata');
        // let random = Math.random();
        // ctx.params.version
        return data;
    });

}
