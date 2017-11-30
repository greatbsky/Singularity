package xyz.xysc.core.utils;

import android.util.Base64;

import com.google.common.base.Strings;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author architect.bian
 * @date 2017-11-30 1:08 PM
 */

public class StrUtil {

    /**
     * 随机获取字符串
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String getRandomString(int length) {
        if (length <= 0) {
            return "";
        }
        Random random = new Random();
        char[] randomChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd',
                'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm' };
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(randomChar[Math.abs(random.nextInt()) % randomChar.length]);
        }
        return stringBuffer.toString();
    }


    /**
     * 判断str是否匹配regexStrs中任一一个正则
     *
     * @param str
     * @param regexStrs
     * @return
     */
    public static boolean isMatchAny(String str, String... regexStrs) {
        for (String regex : regexStrs) {
            if (Pattern.matches(regex, str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除开始结束两头的特定字符
     *
     * @param str     原字符串
     * @param remover 要删除的字符
     * @return 删除后的字符串
     */
    public static String trim(String str, String remover) {
        return removeEnd(removeStart(str, remover), remover);
    }

    /**
     * <pre>
     * StringUtils.removeEnd(null, *)      = null
     * StringUtils.removeEnd("", *)        = ""
     * StringUtils.removeEnd(*, null)      = *
     * StringUtils.removeEnd("www.domain.com", ".com.")  = "www.domain.com"
     * StringUtils.removeEnd("www.domain.com", ".com")   = "www.domain"
     * StringUtils.removeEnd("www.domain.com", "domain") = "www.domain.com"
     * StringUtils.removeEnd("abc", "")    = "abc"
     * </pre>
     *
     */
    public static String removeEnd(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.endsWith(remove)) {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }
    /**
     * <pre>
     * removeStart(null, *)      = null
     * removeStart("", *)        = ""
     * removeStart(*, null)      = *
     * removeStart("www.domain.com", "www.")   = "domain.com"
     * removeStart("domain.com", "www.")       = "domain.com"
     * removeStart("www.domain.com", "domain") = "www.domain.com"
     * removeStart("abc", "")    = "abc"
     * </pre>
     */
    public static String removeStart(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.startsWith(remove)){
            return str.substring(remove.length());
        }
        return str;
    }

    /**
     * 判断是否数字，是否整数
     *
     * @param cs
     * @return
     */
    public static boolean isNumeric(CharSequence cs) {
        if (cs == null || cs.length() == 0) {
            return false;
        }
        int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(cs.charAt(i)) == false) {
                if (i != 0 || cs.charAt(i) != '-') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判断是否数字，是否整数
     *
     * @param obj
     * @return
     */
    public static boolean isNumeric(Object obj) {
        if (obj == null) {
            return false;
        }
        return isNumeric(obj.toString());
    }

    /**
     * @param text
     * @param contains
     * @return
     */
    public static boolean isContainAny(String text, String... contains) {
        for (String str : contains) {
            if (text.contains(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否不为空白字符串
     *
     * @param strobj
     * @return
     */
    public static boolean isNotEmpty(Object strobj) {
        return !isEmpty(strobj);
    }

    /**
     * 判断是否为null或空白字符串
     *
     * @param strobj
     * @return
     */
    public static boolean isEmpty(Object strobj) {
        if (strobj != null) {
            return Strings.isNullOrEmpty(strobj.toString().trim());
        }
        return true;
    }

    /**
     * 去除html标签
     * @author liubin
     * @date 2017年9月6日 下午5:11:07
     * @return StringBuffer
     */
    public static StringBuffer clearHtmlTag(String source) {
        StringBuffer filteredBuffer = new StringBuffer();
        for (int i = 0, k=0; i < source.length(); i++) {
            String c = String.valueOf(source.charAt(i));
            if (source.charAt(i) == '<') {
                for (k = i + 1; k < source.length(); k++) {
                    if (source.charAt(k) == '<') {
                        k = i;
                        filteredBuffer.append(c);
                        break;
                    }
                    if (source.charAt(k) == '>') {
                        if (k == i + 1) {
                            filteredBuffer.append(c);
                            filteredBuffer.append(source.charAt(k));
                        }
                        break;
                    }
                }
                i = k;
            } else {
                filteredBuffer.append(c);
            }
        }
        return filteredBuffer;
    }

    /**
     * @param name
     * @return
     */
    public static String encode(String name) {
        try {
            return URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 删除空白
     *
     * @param str
     * @return
     */
    public static String removeSpace(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("(?i)\\s", "");
    }

    /**
     * 返回某个编码的字符串
     *
     * @param str
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String get(String str, String charset) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return new String(str.getBytes(), charset);
    }

    /**
     * 返回某个编码的字符串
     *
     * @param str
     * @param charsetfrom
     * @param charsetto
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String get(String str, String charsetfrom, String charsetto) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return new String(str.getBytes(charsetfrom), charsetto);
    }

    /**
     * 格式化整型
     *
     * @param format
     * @param num
     * @return
     */
    public static String format(String format, long num) {
        return new DecimalFormat(format).format(num);
    }

    /**
     * 格式化Double类型<br />
     * 例如：0.00 0.10<br />
     * #.## 0.1
     *
     * @param format
     * @param d
     * @return
     */
    public static String format(String format, double d) {
        return new DecimalFormat(format).format(d);
    }

    /**
     *
     */
    public static String base64Enc(byte[] binaryData) {
        return Base64.encodeToString(binaryData, Base64.DEFAULT);
    }

    /**
     * 将第一个字母转成大写
     *
     * @return
     * @author Architect.bian
     * @createtime 2014-7-1 下午4:05:11
     */
    public static String capitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        final int firstCodepoint = str.codePointAt(0);
        final int newCodePoint = Character.toTitleCase(firstCodepoint);
        if (firstCodepoint == newCodePoint) {
            // already capitalized
            return str;
        }

        final int newCodePoints[] = new int[strLen]; // cannot be longer than the char array
        int outOffset = 0;
        newCodePoints[outOffset++] = newCodePoint; // copy the first codepoint
        for (int inOffset = Character.charCount(firstCodepoint); inOffset < strLen; ) {
            final int codepoint = str.codePointAt(inOffset);
            newCodePoints[outOffset++] = codepoint; // copy the remaining ones
            inOffset += Character.charCount(codepoint);
        }
        return new String(newCodePoints, 0, outOffset);
    }

    /**
     * 将第一个字母转成小写
     *
     * @return
     * @author Architect.bian
     * @createtime 2014-7-1 下午4:05:11
     */
    public static String uncapitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        final int firstCodepoint = str.codePointAt(0);
        final int newCodePoint = Character.toLowerCase(firstCodepoint);
        if (firstCodepoint == newCodePoint) {
            // already capitalized
            return str;
        }

        final int newCodePoints[] = new int[strLen]; // cannot be longer than the char array
        int outOffset = 0;
        newCodePoints[outOffset++] = newCodePoint; // copy the first codepoint
        for (int inOffset = Character.charCount(firstCodepoint); inOffset < strLen; ) {
            final int codepoint = str.codePointAt(inOffset);
            newCodePoints[outOffset++] = codepoint; // copy the remaining ones
            inOffset += Character.charCount(codepoint);
        }
        return new String(newCodePoints, 0, outOffset);
    }


    public static String join(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (String str : arr) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * str是否以prefix开头
     *
     * @param str
     * @param prefix
     * @return
     */
    public static boolean startsWith(final CharSequence str, final CharSequence prefix) {
        return startsWith(str, prefix, false);
    }

    /**
     * <p>Case insensitive check if a CharSequence starts with a specified prefix.</p>
     *
     * <p>{@code null}s are handled without exceptions. Two {@code null}
     * references are considered to be equal. The comparison is case insensitive.</p>
     *
     * <pre>
     * StringUtils.startsWithIgnoreCase(null, null)      = true
     * StringUtils.startsWithIgnoreCase(null, "abc")     = false
     * StringUtils.startsWithIgnoreCase("abcdef", null)  = false
     * StringUtils.startsWithIgnoreCase("abcdef", "abc") = true
     * StringUtils.startsWithIgnoreCase("ABCDEF", "abc") = true
     * </pre>
     */
    public static boolean startsWithIgnoreCase(final CharSequence str, final CharSequence prefix) {
        return startsWith(str, prefix, true);
    }

    /**
     * <p>Check if a CharSequence starts with a specified prefix (optionally case insensitive).</p>
     */
    private static boolean startsWith(final CharSequence str, final CharSequence prefix, final boolean ignoreCase) {
        if (str == null || prefix == null) {
            return str == null && prefix == null;
        }
        if (prefix.length() > str.length()) {
            return false;
        }
        return regionMatches(str, ignoreCase, 0, prefix, 0, prefix.length());
    }


    /**
     * <p>Check if a CharSequence ends with a specified suffix.</p>
     *
     * <p>{@code null}s are handled without exceptions. Two {@code null}
     * references are considered to be equal. The comparison is case sensitive.</p>
     *
     * <pre>
     * StringUtils.endsWith(null, null)      = true
     * StringUtils.endsWith(null, "def")     = false
     * StringUtils.endsWith("abcdef", null)  = false
     * StringUtils.endsWith("abcdef", "def") = true
     * StringUtils.endsWith("ABCDEF", "def") = false
     * StringUtils.endsWith("ABCDEF", "cde") = false
     * StringUtils.endsWith("ABCDEF", "")    = true
     * </pre>
     */
    public static boolean endsWith(final CharSequence str, final CharSequence suffix) {
        return endsWith(str, suffix, false);
    }

    /**
     * <p>Case insensitive check if a CharSequence ends with a specified suffix.</p>
     *
     * <p>{@code null}s are handled without exceptions. Two {@code null}
     * references are considered to be equal. The comparison is case insensitive.</p>
     *
     * <pre>
     * StringUtils.endsWithIgnoreCase(null, null)      = true
     * StringUtils.endsWithIgnoreCase(null, "def")     = false
     * StringUtils.endsWithIgnoreCase("abcdef", null)  = false
     * StringUtils.endsWithIgnoreCase("abcdef", "def") = true
     * StringUtils.endsWithIgnoreCase("ABCDEF", "def") = true
     * StringUtils.endsWithIgnoreCase("ABCDEF", "cde") = false
     * </pre>
     */
    public static boolean endsWithIgnoreCase(final CharSequence str, final CharSequence suffix) {
        return endsWith(str, suffix, true);
    }

    /**
     * <p>Check if a CharSequence ends with a specified suffix (optionally case insensitive).</p>
     */
    private static boolean endsWith(final CharSequence str, final CharSequence suffix, final boolean ignoreCase) {
        if (str == null || suffix == null) {
            return str == null && suffix == null;
        }
        if (suffix.length() > str.length()) {
            return false;
        }
        final int strOffset = str.length() - suffix.length();
        return regionMatches(str, ignoreCase, strOffset, suffix, 0, suffix.length());
    }

    static boolean regionMatches(final CharSequence cs, final boolean ignoreCase, final int thisStart,
                                 final CharSequence substring, final int start, final int length)    {
        if (cs instanceof String && substring instanceof String) {
            return ((String) cs).regionMatches(ignoreCase, thisStart, (String) substring, start, length);
        }
        int index1 = thisStart;
        int index2 = start;
        int tmpLen = length;

        // Extract these first so we detect NPEs the same as the java.lang.String version
        final int srcLen = cs.length() - thisStart;
        final int otherLen = substring.length() - start;

        // Check for invalid parameters
        if (thisStart < 0 || start < 0 || length < 0) {
            return false;
        }

        // Check that the regions are long enough
        if (srcLen < length || otherLen < length) {
            return false;
        }

        while (tmpLen-- > 0) {
            final char c1 = cs.charAt(index1++);
            final char c2 = substring.charAt(index2++);

            if (c1 == c2) {
                continue;
            }

            if (!ignoreCase) {
                return false;
            }

            // The same check as in String.regionMatches():
            if (Character.toUpperCase(c1) != Character.toUpperCase(c2)
                    && Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
                return false;
            }
        }

        return true;
    }
}