package cn.com.jandar.kit;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressWarnings("unchecked")
public class StringUtil {

	/**
	 * 把String字符串数组转化成一个字符串，中间用特定符号分隔
	 * @param String[] str 字符串数组
	 * @param String symbol 符号：如：","、";"等等
	 * @return String string 转化好后的字符串
	 */
	public static String array2String(String[] str,String symbol) {

		String string = "";

		for(int i=0;i<str.length;i++,string += symbol)
		{
			string += String.valueOf(str[i]);
		}
		if(!"".equals(string))
		{
			if(symbol.equals(string.substring(string.length()-1)))
			{
				string = string.substring(0, string.length()-1);
			}
		}
		return string;
	}

	/**
	 * 判断一个字符串是否以某个字符打头和结尾，若没有，这加上检查字符串
	 * @param str:输入字符串；checkStr:检查字符串
	 */
	public static String fillStringBeginAndEnd(String str,String checkStr)
	{
		String str1;
		if(str.indexOf(checkStr)==0)
		{
			if(str.endsWith(checkStr))
			{
				str1 = str;
			}
			else
			{
				str1 = str+checkStr;
			}
		}
		else
		{
			if(str.endsWith(checkStr))
			{
				str1 = checkStr+str;
			}
			else
			{
				str1 = checkStr+str+checkStr;
			}
		}
		return str1;
	}

	public static boolean isEmptyString(String eStr) {

		if (eStr == null || "".equals(eStr))
			return true;
		return false;
	}

	public static String replaceDian(String content) {
		String str = "([\\\\/][^/\\.\\\\]*[\\\\/]\\.\\.)";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(content);
		if (m.find()) {
			content=content.replace(m.group(1), "");
			return replaceDian(content);
		}else{
			return content;
		}
	}

	/*
	 * 去除字符串中的特殊字符
	 * str:需要处理的字符串 ， s:需要去除的字符串。 空格"\\s*", 回车"\\n" ,换行"\\r",水平制表符"\\t"
	 *                                       去除多个"\\s*|\t|\r|\n" 
	 */
	public static  String  replaceStr(String str, String s)
	{
		Pattern p = Pattern.compile(s);
		Matcher m = p.matcher(str);
		String after = m.replaceAll(""); 
		return after;
	}

	/**
	 * 将字符串以逗号分隔转换为字符串数组
	 * 
	 * @param str
	 * @return
	 */
	public static String[] tranStringToArrayByComma(String str) {

		if (isEmptyString(str))
			return new String[0];

		//去掉首尾的',' 并将多个','改为单个','
		str = trimAndReplace(str);
		return str.split(",");
	}

	/**
	 * 将字符串型数组转换成含Integer型的List
	 * 
	 * @param array
	 * @return
	 */
	public static List<Integer> tranStringToIntegerOfArray(String[] array) {
		List<Integer> integerList = new LinkedList<Integer>();
		for (String str : array) {
			integerList.add(Integer.parseInt(str));
		}
		return integerList;
	}

	/**
	 * 追加标签内容在结束处
	 * 作者：徐翔
	 * 2010-01-16
	 * 修订0
	 * @param content 原内容
	 * @param startWith 开始标记
	 * @param endWith	结束标记
	 * @param replaceContent 被替换的内容
	 * @param autoEnterRow 是否自动回车换行
	 * @return 结果
	 */
	public static String matchTagAppend(String content,String startWith,String endWith,String replaceContent,boolean autoEnterRow) {
		String rn="";
		if (autoEnterRow)
			rn="\r\n	";
		String i_startWith=startWith.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
		String i_endWith=endWith.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
		String s = i_startWith+"[\\s\\S]*"+i_endWith;
		Pattern p = Pattern.compile(s);
		Matcher matcher = p.matcher(content);
		if(matcher.find())
		{
			String sg=content.substring(matcher.start(),matcher.end());
			sg=sg.replace(endWith, replaceContent+ rn+endWith);
			return content.substring(0,matcher.start())+sg+content.substring(matcher.end());
		}
		return content;
	}

	/**
	 * 追加标签内容在开始处
	 * 作者：徐翔
	 * 2010-01-16
	 * 修订0
	 * @param content 原内容
	 * @param startWith 开始标记
	 * @param endWith	结束标记
	 * @param replaceContent 被替换的内容
	 * @param autoEnterRow 是否自动回车换行
	 * @return 结果
	 */
	public static String matchTagBefore(String content,String startWith,String endWith,String replaceContent,boolean autoEnterRow) {
		String rn="";
		if (autoEnterRow)
			rn="\r\n	";
		String i_startWith=startWith.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
		String i_endWith=endWith.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
		String s = i_startWith+"[\\s\\S]*"+i_endWith;
		Pattern p = Pattern.compile(s);
		Matcher matcher = p.matcher(content);
		if(matcher.find())
		{
			String sg=content.substring(matcher.start(),matcher.end());
			sg=sg.replace(startWith, startWith+replaceContent+ rn);
			return content.substring(0,matcher.start())+sg+content.substring(matcher.end());
		}
		return content;
	}

	/**
	 * 替换标签内容
	 * 作者：徐翔
	 * 2010-01-16
	 * 修订0
	 * @param content 原内容
	 * @param startWith 开始标记
	 * @param endWith	结束标记
	 * @param replaceContent 被替换的内容
	 * @param autoEnterRow 是否自动回车换行
	 * @return 结果
	 */
	public static String matchTagInner(String content,String startWith,String endWith,String replaceContent,boolean autoEnterRow) {
		String rn="";
		if (autoEnterRow)
			rn="\r\n	";
		String i_startWith=startWith.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
		String i_endWith=endWith.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
		String s = i_startWith+"[\\s\\S]*"+i_endWith;
		Pattern p = Pattern.compile(s);
		Matcher matcher = p.matcher(content);
		return matcher.replaceAll(rn+startWith + replaceContent+ rn+endWith);

	}

	/**
	 * 替换标签内容及本身
	 * 作者：徐翔
	 * 2010-01-16
	 * 修订0
	 * @param content 原内容
	 * @param startWith 开始标记
	 * @param endWith	结束标记
	 * @param replaceContent 被替换的内容
	 * @return 结果
	 */
	public static String matchTagOuter(String content,String startWith,String endWith,String replaceContent) {

		String i_startWith=startWith.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
		String i_endWith=endWith.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
		String s = i_startWith+"[\\s\\S]*"+i_endWith;
		Pattern p = Pattern.compile(s);
		Matcher matcher = p.matcher(content);
		return matcher.replaceAll( replaceContent);
	}

	// 去除页面中的标签
	public static String clearTags(String content) {
		// 去掉尖括号之中的内容
		String result = content.replaceAll("<[^>]*>", "");
		// 替换空格符
		result = result.replaceAll("&nbsp;", " ");
		// 替换多个空格符为一个空格
		result = result.replaceAll("\\s+", " ");
		return result;
	}

	// 去字符串空格回车函数
	public static String replaceBlank(String str) {
		if (str == null)
			return "";
		Pattern p = Pattern.compile("\t|\r|\n|\\s");
		Matcher m = p.matcher(str);
		return m.replaceAll("");
	}

	/**
	 * 根据replaceMap中的替换内容,去替换replaceStr中的内容.
	 * 
	 * @param replaceStr
	 *            被替换内容
	 * @param replaceMap
	 *            key:原内容 value:新内容
	 * @return String
	 */
	public static String replaceByMap(String replaceStr,
			Map<String, String> replaceMap) {
		for (Iterator<Map.Entry<String, String>> it = replaceMap.entrySet()
				.iterator(); it.hasNext();) {
			Map.Entry<String, String> entry = it
			.next();
			replaceStr = replaceStr.replace(entry.getKey().toString(), entry
					.getValue().toString());
		}
		return replaceStr;
	}

	/*
	 * 按指定长度，省略字符串部分字符 @param String 字符串 @param length 保留字符串长度 @return 省略后的字符串
	 */
	public static String interLength(String string, int length) {
		StringBuffer sb = new StringBuffer();
		length=length*2;
		if (StringUtil.byteLength(string) > length) {
			int count = 0;
			for (int ii = 0; ii < string.length(); ii++) {
				char temp = string.charAt(ii);
				if (Integer.toHexString(temp).length() == 4) {
					count += 2;
				} else {
					count++;
				}
				if (count < length) {
					sb.append(temp);
				}
				if (count == length) {
					sb.append(temp);
					break;
				}
				if (count > length) {
					sb.append(" ");
					break;
				}
			}
		} else 
		{
			sb.append(string);
		}
		return sb.toString();
	}

	/*
	 * 计算字符串的字节长度(字母数字计1，汉字及标点计2)
	 * 
	 */
	public static int byteLength(String string) {
		int count = 0;
		for (int ii = 0; ii < string.length(); ii++) {
			if (Integer.toHexString(string.charAt(ii)).length() == 4) {
				count += 2;
			} else {
				count++;
			}
		}
		return count;
	}

	/**
	 * 判断一个字符串是否以某个字符打头和结尾
	 * @param str:输入字符串；checkStr:检查字符串
	 */
	public static boolean checkStringBeginAndEnd(String str,String checkStr)
	{
		if(str.indexOf(checkStr)==0)
		{
			if(str.endsWith(checkStr))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}



	/**
	 * 把一个字符串按特定的字符切分，返回字符List
	 * @param String str:需要切分的字符串
	 * @param String str:分隔符号
	 */
	public static List<String> cutString(String str,String model)
	{
		List<String> strList = new ArrayList<String>();
		Pattern p = Pattern.compile(model);
		String[] ss = p.split(str);
		for(String i:ss)
		{
			strList.add(i);
		}
		return strList;
	}

	/**
	 * 去掉HTML
	 * 
	 * @param eValue
	 * @return
	 */
	public static String deleteHtml(String eValue) {
		String result = eValue;
		while (result.indexOf("<") != -1) {
			int bPos = result.indexOf("<");
			int ePos = result.indexOf(">");
			result = result.substring(0, bPos) + result.substring(ePos + 1);

		}
		return result;
	}

	// 根据属性名取属性值
	public static String getValue(String stag, String Pname) {
		if(stag.indexOf(Pname)== -1)
		{
			return "";
		}
		if(stag.indexOf(Pname+"\"")!= -1)
		{
			return "";
		}
		else
		{
			String str1 = "";
			int start = stag.indexOf(Pname) + Pname.length();
			int end = stag.indexOf("\"", start+1);
			str1 = stag.substring(start, end);
			return str1;
		}
	}

	public static String getSpace(int depth) {
		String resultSpace="";
		for(int i=0;i<depth;i++)
		{
			resultSpace+="&nbsp;&nbsp;";
		}
		return resultSpace;
	}

	/**
	 * 统计特定字符(character) 在内容字符串(content)中出现的次数
	 * @param content    内容字符串
	 * @param character  特定字符
	 * @return count     
	 */
	public static int countChar(String content, String character)
	{
		int count = 0;
		Pattern p = Pattern.compile(character);
		Matcher m = p.matcher(content);
		while(m.find())
		{
			count++;
		}
		return count;
	}

	/**
	 * 使用symbol分割得到数组长度
	 * @param content
	 * @param symbol
	 * @return
	 */
	public static int countString(String content, String symbol){
		int count=0;

		String[] arr=content.split("\\"+symbol);
		for (int i = 0; i < arr.length; i++) {
			if("".equals(arr[i]))
				continue;
			count++;
		}

		return count;
	}

	/**
	 * 将一个[,3,,4,,5,,,,6,]的串 变成 [3,4,5,6]
	 * @param s
	 * @return
	 */
	public static String formatComma(String s)
	{
		if(s==null)
			return "";
		return StringUtil.trimComma(s.replaceAll(",+", ",")) ;
	}

	/**
	 * 给用逗号分隔的字符串加上单引号
	 * @param str
	 * @return
	 */
//	public static String joinDanYinHao(String str)
//	{
//	String strNew = "";

//	strNew = "'"+str+"'";

//	strNew = strNew.replace(",", "','");

//	return strNew;
//	}

	/**
	 * 给用逗号分隔的字符串加上单引号
	 * @param str
	 * @return
	 */
	public static String joinDanYinHao(String str)
	{
		String strNew = "";

		strNew = "'"+str+"'";

		strNew = strNew.replace(",", "','");

		return strNew;
	}

	/**
	 * 去掉首位的逗号以及把字符串中的",,"改为","
	 */
	public static String trimAndReplace(String str)
	{
		String strNew = "";

		Pattern p = Pattern.compile(",+");
		Matcher m = p.matcher(str);
		strNew = m.replaceAll(",");
		strNew = StringUtil.trimComma(strNew);

		return strNew;
	}

	/**
	 * 加上首尾逗号，并将',,'改为',',多个相邻逗号改为单个逗号
	 * @param str
	 * @return
	 */
	public static String addAndReplaceComma(String str){

		String newStr = "";

		newStr = StringUtil.trimAndReplace(str);

		newStr = "," + newStr + ",";

		return newStr;
	}

	/**
	 * 把字符串中的",,"改为","
	 * @param str
	 * @return
	 */
	public static String replaceComma(String str)
	{
		String strNew = "";

		strNew = str.replace(",,", ",");

		return strNew;
	}

	/**
	 * 将一个[,3,,4,,5,,,,6,]的串 变成 [3,,4,,5,,,,6]
	 * 去除字符串首尾的逗号
	 * @param str
	 * @return
	 */
	public static String trimComma(String str)
	{
		str = DbUtil.readDbString(str);
		if(!DbUtil.readDbString(str).equals(""))
		{
			if(",".equals(str.substring(0, 1)))
			{
				str = str.substring(1);
			}
			if(str.length()>0 && ",".equals(str.substring(str.length()-1)))
			{
				str = str.substring(0, str.length()-1);
			}
		}
		return str;
	}
	/**
	 * 将一个[E:/workspace//x2\\PublicServiceManagementSystem/WebRoot/]
	 * 变成 [E:\workspace\x2\PublicServiceManagementSystem\WebRoot\]
	 * 格式化地址的/
	 * @param str
	 * @return
	 */
	public static String trimGangForDisk(String str)
	{
		String sprate = System.getProperty("file.separator");
		if("/".equals(sprate))
		{
			return replaceDian(str).replaceAll("/+", "\\\\").replaceAll("(\\\\)+","/");

		}else
		{
			return replaceDian(str).replaceAll("/+", "\\\\").replaceAll("\\\\+", "\\\\");
		}

	}

	/**
	 * 将一个[E:/workspace//x2\\PublicServiceManagementSystem/WebRoot/]
	 * 变成 [E:/workspace/x2/PublicServiceManagementSystem/WebRoot/]
	 * 格式化地址的/
	 * @param str
	 * @return
	 */
	public static String trimGangForWeb(String str)
	{
		return replaceDian(str).replaceAll("\\\\+", "/").replaceAll("/+", "/");
	}

	public static String replaceEnter(String replaceAll) {
		return null;
	}

	/**
	 * 正则匹配：将str中匹配oldChar的转化为newChar 
	 * @param str
	 * @param oldChar
	 * @param newChar
	 * @return
	 */
	public static String strReplaceAll(String str, String oldChar, String newChar){

		String newStr = "";

		Pattern p = Pattern.compile(oldChar);
		Matcher m = p.matcher(str);
		newStr = m.replaceAll(newChar);

		return newStr;
	}

	/**
	 * 传入一个长度 得到一个String:由数字组成的字符串
	 * @param length
	 * @return
	 */
	public static String RandomStr(int length){

		String retStr = "";
		Random random = new Random();
		for(int i=0; i<length; i++)
			retStr += random.nextInt(9);
		return retStr;
	}

	/**
	 * 传入一个map 判断key是否存在:存在且非空则返回值;否则返回"";
	 * @param config
	 * @param key
	 * @return
	 */
	public static String readMapString(Map config, String key){

		String retStr="";

		if(config.containsKey(key)){

			if(!"".equals(DbUtil.readDbString(config.get(key))))
				retStr = DbUtil.readDbString(config.get(key));
		}

		return retStr;
	}

	/**
	 * 得到路径的深度  用/分割
	 * @param path
	 * @return
	 */
	public static int countDepth(String path) {
		int count = 0;
		String[] s =path.split("/");
		for(int i = 0; i < s.length; i++){
			if(!"".equals(s[i])){
				count++;
			}
		}
		return count;
	}

	/**
	 * 使用symbol分割content 得到字符串数组 并取出从startIndex到endIndex的元素 用symbol连接成新的字符串
	 * @param content
	 * @param symbol
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public static String subStr(String content, String symbol, int startIndex, int endIndex) {
		String ret="";

		String[] arr=content.split("\\"+symbol);

		for(int i=startIndex; i<endIndex; i++){
			if("".equals(arr[i]))
				continue;
			ret+=arr[i]+symbol;
		}

		return ret;
	}

	/**
	 * "name":"xuxiang","work":"jandar"
	 * 把这样的字符参数串转成key-value对到MAP
	 * @param s
	 * @return
	 */
	public static Map string2ParamMap(String s) {
		Map<String, String> m=new HashMap<String, String>();
		if(s!=null && !s.equals("")){
			String[] param=s.split(",");
			for (int i = 0; i < param.length; i++) {
				if(param[i].equals(""))
					continue;
				String[] key=param[i].split(":");
				if(!key[0].equals(""))
					try {
						m.put(key[0].replaceAll("\"|'", ""),java.net.URLDecoder.decode(key[1],"utf-8").replaceAll("\"|'", ""));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
			}
		}
		return m;
	}

	/**
	 * delete the same element from array of string
	 * @param a
	 * @return
	 */
	public static String[] array_unique(String[] a) {
		// array_unique
		List<String> list = new LinkedList<String>();
		for(int i = 0; i < a.length; i++) {
			if(!list.contains(a[i])) {
				list.add(a[i]);
			}
		}
		return (String[])list.toArray(new String[list.size()]);
	}

	/**
	 * 从输入流中读取内容为一个字符串
	 * @param is 输入流
	 * @return
	 */
	public static String inputStreamToString(InputStream is){
		StringBuilder result = new StringBuilder();
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line+"\r\n");
			}
			in.close();
		}
		catch(Exception e){
		}
		return result.toString();
	}

	/**   
	 * 半角转全角   
	 *    
	 * @param input String.   
	 * @return 全角字符串.   
	 */    
	public   static  String toSBC(String input) {    
		if  (input ==  null ) {    
			return   "" ;    
		}    
		char  c[] = input.toCharArray();    
		for  ( int  i =  0 ; i < c.length; i++) {    
			if  (c[i] ==  ' ' ) {    
				c[i] =  '\u3000' ;    
			}  else   if  (c[i] <  '\177' ) {    
				c[i] = ( char ) (c[i] +  65248 );    

			}    
		}    
		return   new  String(c);    
	}  



	/**   
	 * 全角转半角   
	 *    
	 * @param input String.   
	 * @return 半角字符串   
	 */    
	public   static  String toDBC(String input) {    
		if  (input ==  null ) {    
			return   "" ;    
		}    
		char  c[] = input.toCharArray();    
		for  ( int  i =  0 ; i < c.length; i++) {    
			if  (c[i] ==  '\u3000' ) {    
				c[i] =  ' ' ;    
			}  else   if  (c[i] >  '\uFF00'  && c[i] <  '\uFF5F' ) {    
				c[i] = ( char ) (c[i] -  65248 );    

			}    
		}    
		return   new  String(c);    
	}  


	/*
	 * 分割中文和非中文
	 */
	public static String strRep(String context, String s, String e){
		StringBuffer sb = new StringBuffer();
		sb.append(s);
		Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
		char[] cs = context.toCharArray();
		boolean up = true;
		boolean need = false;
		for(char c : cs){
			String tmp = String.valueOf(c);
			Matcher m = pattern.matcher(tmp);
			if(m.matches()){
				if(up){
					need = false; 
				}else{
					need = true;
				}
				up = true;
			}else{
				if(up){
					need = true;
				}else{
					need = false;
				}
				up = false;
			}
			if(need){
				sb.append(e);
				sb.append(s);
				sb.append(tmp);

			}else{
				sb.append(tmp);
			}
		}
		sb.append(e);
		return sb.toString();
	} 

	/**
	 * utf-8转义
	 * @param str
	 * @return
	 */
	public static String decodeString(String str){

		try {
			if(str==null||str.equals(""))
				return str;
			str=java.net.URLDecoder.decode(str, "utf-8");
			str=java.net.URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * 字符串左补 “0” 或 “ ” 
	 * @param length   长度
	 * @param str      原始字符串
	 * @param isZero   是否补零
	 * @return
	 */
	public static String leftPadding(int length, String str, boolean isZero){
		int strLength = str.length();
		if(strLength > length){
			return str.substring(0, length);
		}else if(strLength == length){
			return str;
		}else{
			StringBuffer sb = new StringBuffer();
			StringBuffer tmp = new StringBuffer();
			for (int i = 0; i < (length - strLength); i++) {
				tmp.append(isZero?"0":" ");
			}
			sb.append(tmp).append(str);
			return sb.toString();
		}
	}

	/**
	 * 字符串右补 “0” 或 “ ” 
	 * @param length   长度
	 * @param str      原始字符串
	 * @param isZero   是否补零
	 * @return
	 */
	public static String rightPadding(int length, String str, boolean isZero){
		int strLength = str.length();
		if(strLength > length){
			return str.substring(0, length);
		}else if(strLength == length){
			return str;
		}else{
			StringBuffer sb = new StringBuffer();
			StringBuffer tmp = new StringBuffer();
			for (int i = 0; i < (length - strLength); i++) {
				tmp.append(isZero?"0":" ");
			}
			sb.append(str).append(tmp);
			return sb.toString();
		}
	}

}
