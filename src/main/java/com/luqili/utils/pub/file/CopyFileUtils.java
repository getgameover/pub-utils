package com.luqili.utils.pub.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.luqili.utils.pub.link.Pair;
import com.luqili.utils.pub.string.StringUtils;

/**
 * 复制文件工具类
 * @author luqili
 *
 */
public class CopyFileUtils {
	
	/**
	 * 复制文本文件,并替换其中指定的内容(每行单独替换)
	 * @param src
	 * @param out
	 * @param replaces
	 * @throws IOException 
	 */
	public static void copyText(String src,String out,List<Pair<String, String>> replaces) throws IOException {
		File f1=new File(src);
		File f2=new File(out);
		copyText(f1, f2, replaces);
	}
	/**
	 * 复制文本文件,并替换其中指定的内容(每行单独替换)
	 * @param src
	 * @param out
	 * @param replaces
	 * @throws IOException 
	 */
	public static void copyText(File src,File out,List<Pair<String, String>> replaces) throws IOException {
		if(replaces==null||replaces.isEmpty()) {
			FileUtils.copyFile(src, out);
			return;
		}
		@SuppressWarnings("unchecked")
		List<String> lines=FileUtils.readLines(src,"UTF-8");
		List<String> result = new ArrayList<>();
		for(String line:lines) {
			for(Pair<String, String> p:replaces) {
				line=StringUtils.replace(line, p.getKey(), p.getValue());
			}
			result.add(line);
		}
		FileUtils.writeLines(out, "UTF-8", result);
	}
}
