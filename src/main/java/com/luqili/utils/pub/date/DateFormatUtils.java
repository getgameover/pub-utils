package com.luqili.utils.pub.date;

import java.util.Date;

/**
 * 扩展DateFormatUtils
 * 
 * @author luqili 2016年12月12日
 *
 */
public class DateFormatUtils extends org.apache.commons.lang3.time.DateFormatUtils {
  
  /**
   * 默认格式化样式
   * <li>yyyy-MM-dd
   * 
   * @param date
   * @return
   */
  public static String formatCnStyle1(Date date) {
    String f = DateFormatUtils.format(date, "yyyy-MM-dd");
    return f;
  }
  
  /**
   * 默认格式化样式
   * <li>yyyy-MM-dd HH:mm
   * 
   * @param date
   * @return
   */
  public static String formatCnStyle2(Date date) {
    String f = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm");
    return f;
  }
  
  /**
   * 默认格式化样式
   * <li>yyyy-MM-dd HH:mm:ss
   * 
   * @param date
   * @return
   */
  public static String formatCnStyle3(Date date) {
    String f = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    return f;
  }
  /**
   * 默认格式化样式
   * <li>yyyy年MM月dd日 HH时mm分
   * 
   * @param date
   * @return
   */
  public static String formatCnStyle4(Date date) {
    String f = DateFormatUtils.format(date, "yyyy年MM月dd日 HH时mm分");
    return f;
  }
}
