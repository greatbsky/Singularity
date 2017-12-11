package com.singularity.utils;

import android.os.Bundle;

/**
 * @author architect.bian
 * @date 2017-12-11 9:25 PM
 */

public class BundleBuilder {

    private Bundle bundle;

    public BundleBuilder(String k, String v) {
        bundle = new Bundle();
        bundle.putString(k, v);
    }

    public Bundle build() {
        return bundle;
    }
}
