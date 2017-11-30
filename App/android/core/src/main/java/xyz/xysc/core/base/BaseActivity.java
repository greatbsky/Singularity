package xyz.xysc.core.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerEventBus();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterEventBus();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    /**
     * 注册监听到eventbus
     */
    private void registerEventBus() {
        if (getListener() != null) {
            EventBus.getDefault().unregister(getListener());
        }
    }

    /**
     * 取消注册
     */
    private void unregisterEventBus() {
        if (getListener() != null) {
            EventBus.getDefault().unregister(getListener());
        }
    }

    /**
     * EventBus的Listener
     * @return
     */
    protected Object getListener() {
        return null;
    }

    /**
     * 初始化视图
     */
    protected void initView() {

    }

    /**
     * toolbar的事件处理
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
