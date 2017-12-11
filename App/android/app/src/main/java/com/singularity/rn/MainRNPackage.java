package com.singularity.rn;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.singularity.rn.base.BasePackage;
import com.singularity.rn.modules.ImagePickerModule;
import com.singularity.rn.modules.NotifyModule;
import com.singularity.rn.modules.RNBridgeModule;
import com.singularity.rn.views.ImageViewManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author architect.bian
 * @date 2017-12-11 1:01 PM
 */
public class MainRNPackage extends BasePackage {

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();

        //有新的RN模块时在此增加
        modules.add(new RNBridgeModule(reactContext));
        modules.add(new NotifyModule(reactContext));
        modules.add(new ImagePickerModule(reactContext));

        return modules;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(
                new ImageViewManager()
        );
    }
}
