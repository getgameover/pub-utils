package com.luqili.utils.pub.district;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 国内行政区划工具类
 * @author luqili 2016年12月19日
 *
 */
public class DistrictUtils {
	private static List<Integer> districts = new ArrayList<>();
	private static List<String> districtNames = new ArrayList<>();
	static {
		try (
				InputStream in=DistrictUtils.class.getResourceAsStream("district.data");
				InputStreamReader r = new InputStreamReader(in,"utf-8");
			){
			BufferedReader reader = new BufferedReader(r);
			String line=null;
			while((line=reader.readLine())!=null){
				String[] datas=StringUtils.split(line, ":");
				if(datas.length>1){
					districts.add(Integer.parseInt(datas[0]));
					districtNames.add(StringUtils.trim(datas[1]));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("加载行政区划资源失败："+e.getMessage());
		}
	}
	/**
	 * 是否包含该行政区划
	 *@author luqili 2016年12月19日
	 * @param distirct
	 * @return
	 */
	public static boolean containsDistrict(String distirct){
		Integer dis=Integer.parseInt(distirct);
		return districts.contains(dis);
	}
	/**
	 * 是否包含该行政区划
	 *@author luqili 2016年12月19日
	 * @param district
	 * @return
	 */
	public static boolean containsDistrict(Integer district){
		return districts.contains(district);
	}
	/**
	 * 根据行政区划，返回名称，不含有的返回 ""
	 *@author luqili 2016年12月19日
	 * @param district
	 * @return
	 */
	public static String getDistrictName(Integer district){
		district=completeDistrict(district);
		if(district==0){
			return "";
		}
		int index=districts.indexOf(district);
		if(index==-1){
			return "";
		}
		return districtNames.get(index);
	}
	/**
	 * 获得全称
	 *@author luqili 2016年12月19日
	 * @param district
	 * @return
	 */
	public static String getDistrictFullName(Integer district){
		district=completeDistrict(district);
		if(district==0){
			return "";
		}
		StringBuffer fullName=new StringBuffer();
		String province=getDistrictName(district/10000);
		fullName.append(province);
		if(district%10000!=0){
			String city=getDistrictName(district/100);
			fullName.append(city);
		}
		if(district%100!=0){
			String county=getDistrictName(district);
			fullName.append(county);
		}
		return fullName.toString();
		
	}
	/**
	 * 
	 *@author luqili 2016年12月19日
	 * @param district
	 * @return
	 */
	private static Integer completeDistrict(Integer district){
		if(district==null||district<0){
			return 0;
		}else if(district<100){
			district*=10000;
		}else if(district<10000){
			district*=100;
		}else if(district>1000000){
			return 0;
		}
		return district;
	}
	public static List<Integer> getDistricts() {
		return districts;
	}
	public static List<String> getDistrictNames() {
		return districtNames;
	}
	
}
