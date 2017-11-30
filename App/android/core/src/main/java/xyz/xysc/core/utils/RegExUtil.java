package xyz.xysc.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author architect.bian
 * @date 2017-11-30 3:00 PM
 */

public class RegExUtil {

    public static final String regEmail = "([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)";

    public static boolean isEmail(String str) {
        return Pattern.matches(regEmail, str);
    }

    /**
     * 判定str是否匹配regex
     * @param regex
     * @param str
     * @return
     * @author: Architect.bian
     * 2014-6-24 下午1:59:57
     */
    public static boolean isMatch(String regex, String str) {
        return Pattern.matches(regex, str);
    }

    /**
     * 判断str开头是否是regex
     * @param regex
     * @param str
     * @return
     * @author Architect.bian
     * @createtime 2014-12-26 下午7:53:22
     */
    public static boolean isStartWith(String regex, String str) {
        return Pattern.matches(regex + ".*", str);
    }

    /**
     * 是否是手机号
     * @param phone
     * @return
     */
    public static boolean isPhoneNum(String phone) {  //
        Pattern p = Pattern.compile("^0{0,1}(13[0-9]|15[0-9]|18[0-9])[0-9]{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}
