package com.singularity.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebViewClient;

import com.singularity.R;
import com.singularity.base.BaseActivity;
import com.singularity.conf.KeyConsts;
import com.singularity.databinding.ActivityTestBinding;
import com.singularity.databinding.ActivityWebViewBinding;
import com.singularity.vm.UserModel;
import com.singularity.vm.WebViewModel;

import xyz.xysc.core.utils.ActivityUtil;

public class WebViewActivity extends BaseActivity {

    protected WebViewModel viewModel = new WebViewModel(this);

    @Override
    protected void initView(Bundle savedInstanceState) {
        ActivityWebViewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);
        binding.setVm(viewModel);

        binding.web.loadUrl(getIntent().getStringExtra(KeyConsts.url));

        binding.include.toolbar.setTitle(R.string.setting);
        binding.include.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(binding.include.toolbar);
    }

    /*----------------------------------------自定义方法----------------------------------------*/

    public static void start(Activity activity, String url) {
        Bundle bundle = new Bundle();
        bundle.putString(KeyConsts.url, url);
        ActivityUtil.start(activity, bundle, WebViewActivity.class);
    }

    public static void startForResult(Activity activity, Bundle bundle) {
        ActivityUtil.startForResult(activity, bundle, WebViewActivity.class, rcDefault);
    }

}
