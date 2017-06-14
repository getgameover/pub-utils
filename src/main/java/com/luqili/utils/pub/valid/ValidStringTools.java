package com.luqili.utils.pub.valid;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.luqili.utils.pub.idcard.IDCardUtils;

/**
 * 正则表达式验证字符串
 * 
 * @author luqili
 *
 */
public class ValidStringTools {
    public static final String PATTERN_VALID_PHONE = "^1\\d{10}$";
    public static final String PATTERN_VALID_QQ = "^\\d{5,13}$";
    public static final String PATTERN_VALID_EMAIL = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    public static final String PATTERN_VALID_CHINESE = "^[\u4e00-\u9fa5]+$";

    public static boolean validPattern(String content, String pattern) {
        return Pattern.matches(pattern, content);
    }

    /**
     * 验证手机号是否符合规范
     * 
     * @param phone
     * @return
     */
    public static boolean validPhone(String phone) {
        return validPattern(phone, PATTERN_VALID_PHONE);
    }

    /**
     * 验证身份证号是否符合规范
     * <p>
     * 18位身份证号,符合校验规则(生日,年龄,行政区划,校验码)
     * </p>
     * 
     * @param idcard
     * @return
     */
    public static boolean validIdCard(String idcard) {
        return IDCardUtils.validIdCardCode(idcard);
    }

    /**
     * 验证QQ号码是否符合规则
     * <p>
     * 5-13位的纯数字编号
     * </p>
     * 
     * @param qq
     * @return
     */
    public static boolean validQQ(String qq) {
        return validPattern(qq, PATTERN_VALID_QQ);
    }

    /**
     * 验证E-mail是否符合规则
     * <p>
     * E-mail长度不能超过64位
     * </p>
     * 
     * @param email
     * @return
     */
    public static boolean validEmail(String email) {
        if (StringUtils.isNotBlank(email)) {
            if (email.length() > 64) {
                return false;
            }
        } else {
            return false;
        }
        return validPattern(email, PATTERN_VALID_EMAIL);
    }

    /**
     * 验证是否全部为中文
     * 
     * <pre>
     * ValidStringTools.validChinese("") = false
     * ValidStringTools.validChinese("有标点符号。") = false
     * ValidStringTools.validChinese("这是中文") = true
     * ValidStringTools.validChinese("繁体字龍") = true
     * ValidStringTools.validChinese("偏僻字嵾寤")		= true
     * </pre>
     * 
     * @param ch
     * @return
     */
    public static boolean validChinese(String ch) {
        return validPattern(ch, PATTERN_VALID_CHINESE);
    }
}
