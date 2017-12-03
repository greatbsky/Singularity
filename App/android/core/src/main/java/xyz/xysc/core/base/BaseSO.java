package xyz.xysc.core.base;

import xyz.xysc.core.api.APIFactory;

/**
 * @author architect.bian
 * @date 2017-11-23 6:56 PM
 */

public abstract class BaseSO {

    protected static <T> T newAPI(Class<T> clazz) {
        return APIFactory.create(clazz);
    }
}
