package com.luqili.utils.string;

/**
 * 字符串工具类扩展
 * @author luqili
 *
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{
	/**
	 * 字符串隐藏中间字符
	 * 	<pre>
	 * String t1=StringUtils.hideMiddleCharacter(null, 1, 2, "*", 3)="***";
	 * String t2=StringUtils.hideMiddleCharacter("a", 1, 2, "*", 3)="a***a";
	 * String t3=StringUtils.hideMiddleCharacter("ab", 1, 2, "*", 3)="a***ab";
	 * String t4=StringUtils.hideMiddleCharacter("abc", 1, 2, "*", 3)="a***bc";
	 * String t5=StringUtils.hideMiddleCharacter("abcd", 1, 2, "*", 3)="a***abc";
	 * </pre>
	 * @param src 字符串
	 * @param leftSize 左侧显示数量
	 * @param leftSize 右侧显示数量
	 * @param replace 替换字符
	 * @param showHideSize 显示隐藏字符数量
	 * @return
	 */
	public static String hideMiddleCharacter(String src,Integer leftSize,Integer rightSize,String replace,Integer showHideSize){
		StringBuffer result = new StringBuffer();
		if(StringUtils.isBlank(src)){
			src="";
		}
		if(leftSize==null||leftSize<0){
			leftSize=2;
		}
		if(rightSize==null||rightSize<0){
			rightSize=2;
		}
		if(StringUtils.isBlank(replace)){
			replace="*";
		}
		if(showHideSize==null||showHideSize<0){
			rightSize=4;
		}
		result.append(left(src, leftSize));
		result.append(repeat(replace, showHideSize));
		result.append(right(src, rightSize));
		return result.toString();
	}
}
