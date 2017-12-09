package my.views;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

/**
 * @author architect.bian
 * @date 2017-12-09 2:55 PM
 */

public class TextInput extends TextInputEditText {

    public TextInput(Context context) {
        super(context);
    }

    public TextInput(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextInput(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return super.onCreateInputConnection(outAttrs);
    }
}
