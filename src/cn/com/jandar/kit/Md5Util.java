package cn.com.jandar.kit;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util{
	public static String compute(String inStr){
		if(inStr==null||"".equals(inStr))
			return "";
		char[] charArray = inStr.toCharArray();

		byte[] byteArray = new byte[charArray.length];

		for (int i=0; i<charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		MessageDigest md5 = null;
		try {
			md5 =  MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i=0; i<md5Bytes.length; i++){
			int val = ((int) md5Bytes[i] ) & 0xff; 
			if (val < 16) hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	public static void main(String[] args){
		String postString = Md5Util.compute("abc");
		System.out.println(postString);
		if(postString.equals("900150983cd24fb0d6963f7d28e17f72")){
			System.out.println("true");
		}else 
			System.out.println("false");
	}
}