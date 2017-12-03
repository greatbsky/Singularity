package com.singularity.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.singularity.BR;
import com.singularity.R;
import com.singularity.base.BaseActivity;
import com.singularity.databinding.ActivityMainBinding;
import com.singularity.databinding.ActivityTestBinding;
import com.singularity.viewmodel.MainModel;
import com.singularity.viewmodel.UserModel;
import com.singularity.viewmodel.VideoItemModel;

import xyz.xysc.core.utils.ActivityUtil;

public class MainActivity extends BaseActivity {

    protected MainModel viewModel = new MainModel(this);
    protected VideoItemModel videoVM = new VideoItemModel(this);

    @Override
    protected void initView(Bundle savedInstanceState) {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setVm(viewModel);
        binding.setVideoVM(videoVM);
    }

    /**
     * 是否根activity
     * @return
     */
    @Override
    protected boolean isRootActivity() {
        return true;
    }

    /*----------------------------------------自定义方法----------------------------------------*/

    public static void start(Activity activity, Bundle bundle) {
        ActivityUtil.start(activity, bundle, MainActivity.class);
    }

    public static void startForResult(Activity activity, Bundle bundle, int requestCode) {
        ActivityUtil.startForResult(activity, bundle, MainActivity.class, requestCode);
    }
}
