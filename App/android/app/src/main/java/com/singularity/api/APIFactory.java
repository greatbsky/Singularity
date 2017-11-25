package com.singularity.api;

import com.singularity.utils.RetrofitUtil;

/**
 * @author architect.bian
 * @date 2017-11-23 5:27 PM
 */

public class APIFactory {

    public static <T> T create(Class<T> clazz) {
        return RetrofitUtil.getDefault().create(clazz);
    }

}
