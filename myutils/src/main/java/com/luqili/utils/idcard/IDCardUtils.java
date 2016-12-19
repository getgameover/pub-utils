package com.luqili.utils.idcard;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.luqili.utils.date.DateUtils;
import com.luqili.utils.district.DistrictUtils;

public class IDCardUtils {

	/**
	1.将前面的身份证号码17位数分别乘以不同的系数。 从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
	2.将这17位数字和系数相乘的结果相加。
	3.用加出来和除以11，看余数是多少？
	4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。 其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。
	5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。 如果余数是10，身份证的最后一位号码就是2。
	 */
	
	public static Pattern validPattern = Pattern.compile("^\\d{17}([\\dX]{1})?$");//使用打正则表达式
	public static Integer maxAge=200;//最大年龄
	public static int[] modeValue = new int[17];
	public static char[] validValue={'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
	
	static{//初始化
		for(int i=0;i<modeValue.length;i++){
			modeValue[i]=(1<< (modeValue.length - i))%11;
		}
	}
	
	/**
	 * 获取新身份证的最后一位:检验位
	 * 
	 * @param code
	 *            18位身份证的前17位
	 * @return 新身份证的最后一位
	 */
	private static char getValidValue(String code) {
		char[] cs=code.toCharArray();
		if(cs.length!=17){
			throw new RuntimeException("获取校验码长度异常");
		}
		int sum=0;
		for(int i=0;i<cs.length;i++){
			sum+=modeValue[i]*Integer.parseInt(Character.toString(cs[i]));
		}
		int y=sum%11;
		return validValue[y-1];
	}

	/**
	 * 根据身份证获得性别
	 * @param idcode
	 * @return
	 */
	public static String getGender(String idcode){
		if(!validIdCardCode(idcode)){
			throw new RuntimeException("身份证号["+idcode+"]错误");
		}
		String sexPostion = null;
		if(idcode.length()==18){
			 sexPostion = idcode.substring(idcode.length()-2, idcode.length()-1);
		} else if(idcode.length()==15){
			 sexPostion = idcode.substring(idcode.length()-1, idcode.length());
		}
		if(sexPostion!=null){
			if(Integer.parseInt(sexPostion)%2==0){//偶数
				return "女";
			} else{
				return "男";
			}
		}else{
			return "未知";
		}
	}
	/**
	 * 通过证件号获取生日信息
	 *@author luqili 2016年12月19日
	 * @param code
	 * @return
	 */
	public static Date getBirthday(String code){
		if(!validPattern.matcher(code).find()){
			throw new RuntimeException("证件号有误");
		}
		try {
			String birth=StringUtils.substring(code, 6, 14);
			Date birthDay =DateUtils.parseDate(birth,"yyyyMMdd");
			return birthDay;
		} catch (ParseException e) {
			throw new RuntimeException("证件号有误,转换日期异常");
		}
	}
	/**
	 * 验证身份证是否正确，X必须为大写 不通过抛出异常信息
	 *@author luqili 2016年12月19日
	 * @param code
	 * @return
	 */
	public static boolean validIdCardCodeMessage(String code) {
		if (StringUtils.isEmpty(code)) {
			throw new RuntimeException("证件号不能为空");
		}
		if(!validPattern.matcher(code).find()){
			//正则表达式不匹配
			throw new RuntimeException("证件号格式错误");
		}
		Date birthDay=getBirthday(code);
		Date now =DateUtils.truncate(new Date(), Calendar.DATE);
		if(now.getTime()-birthDay.getTime()<0){
			throw new RuntimeException("生日日期不能大于当前时间");
		}
		Integer age=DateUtils.differYear(now, birthDay);
		if(age>maxAge){
			throw new RuntimeException("年龄不能大于"+maxAge+"岁");
		}
		String district=StringUtils.substring(code, 0,6);
		if(!DistrictUtils.containsDistrict(district)){
			throw new RuntimeException("证件号行政区划有误");
		}
		char v=getValidValue(StringUtils.left(code, 17));
		if(!StringUtils.right(code, 1).equals(Character.toString(v))){
			throw new RuntimeException("校验码错误");
		}
		return true;
	}
	/**
	 * 验证证件号是否正确
	 *@author luqili 2016年12月19日
	 * @param code
	 * @return
	 */
	public static boolean validIdCardCode(String code){
		try{
			return validIdCardCodeMessage(code);
		}catch (Exception e) {
			return false;
		}
	}
}
