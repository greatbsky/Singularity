package com.singularity.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.singularity.BuildConfig;
import com.singularity.R;

import my.views.TabLayout;
import my.views.ViewPager;

public class BookTestActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_book_test);
//    }



    private static final String TAG = "MainActivity";

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_test);
        mTextView = (TextView) findViewById(R.id.my_text_view);
        mTextView.setOnClickListener(this); // 设置MyTextView的点击处理
        mTextView.setOnTouchListener(this); // 设置MyTextView的触摸处理
        RotateAnimation anim = new RotateAnimation(0, -750, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(3000);
        anim.setFillAfter(false);
        mTextView.setAnimation(anim);

        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputLayout pwd = (TextInputLayout) findViewById(R.id.pwdLayout);
                pwd.setError("password not correct!");
                pwd.getEditText().setFocusable(true);
                pwd.getEditText().setFocusableInTouchMode(true);
                pwd.getEditText().requestFocus();
            }
        });

        final TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        final ViewPager pages = ((ViewPager) findViewById(R.id.pages));
//        tabs.setupWithViewPager((ViewPager) findViewById(R.id.pages));
        tabs.addTab(tabs.newTab().setText("1"));
        tabs.addTab(tabs.newTab().setText("2"));
        tabs.addTab(tabs.newTab().setText("3"));
        tabs.addTab(tabs.newTab().setText("4"));
        tabs.addTab(tabs.newTab().setText("5"));
        pages.correlate(tabs);
//        tabs.correlate(pages);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "dispatchTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(TAG, "dispatchTouchEvent ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(TAG, "onTouchEvent ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_text_view:
                Log.e(TAG, "MyTextView onClick");
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(view.getId()) {
            case R.id.my_text_view:
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "MyTextView onTouch ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "MyTextView onTouch ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "MyTextView onTouch ACTION_UP");
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return false;
    }
}
