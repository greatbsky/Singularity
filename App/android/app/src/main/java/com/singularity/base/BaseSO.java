package com.singularity.base;

import com.singularity.api.APIFactory;

/**
 * @author architect.bian
 * @date 2017-11-23 6:56 PM
 */

public class BaseSO {

    protected <T> T getAPI(Class<T> clazz) {
        return APIFactory.get(clazz);
    }
}
