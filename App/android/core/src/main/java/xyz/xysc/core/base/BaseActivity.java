package xyz.xysc.core.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;

import xyz.xysc.core.R;
import xyz.xysc.core.event.Events;
import xyz.xysc.core.global.Global;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Events.registerEventBus(getListener());
        initView(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Events.unregisterEventBus(getListener());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isRootActivity()) {
                return exitWithConfirm();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    /*----------------------------------------自定义方法----------------------------------------*/

    /**
     * 是否根activity
     * @return
     */
    protected boolean isRootActivity() {
        return false;
    }

    /**
     * 两次返回退出
     * @return
     */
    protected boolean exitWithConfirm() {
        if (!Global.shouldExit) {
            Global.showToastLong(R.string.confirm_exit);
            Global.shouldExitInvalideDelay();
            Global.shouldExit = true;
            return false;
        } else {
            finish();
            System.exit(0);
            return true;
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
     * 初始化视图，初始或还原组件数据
     * @param savedInstanceState
     */
    protected void initView(Bundle savedInstanceState) {

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
