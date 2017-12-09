package my.views;


import android.content.Context;
import android.util.AttributeSet;

/**
 * @author architect.bian
 * @date 2017-12-09 2:51 PM
 */

public class TextInputLayout extends android.support.design.widget.TextInputLayout {

    public TextInputLayout(Context context) {
        super(context);
    }

    public TextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}

/*
TextInputLayout pwd = (TextInputLayout) findViewById(R.id.pwdLayout);
pwd.setError("password not correct!");
pwd.getEditText().setFocusable(true);
pwd.getEditText().setFocusableInTouchMode(true);
pwd.getEditText().requestFocus();
*/