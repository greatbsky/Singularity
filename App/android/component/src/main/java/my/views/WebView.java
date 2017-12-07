package my.views;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebViewClient;

/**
 * @author architect.bian
 * @date 2017-12-07 11:57 AM
 */

public class WebView extends android.webkit.WebView {

    public WebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.getSettings().setJavaScriptEnabled(true);
        this.setWebViewClient(new WebViewClient());
    }

}
