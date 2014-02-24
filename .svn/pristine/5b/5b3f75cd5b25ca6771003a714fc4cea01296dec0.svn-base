package cn.com.jandar.kit;


import java.sql.Clob;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对数据库的操作工具类
 * @author XUXIANG
 *
 */
@SuppressWarnings("unchecked")
public class DbUtil {
	public static final String SPRITEFLAG="-";
	
	
	/**  
     * 数据库Clob对象转换为String  
     */  
    @SuppressWarnings("unused")   
    public static String readClobToString(Clob clob)   
    {   
        try  
        {   
            return clob.getSubString((long)1,(int)clob.length());
        }   
        catch (SQLException es)   
        {   
            es.printStackTrace();   
        }   
        return null;   
    }     

    
    
	/**
	 * 读NULL字段值为空
	 * 
	 * @param o
	 * @return
	 */
	public static int readDbInt(Object o) {
		if (o == null)
			return -1;
		else if(readDbString(o).equals(""))
			return -1;
		else
			return Integer.parseInt(clearNumberString(o));
	}

	/**
	 * 读NULL字段值为空
	 * 
	 * @param o
	 * @return
	 */
	public static String readDbString(Object o) {
		if (o == null)
			return "";
		else
			return o.toString();
	}

	/**
	 * 读NULL字段值为空
	 * 
	 * @param o
	 * @return
	 */
	public static int readDbInt0(Object o) {
		if (o == null)
			return 0;
		else if(readDbString(o).equals(""))
			return 0;
		else
			return Integer.parseInt(clearNumberString(o));
	}

	/**
	 * 读NULL字段值为空
	 * 
	 * @param o
	 * @return
	 */
	public static Long readDbLong(Object o) {
		if (o == null)
			return -1L;
		else if(readDbString(o).equals(""))
			return -1L;
		else
			return Long.valueOf(clearNumberString(o));
	}

	/**
	 * 读NULL字段值为空
	 * 
	 * @param o
	 * @return
	 */
	public static Long readDbLong0(Object o) {
		if (o == null)
			return 0L;
		else if(readDbString(o).equals(""))
			return 0L;
		else
			return Long.valueOf(clearNumberString(o));
	}
	/**
	 * 读NULL字段值为空
	 * 
	 * @param o
	 * @return
	 */
	public static Double readDbDouble(Object o) {
		if (o == null)
			return -1D;
		else if(readDbString(o).equals(""))
			return -1D;
		else
			return Double.valueOf(clearNumberString(o));
	}
	
	/**
	 * 读NULL字段值为空
	 * 
	 * @param o
	 * @return
	 */
	public static Double readDbDouble0(Object o) {
		if (o == null)
			return 0D;
		else if(readDbString(o).equals(""))
			return 0D;
		else
			return Double.valueOf(clearNumberString(o));
	}
	
	
	/**
	 * 读NULL字段值为空
	 * 
	 * @param o
	 * @return
	 */
	public static Float readDbFloat(Object o) {
		if (o == null)
			return -1F;
		else if(readDbString(o).equals(""))
			return -1F;
		else
			return Float.valueOf(clearNumberString(o));
	}

	/**
	 * 读NULL字段值为空
	 * 
	 * @param o
	 * @return
	 */
	public static Float readDbFloat0(Object o) {
		if (o == null)
			return 0F;
		else if(readDbString(o).equals(""))
			return 0F;
		else
			return Float.valueOf(clearNumberString(o));
	}

	/**
	 * 读Checkbox值为0或1
	 * 
	 * @param o
	 * @return
	 */
	public static int readCheckboxToInt(Object o) {
		if (o == null)
			return 0;
		else if(readDbString(o).equals(""))
			return 0;
		else
		{
			if(readDbString(o).toLowerCase().equals("true"))
			{
				return 1;
			}
			else
				return 0;
		}
	}
	
	/**
	 * 读Checkbox值为0或1
	 * 
	 * @param o
	 * @return
	 */
	public static String readCheckboxToString(Object o) {
		if (o == null)
			return "0";
		else if(readDbString(o).equals(""))
			return "0";
		else
		{
			if(readDbString(o).toLowerCase().equals("true"))
			{
				return "1";
			}
			else
				return "0";
		}
	}


	
	
	/**
	 * 将一个[3,,4,,5,,,,6,]的串 变成 [,3,,4,,5,,6,]
	 * @param s
	 * @return
	 */
	public static String getSqlCharsForLike(String s) {
		String result="";
		if(s==null || "".equals(s))
			return "";
		else
		{
			String[] _s=s.split(",");
			for (int i = 0; i < _s.length; i++) {
				if(!"".equals(_s[i]))
					result+=","+_s[i]+",";
			}
			return  result;
		}
	}

	/*将-1-2-7,-16-17-19 变成
	 * '-1-2-7','-1-2','-1','-16-17-19','-16-17','-16'
	 * @param s
	 * @return
	 */
	public static String getSqlCharsForTreeClass(String s) {
		String classSplit=SPRITEFLAG;
		String sigmentSplit=",";
		String source="";
		String result="";
		String[] m,n;
		if(s==null || "".equals(s))
			return "''";
		else
		{
			source=StringUtil.formatComma(s);
			m=source.split(sigmentSplit);
			for (int i = 0; i < m.length; i++) {
				if(!m[i].equals(""))
				{
					n=m[i].split(classSplit);
					String x="";
					for (int j = 0; j < n.length; j++) {
						if(!n[j].equals(""))
						{
							x+=classSplit+n[j];
							String sumChar="'"+x+"'";
							if(result.indexOf(sumChar)==-1)
								result+=","+sumChar;
						}
					}
				}
			}
			
			return StringUtil.trimComma(result);
			
		}
	}

	public static float round(float d, int i) {
		
		return Math.round(d*100)/100.0f;
	}

	public static String sqlServerTop10000000000(String sql) {
		return sql.replaceAll("^(\\s)*[Ss][Ee][Ll][Ee][Cc][Tt](\\s)*([Tt][Oo][Pp](\\s)+[0-9]+)?", "select top 10000000000  ");
	}
	

	
	public static String replaceFormatSqlOrderBy(String sql) {
		sql = sql.replaceAll("(\\s)+", " ");
		int index = sql.toLowerCase().lastIndexOf("order by");
		if (index > sql.toLowerCase().lastIndexOf(")")) {
			String sql1 = sql.substring(0, index);
			String sql2 = sql.substring(index);
			sql2 = sql2
					.replaceAll(
							"[oO][rR][dD][eE][rR] [bB][yY] [a-zA-Z0-9_.]+((\\s)+(([dD][eE][sS][cC])|([aA][sS][cC])))?(( )*,( )*[a-zA-Z0-9_.]+(( )+(([dD][eE][sS][cC])|([aA][sS][cC])))?)*",
							"");
			return sql1 + sql2;
			
		}
		return sql;
	}
	
	
	/**
	 * 当需要将SQL中的SELECT ...FROM中的字段换成统计条数时使用
	 * @param sql
	 * @return
	 */
	public static String replaceFormatSqlToCount(String sql) {
		sql = sql.replaceAll("(\\s)+", " ");
		int fromIndex= sql.toLowerCase().indexOf("from");
		if(fromIndex>0)
			return "select count(0) "+sql.substring(fromIndex);
		else
			return sql;
	}
	
	
	
	
	/**
	 * 拼出LIKE语句，实现模糊查询 
	 * 支持空格查询多个关键字（空格=或  ＆=且）
	 * 注：请用全角字符＆
	 * @param fields
	 * @param keyword
	 * @return
	 */
	public static String getSqlLike(String fields[],String keyword){
		String likeStr="(";
		String[] s=keyword.split(" ");
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < s.length; j++) {
				if(!"".equals(s[j]))
				{
					if(likeStr.length()!=1)
						likeStr+=" OR (";
					else
						likeStr+=" (";
					//如果关键字还包含&符号，那就再切分
					if(s[j].indexOf("＆")!=-1)
					{
						String[] _andS=s[j].split("＆");
						String  _andResult="";
						for (int k = 0; k < _andS.length; k++) {
							if(!_andS[k].equals("")){
								if(_andResult.equals(""))
									_andResult=" "+fields[i].toUpperCase()+" LIKE '%" +_andS[k] + "%'  ";
								else
									_andResult+=" AND "+fields[i].toUpperCase()+" LIKE '%" +_andS[k] + "%'  ";
							}
						}
						likeStr+=" "+_andResult + " ";
					}
					else
						likeStr+=" "+fields[i].toUpperCase()+" LIKE '%" + s[j] + "%'  ";
					likeStr+=" ) ";
				}
			}
		}
		likeStr+=")";
		if(likeStr.equals("()"))
			return "";
		else
			return likeStr;
	}

	/**
	 * 生成一个时间范围的SQL串
	 * @param fieldName
	 * @param vrPublishDt
	 * @param vrPublishDtEnd
	 * @return
	 */
	public static String getSqlDataRange(String fieldName, String vrPublishDt, String vrPublishDtEnd) {
		String result="";
		if(!"".equals(vrPublishDt))
			result+=fieldName+">="+DateUtil.getDateStart(vrPublishDt);
		if(!"".equals(result))
			result+=" AND ";
		if(!"".equals(vrPublishDtEnd))
			result+=fieldName+" <="+vrPublishDtEnd;
		if(!"".equals(result))
			return " ("+result+") ";
		else
			return "";
	}
	
	
	
	/**
	 * 从LIST集合中取出指定字段来生成sql in 所需的字符串
	 *'-1-2-7','-1-2','-1','-16-17-19','-16-17','-16'
	 * @param ls
	 * @param fieldName
	 * @return
	 */
	public static String  getSqlInCharsForTreeClassByList(List ls ,String fieldName)
	{
		String ids="";
		int i=0;
		for (Iterator iterator = ls.iterator(); iterator.hasNext();) {
			Map m = (Map) iterator.next();
			if(i!=0)
				ids+=",";
			ids += DbUtil.readDbString(m.get(fieldName)) ;
			i++;
		}
		return DbUtil.getSqlCharsForTreeClass(ids);
	}

	/**
	 * 将数据库中读出来的真、假值字段 值反转
	 * 如数据库中是 1 那就变 0 ,是 0 那就变 1
	 * 如数据库中是 NULL  那就变 1 ;
	 * @param ipCanRepeat
	 * @return
	 */
	public static Integer reverseBooleanField(Integer value) {
		if(value!=1)
			return 1;
		else
			return 0;
	}

	/**
	 * 将数据库中读出来的真、假值字段 值反转
	 * 如数据库中是 "1" 那就变 "0" ,是 "0" 那就变 "1" 
	 * 如数据库中是 "" 或NULL  那就变 "1" ;
	 * @param ipCanRepeat
	 * @return
	 */
	public static Integer reverseBooleanField(String value) {
		int _value=readDbInt0(value);
		if(_value!=1)
			return 1;
		else
			return 0;
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
	 * 分解字符串，以解决oracle的in字句中不能超过1000个数据
	 * @param sqlParam    字符串
	 * @param columnName  字段
	 * @return string     
	 */
	public static String getSqlIn(String sqlParam, String columnName ){
        String[] sqlArray = StringUtil.tranStringToArrayByComma(sqlParam);
        String singleStr = "";
        String singleSql = "";
        String allSql = "";
        int length = sqlArray.length;
        int size = length/1000;
        int count = 0;
        for(int j=1; j <= size+1 ; j++){
        	if(j*1000>length){
        		count = length;
        	}else{
        		count = j*1000;
        	}
        	for (int i = (j-1)*1000; i < count; i++) {
        	    singleStr += sqlArray[i]+",";
        	    singleSql = "OR "+columnName+" in("+singleStr+") "; 
		    }
        	allSql += singleSql;
        }      
        return allSql;
	}
	
	/*将'-1-2-7','-1-2','-1','-16-17-19','-16-17','-16'
	 * 得到'-1-2-7','-16-17-19',
	 * 去掉父类节点，只留下最小的子节点，并生成查询子树的SQL语句如：CLASSID like '-16-17-19-%' and CLASSID like '-1-2-7-%'
	 * @param s
	 * @return
	 */
	public static String getSqlCharsForTreeClass_minNodes(List ls ,String lsFieldName,String classIdFieldName) {
		String _tmp="";
		String result="";
		String resultSqlLike="";
		
		for (Iterator iterator = ls.iterator(); iterator.hasNext();) {
			Map mm = (Map) iterator.next();
			String _v=DbUtil.readDbString(mm.get(lsFieldName));
			_tmp+=","+_v+",";
		}
		
		for (Iterator iterator = ls.iterator(); iterator.hasNext();) {
			Map mm = (Map) iterator.next();
			String _v=DbUtil.readDbString(mm.get(lsFieldName));
			if(_tmp.replaceAll(","+_v+",","").indexOf(","+_v)==-1)
				result+=","+_v+",";
		}
		String _s[]=result.split(",");
		for (int i = 0; i < _s.length; i++) {
			if(!_s[i].equals("")){
				if(!resultSqlLike.equals(""))
					resultSqlLike+=" OR ";
				resultSqlLike+=classIdFieldName+" LIKE '"+_s[i]+"-%' ";
			}
		}
		
		return "("+resultSqlLike+")";
	}
	
	
	
	
	/**
	 * 拼出LIKE语句，实现模糊查询 
	 * 支持空格查询多个关键字（空格=或  ＆=且）
	 * 注：请用全角字符＆
	 * @param fields
	 * @param keyword
	 * @return
	 */
	public static String getSqlLikeAndCn(String fields[],String keyword){
		String likeStr="(";
		String[] s=keyword.split(" ");
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < s.length; j++) {
				if(!"".equals(s[j]))
				{
					if(likeStr.length()!=1)
						likeStr+=" OR (";
					else
						likeStr+=" (";
					//如果关键字还包含&符号，那就再切分
					if(s[j].indexOf("＆")!=-1)
					{
						String[] _andS=s[j].split("＆");
						String  _andResult="";
						for (int k = 0; k < _andS.length; k++) {
							if(!_andS[k].equals("")){
								if(_andResult.equals(""))
									_andResult=" "+fields[i].toUpperCase()+" LIKE '%" +_andS[k] + "%'  ";
								else
									_andResult+=" AND "+fields[i].toUpperCase()+" LIKE '%" +_andS[k] + "%'  ";
							}
						}
						likeStr+=" "+_andResult + " ";
					}
					else{
						if(s[j].length()==s[j].getBytes().length)
							likeStr+=" "+getCnStringSearchCondition(s[j],fields[i].toUpperCase());
						else
							likeStr+=" "+fields[i].toUpperCase()+" LIKE '%" + s[j] + "%'  ";
						
					}
					likeStr+=" ) ";
				}
			}
		}
		likeStr+=")";
		if(likeStr.equals("()"))
			return "";
		else
			return likeStr;
	}
	
	/**
	 * 中文字查询条件
	 * 
	 * @param fSearchString
	 * @param fFieldName
	 * @return
	 */
	public static String getCnStringSearchCondition(String fSearchString,
			String fFieldName) {
		int sLen = 0;
		String cConditionSql = "";
		String cTempCondition = "";
		String currenWord = "";
		cConditionSql = "(" + fFieldName + " LIKE '%" + fSearchString
				+ "%' OR ({%StringSearchCondition%}))";
		sLen = fSearchString.length();

		for (int i = 1; i <= sLen; i++) {
			currenWord = fSearchString.substring(i - 1, i);
			cTempCondition = cTempCondition
					+ getCnCharSearchCondition(currenWord, fFieldName, i);
			if (i < sLen)
				cTempCondition = cTempCondition + " and ";
		}
		return cConditionSql.replace("{%StringSearchCondition%}",
				cTempCondition);

	}

	/**
	 * 英文字母转中文SQL条件
	 * 
	 * @param cChar
	 * @param strFieldName
	 * @param cPos
	 * @return
	 * @throws SQLException 
	 */
	public static  String getCnCharSearchCondition(String cChar,
			String strFieldName, int cPos)  {
		String strBeginWord = "";
		String strEndWord = "";
		String strHeadChar = "";
		if ((cChar).length() >= 1)
			strHeadChar = (cChar.substring(0, 1)).toUpperCase();

		if (strHeadChar.equals("A")) {
			strBeginWord = "啊";
			strEndWord = "芭";
		}
		if (strHeadChar.equals("B")) {
			strBeginWord = "芭";
			strEndWord = "擦";
		}
		if (strHeadChar.equals("C")) {
			strBeginWord = "擦";
			strEndWord = "搭";
		}
		if (strHeadChar.equals("D")) {
			strBeginWord = "搭";
			strEndWord = "蛾";
		}
		if (strHeadChar.equals("E")) {
			strBeginWord = "蛾";
			strEndWord = "发";
		}
		if (strHeadChar.equals("F")) {
			strBeginWord = "发";
			strEndWord = "噶";
		}
		if (strHeadChar.equals("G")) {
			strBeginWord = "噶";
			strEndWord = "哈";
		}
		if (strHeadChar.equals("H")) {
			strBeginWord = "哈";
			strEndWord = "击";
		}
		if (strHeadChar.equals("I")) {
			strBeginWord = "击";
			strEndWord = "击";
		}
		if (strHeadChar.equals("J")) {
			strBeginWord = "击";
			strEndWord = "喀";
		}
		if (strHeadChar.equals("K")) {
			strBeginWord = "喀";
			strEndWord = "垃";
		}
		if (strHeadChar.equals("L")) {
			strBeginWord = "垃";
			strEndWord = "妈";
		}
		if (strHeadChar.equals("M")) {
			strBeginWord = "妈";
			strEndWord = "拿";
		}
		if (strHeadChar.equals("N")) {
			strBeginWord = "拿";
			strEndWord = "哦";
		}
		if (strHeadChar.equals("O")) {
			strBeginWord = "哦";
			strEndWord = "啪";
		}
		if (strHeadChar.equals("P")) {
			strBeginWord = "啪";
			strEndWord = "期";
		}
		if (strHeadChar.equals("Q")) {
			strBeginWord = "期";
			strEndWord = "然";
		}
		if (strHeadChar.equals("R")) {
			strBeginWord = "然";
			strEndWord = "撒";
		}
		if (strHeadChar.equals("S")) {
			strBeginWord = "撒";
			strEndWord = "塌";
		}
		if (strHeadChar.equals("T")) {
			strBeginWord = "塌";
			strEndWord = "挖";
		}
		if (strHeadChar.equals("U")) {
			strBeginWord = "挖";
			strEndWord = "挖";
		}
		if (strHeadChar.equals("V")) {
			strBeginWord = "挖";
			strEndWord = "挖";
		}
		if (strHeadChar.equals("W")) {
			strBeginWord = "挖";
			strEndWord = "昔";
		}
		if (strHeadChar.equals("X")) {
			strBeginWord = "昔";
			strEndWord = "压";
		}
		if (strHeadChar.equals("Y")) {
			strBeginWord = "压";
			strEndWord = "匝";
		}
		if (strHeadChar.equals("Z")) {
			strBeginWord = "匝";
			strEndWord = "卒";
		}
	
		return "( substring(" + strFieldName + "," + cPos + ",1)<'"
				+ strEndWord + "' AND substring(" + strFieldName + "," + cPos
				+ ",1)>='" + strBeginWord + "')";
	}
	
	/**
	 * 清除数值型字符串中的空白符号
	 * @param o
	 * @return
	 */
	public static String clearNumberString(Object o)   
    {   
		return o.toString().replaceAll("\\s+","");
    }
}
