/*
 * @(#)   filename.java
 * @Author:Authorname(mail) 2014-2-25
 * @Copyright (c) 2002-2014 Travelsky Limited. All rights reserved.
 */
package com.neusoft.springbatch.sample.cvs.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * @author cz(mail) 2014-2-25
 * @version 1.0
 * @Function 类功能说明
 */
public class StringUtil {

    private static MathContext DEFAULT_MATH = new MathContext(100); // 默认精度
    private static String DEFAULT_ENCODE = "UTF-8"; // 默认编码

    /**
     * 判断字符串是否为空 true 为空 false不为空 example isBlank("aa") = false
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        boolean result = false;
        if (null == str || "".equals(str.trim())) {
            result = true;
        }
        return result;
    }

    public static boolean equalsIgnoreCase(final String input1, final String input2) {
        if (input1 == null && input2 == null) return true;
        if (input1 == null && input2 != null) return false;
        if (input2 == null && input1 != null) return false;
        return input1.trim().equalsIgnoreCase(input2.trim());
    }

    public static boolean equals(final String input1, final String input2) {
        if (input1 == null || input2 == null) return false;
        return input1.trim().equals(input2.trim());
    }

    /**
     * 判断字符串是否为非空  true为非空 false为空
     *
     * @param str
     * @return
     */
    public static boolean notBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 填充字符串
     *
     * @param str
     * @return
     */
    public static String fillString(String str) {
        if (isBlank(str))
            return "";
        else
            return str;
    }

    /**
     * 填充字符串,数组为空时填充字符串为""
     *
     * @param array
     * @param index
     * @return
     */
    public static String fillString(String[] array, int index) {
        if (AssertUtil.notNull(array)) {
            if (index >= array.length) {
                return "";
            } else {
                return array[index].trim();
            }
        }
        return "";
    }

    /**
     * 比较两个字符串是否相同,不区别大小写。
     * 注意:传入两个对象为空时也认为是相同
     *
     * @param str1 --参数1
     * @param str2 --参数2
     * @return true为相同 false为不同
     */
    public static boolean compare(String str1, String str2) {
        if (AssertUtil.isNull(str1) && AssertUtil.isNull(str2)) {
            return true;
        }
        if (AssertUtil.isNull(str1) || AssertUtil.isNull(str2)) {
            return false;
        }
        return str1.trim().toUpperCase().equals(str2.trim().toUpperCase());
    }

    /**
     * 价格前缀处理 example prefixPrice("10.00") == +10.00
     *
     * @param target --需要处理的字符串
     * @return
     */
    public static String prefixPrice(String target) {
        if (target.indexOf("+") != 0) {
            return "+" + target;
        }
        return target;
    }

    /**
     * 证件类型转化
     *
     * @param target
     * @return
     */
    public static String changeIdType(String target) {
        if (target.equals("NI") || target.equals("PP") || target.equals("ID")) {
            return target;
        } else if ("身份证".equals(target.trim())) {
            return "NI";
        } else if ("护照".equals(target.trim())) {
            return "PP";
        } else if ("其他".equals(target.trim())) {
            return "ID";
        }
        return "";
    }

    /**
     * 格式化金额 传入528.0 格式化成528
     *
     * @param value
     * @return
     */
    public static String formatPrice(String value) {
        return value.substring(0, value.indexOf(".") == -1 ? value.length() : value.indexOf("."));
    }

    /**
     * 格式化到店时间,传入次日0:00-次日6:00时,转为 0:00--6:00
     *
     * @param value
     * @return
     */
    public static String formatArriveTime(String value) {
        if (value.indexOf("次日") != -1) {
            return "0:00-6:00";
        }
        return value;
    }

    /**
     * 格式化日期 传入2013-12-17+08:00 返回2013-12-17
     *
     * @param value
     * @return
     */
    public static String formatDate(String value) {
        return value.substring(0, value.indexOf("+"));
    }

    /**
     * Long号序列化 0L传入则根据长度返回 000000L
     *
     * @param value  --值
     * @param length -要序列化的长度
     * @return
     * @example: result =toSequence(10L,5)  result = 00010
     */
    public static String toSequence(Long value, int length) {
        StringBuffer result = new StringBuffer();
        int size = value.toString().length();
        if (size < length) {
            for (int i = size + 1; i <= length; i++) {
                result.append("0");
            }
        }
        result.append(value.toString());
        return result.toString();
    }

    /**
     * decodeURL(这里用一句话描述这个方法的作用)
     *
     * @param target
     * @return
     */
    public static String decodeURL(String target) {
        try {
            target = java.net.URLDecoder.decode(target, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * 字符串编码集转换
     *
     * @param target
     * @param encode
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(String target, String encode) throws UnsupportedEncodingException {
        return new String(target.getBytes(), encode);
    }

    /**
     * 字符串编码集转换
     *
     * @param target
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(String target) throws UnsupportedEncodingException {
        return new String(target.getBytes(), DEFAULT_ENCODE);
    }

    /**
     * 字符串数字运算相加
     *
     * @param args
     * @return
     */
    public static String add(String... args) {
        BigDecimal result = new BigDecimal("0", DEFAULT_MATH);
        for (String str : args) {
            result = result.add(new BigDecimal(str, DEFAULT_MATH));
        }
        return result.toString();
    }

    /**
     * string2IntArray(这里用一句话描述这个方法的作用)
     *
     * @param str
     * @param separator
     * @return
     */
    public static Integer[] string2IntArray(String str, String separator) {
        return stringArrayToIntArray(str.split(separator));
    }

    /**
     * stringArrayToIntArray(这里用一句话描述这个方法的作用)
     *
     * @param array
     * @return
     */
    public static Integer[] stringArrayToIntArray(String[] array) {
        if (array == null) {
            return null;
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (String s : array) {
            ret.add(Integer.parseInt(s));
        }
        Integer[] arry = new Integer[]{};
        return ret.toArray(arry);
    }

    /**
     * intArrayToString(这里用一句话描述这个方法的作用)
     *
     * @param array
     * @param separate
     * @return
     */
    public static String intArrayToString(int[] array, String separate) {
        if (array == null) {
            return "";
        }
        StringBuffer sbRet = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            if (sbRet.length() == 0) {
                sbRet.append(array[i]);
            } else {
                sbRet.append(separate).append(array[i]);
            }
        }
        return sbRet.toString();
    }

    /**
     * arrayToString(这里用一句话描述这个方法的作用)
     *
     * @param array
     * @param separate
     * @param prepend
     * @param append
     * @return
     */
    public static <E> String arrayToString(E[] array, String separate, String prepend, String append) {
        if (array == null) {
            return "";
        }
        StringBuffer sbRet = new StringBuffer();
        for (E a : array) {
            if (sbRet.length() != 0) {
                sbRet.append(separate);
            }
            if (StringUtils.isNotEmpty(prepend)) {
                sbRet.append(prepend);
            }
            sbRet.append(a);
            if (StringUtils.isNotEmpty(append)) {
                sbRet.append(append);
            }
        }
        return sbRet.toString();
    }

    /**
     * arrayToString(这里用一句话描述这个方法的作用)
     *
     * @param array
     * @param separate
     * @return
     */
    public static <E> String arrayToString(E[] array, String separate) {
        return arrayToString(array, separate, null, null);
    }

    /**
     * listToString(这里用一句话描述这个方法的作用)
     *
     * @param array
     * @param separate
     * @return
     */
    public static <E> String listToString(List<E> array, String separate) {
        if (array != null) {
            return arrayToString(array.toArray(), separate, null, null);
        } else {
            return "";
        }
    }

    public static <E> List<E> arrayToList(E[] array) {
        if (array != null) {
            List<E> list = new ArrayList<E>(array.length);
            for (E el : array) {
                list.add(el);
            }
            return list;
        } else {
            return null;
        }
    }

    /**
     * 字符串格式化
     * 实现 format("a{0}{1}","b","c") = abc的功能
     *
     * @param str
     * @param args
     * @return
     */
    public static String format(String str, Object... args) {
        if (args.length == 0) return str;
        for (int index = 0; index < args.length; index++) {
            str = str.replace("{" + index + "}", args[index].toString());
        }
        return str;
    }
}
