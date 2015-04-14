/*
 *File: StrHandleUtils.java
 *Company: ECIQ
 *@version: 1.0
 *Date: 2013-10-8
 */
package xuxu.blog.common.utils;

import java.io.UnsupportedEncodingException;

/**
 *
 * 与外部接口处理数据时用的工具类
 *
 * @author 单军鹏
 * @since 1.0
 */
public class StrHandleUtils {
    //编码

    private static final String ENCODING = "GBK";

    /**
     * 对于编码类的文本（不包含汉字等）进行超长截取
     *
     * @param targetString 目标字符串
     * @param size 指定字节数
     *
     * @return
     */
    public static String cutCodeStr(String str, int size) {
        if (str == null) {
            return null;
        }
        if (str.length() < size) {
            return str;
        }
        return str.substring(0, size);
    }

    /**
     * 如果字符串的字节数超出指定字节数，进行从后往前截位处理
     *
     * @param targetString 目标字符串
     * @param byteCount 指定字节数
     *
     * @return
     */
    public static String cutStrFromTail(String targetString, int byteCount){
    	if (targetString == null) {
            return null;
        }
    	try {
            byte[] bytes = targetString.getBytes(ENCODING);
            //如果字符串的字节数超出了指定的长度
            if (bytes.length > byteCount) {
            	String tmpStr = new StringBuilder(targetString).reverse().toString();//反转字符串
            	tmpStr = subStringByByte(tmpStr, byteCount);//返回截取后的字符串
                return new StringBuilder(tmpStr).reverse().toString();//再反转回去
            }
        } catch (UnsupportedEncodingException ex) {
        	throw new RuntimeException(ex);
        }
        return targetString;
    	
    }
    
    
    /**
     * 如果字符串的字节数超出指定字节数，进行截位处理
     *
     * @param targetString 目标字符串
     * @param byteCount 指定字节数
     *
     * @return
     */
    public static String cutStr(String targetString, int byteCount) {
        if (targetString == null) {
            return null;
        }
        try {
            byte[] bytes = targetString.getBytes(ENCODING);
            //如果字符串的字节数超出了指定的长度
            if (bytes.length > byteCount) {
                //返回截取后的字符串
                return subStringByByte(targetString, byteCount);
            }
        } catch (UnsupportedEncodingException ex) {
        	throw new RuntimeException(ex);
        }
        return targetString;
    }

    /**
     * 按字节数对字符串进行截取
     *
     * @param targetString 目标字符串
     * @param cutByteCount 要截取的字节数
     *
     * @return 截取后的字符串，对于最后字节为汉字的情况将汉字舍弃，保证截取后的字节数不超过cutByteCount且不出现乱码
     */
    private static String subStringByByte(String targetString, int cutByteCount) {
        int byteCount = 0;//最终要截取的字节数
        int subStringCount = 0;//最终要截取的字符数
        int length = targetString.length();//原字符串的字符数
        for (int i = 0; i < length; i++) {
            if (byteCount == cutByteCount) {//达到预期字节数，跳出
                break;
            }
            char c = targetString.charAt(i);
            int byteCountOfChar = getByteCountOfChar(c, ENCODING);//取得该字符的字节数
            if (byteCount + byteCountOfChar > cutByteCount) {//截取字节数超出预计
                //放弃该字符，不增加要截取的字符数，跳出
                break;
            }
            byteCount += byteCountOfChar;//增加要截取的字节数
            subStringCount++;//增加要截取的字符数
        }
        //sjp TODO删除
//        System.out.println("最终要截取的字节数" + byteCount);
//        System.out.println("最终要截取的字符数" + subStringCount);
        return targetString.substring(0, subStringCount);
    }

    /**
     * 取得字符在某种编码下的字节数
     *
     * @param c 字符
     * @param encoding 字符编码
     *
     * @return 字节数
     */
    private static int getByteCountOfChar(char c, String encoding) {
        try {
            //取得字符在某种编码下的字节数
            return String.valueOf(c).getBytes(encoding).length;
        } catch (UnsupportedEncodingException ex) {
        	throw new RuntimeException(ex);
        }
    }

    
    /**
     * 取得字符在某种编码下的字节数
     *
     * @param c 字符
     * @param encoding 字符编码
     *
     * @return 字节数
     */
    public static int getByteCountOfString(String c, String encoding) {
        try {
            //取得字符在某种编码下的字节数
            return c.getBytes(encoding).length;
        } catch (UnsupportedEncodingException ex) {
        	throw new RuntimeException(ex);
        }
    }

    /**
     * 如果字符串的字节数超出指定字节数，进行截位处理,补齐省略号
     *
     * @param targetString 目标字符串
     * @param byteCount 指定字节数
     *
     * @return
     */
    public static String cutStrWithEllipsis(String targetString, int byteCount) {
        if (targetString == null) {
            return null;
        }
        try {
            byte[] bytes = targetString.getBytes(ENCODING);
            //如果字符串的字节数超出了指定的长度
            if (bytes.length > byteCount) {
                //返回截取后的字符串
                String tempString = subStringByByte(targetString, byteCount - 3) + "...";
                return tempString;
            }
        } catch (UnsupportedEncodingException ex) {
        	throw new RuntimeException(ex);
        }
        return targetString;
    }
    
    public static void main(String[] args){
        String str = StrHandleUtils.cutStrWithEllipsis("123456789012345678901234567890", 20);
        System.out.println(str);
        str = StrHandleUtils.cutStrWithEllipsis("12345678901234567890", 20);
        System.out.println(str);
        
        StringBuilder sb = new StringBuilder("123都是ds都是");
        String str2 = sb.reverse().toString();
        System.out.println(str2);
        
    }
}
