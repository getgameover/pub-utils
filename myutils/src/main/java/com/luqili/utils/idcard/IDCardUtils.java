package com.luqili.utils.idcard;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.luqili.utils.date.DateUtils;
import com.luqili.utils.district.DistrictUtils;

public class IDCardUtils {
	public static Integer maxAge = 200;// 最大年龄
	public static Pattern pattern_idcard = Pattern.compile("^\\d{17}([\\dX]{1})?$");// 验证身份证
	public static Pattern pattern_creditcode = Pattern.compile("^[0-9ABCDEFGHJKLMNPQRTUWXY]{18}$");// 验证社会信用代码
	public static Pattern pattern_orgcode = Pattern.compile("^[0-9A-Z]{8}-?[0-9A-Z]{1}$");
	public static int[] weight_idcard = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
	public static int[] weight_creditcode = { 1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28 };
	public static int[] weight_orgcode = { 3, 7, 9, 10, 5, 8, 4, 2 };
	public static char[] validValue = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
	public static char[] tables = "0123456789ABCDEFGHJKLMNPQRTUWXY".toCharArray(); // 字典表

	/**
	 * 获取新身份证的最后一位:检验位
	 * 
	 * @param code
	 *            18位身份证的前17位
	 * @return 新身份证的最后一位
	 */
	private static String getIdCardValidValue(String code) {
		int sum = 0;
		for (int i = 0; i < 17; i++) {
			sum += Integer.parseInt(StringUtils.substring(code, i, i + 1)) * weight_idcard[i];
		}
		int check = (12 - sum % 11) % 11;
		String result = Integer.toString(check);
		if (check == 10)
			result = "X";
		return result;
	}

	/**
	 * 根据身份证获得性别
	 * 
	 * @param idcode
	 * @return
	 */
	public static String getIdCardGender(String idcode) {
		if (!validIdCardCode(idcode)) {
			throw new RuntimeException("身份证号[" + idcode + "]错误");
		}
		String sexPostion = null;
		if (idcode.length() == 18) {
			sexPostion = idcode.substring(idcode.length() - 2, idcode.length() - 1);
		} else if (idcode.length() == 15) {
			sexPostion = idcode.substring(idcode.length() - 1, idcode.length());
		}
		if (sexPostion != null) {
			if (Integer.parseInt(sexPostion) % 2 == 0) {// 偶数
				return "女";
			} else {
				return "男";
			}
		} else {
			return "未知";
		}
	}

	/**
	 * 通过证件号获取生日信息
	 * 
	 * @author luqili 2016年12月19日
	 * @param code
	 * @return
	 */
	public static Date getIdCardBirthday(String code) {
		if (!pattern_idcard.matcher(code).find()) {
			throw new RuntimeException("证件号有误");
		}
		try {
			String birth = StringUtils.substring(code, 6, 14);
			Date birthDay = DateUtils.parseDate(birth, "yyyyMMdd");
			return birthDay;
		} catch (ParseException e) {
			throw new RuntimeException("证件号有误,转换日期异常");
		}
	}

	/**
	 * 验证身份证是否正确，X必须为大写 不通过抛出异常信息
	 * <li>二代身份证校验函数
	 * 
	 * @author luqili 2016年12月19日
	 * @param code
	 * @return
	 */
	public static boolean validIdCardCodeMessage(String code) {
		if (StringUtils.isEmpty(code)) {
			throw new RuntimeException("证件号不能为空");
		}
		if (!pattern_idcard.matcher(code).find()) {
			// 正则表达式不匹配
			throw new RuntimeException("证件号格式错误");
		}
		Date birthDay = getIdCardBirthday(code);
		Date now = DateUtils.truncate(new Date(), Calendar.DATE);
		if (now.getTime() - birthDay.getTime() < 0) {
			throw new RuntimeException("生日日期不能大于当前时间");
		}
		Integer age = DateUtils.differYear(now, birthDay);
		if (age > maxAge) {
			throw new RuntimeException("年龄不能大于" + maxAge + "岁");
		}
		String district = StringUtils.substring(code, 0, 6);
		if (!DistrictUtils.containsDistrict(district)) {
			throw new RuntimeException("证件号行政区划有误");
		}
		String v = getIdCardValidValue(code);
		if (!StringUtils.right(code, 1).equals(v)) {
			throw new RuntimeException("校验码错误");
		}
		return true;
	}

	/**
	 * 验证证件号是否正确
	 * <li>二代身份证校验函数
	 * 
	 * @author luqili 2016年12月19日
	 * @param code
	 * @return
	 */
	public static boolean validIdCardCode(String code) {
		try {
			return validIdCardCodeMessage(code);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 统一社会信用代码校验（必须为大写）
	 * <li>统一社会信用代码校验函数,编码规范见<法人和其他组织统一社会信用代码编码规则>(GB 32100-2015)
	 * 
	 * @author luqili 2016年12月20日
	 * @param code
	 * @return
	 */
	public static boolean vliadCreditCode(String code) {
		if (StringUtils.isBlank(code)) {
			return false;
		}
		Matcher mt = pattern_creditcode.matcher(code);
		if (!mt.find()) {
			return false;
		}
		int sum = 0;
		for (int i = 0; i < 17; i++) {
			int index = Arrays.binarySearch(tables, code.charAt(i));
			sum += index * weight_creditcode[i];
		}
		int c17 = 31 - sum % 31;
		if (c17 == 31) {
			c17 = 0;
		}
		return code.charAt(17) == tables[c17];
	}

	/**
	 * 验证是否为有效的组织机构代码（必须为大写）
	 * <li>组织结构代码校验函数,编码规范见<全国组织机构代码编制规则>(GB 11714-1997)
	 * 
	 * @author luqili 2016年12月20日
	 * @param code
	 * @return
	 */
	public static boolean validOrgCode(String code) {
		if (StringUtils.isBlank(code)) {
			return false;
		}
		Matcher mt = pattern_orgcode.matcher(code);
		if (!mt.find()) {
			return false;
		}
		int sum = 0;
		for (int i = 0; i < 8; i++) {
			int index = Arrays.binarySearch(tables, code.charAt(i));
			sum += index * weight_orgcode[i];
		}
		int c9 = 11 - sum % 11;
		String v = c9 + "";
		switch (c9) {
		case 10:
			v = "X";
			break;
		case 11:
			v = "0";
		}
		String y = StringUtils.right(code, 1);// 最后一位
		return v.equals(y);

	}
}
