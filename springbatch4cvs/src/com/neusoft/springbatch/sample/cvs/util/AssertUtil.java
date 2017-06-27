package com.neusoft.springbatch.sample.cvs.util;

/**
 * @author: cz
 * @description:断言工具�?
 */
public class AssertUtil {
	/**
	 * 断言对象是否为空 true为非�?false 为空
	 * @param object
	 * @return
	 */
	public static boolean notNull(Object object) {
		return object != null;
	}

    /**
     * 断言对象是否为空�?true为非空白,false为空�?
     * @param object
     * @return
     */
    public static boolean notEmpty(Object object){
         return !"".equals(object);
    }

    /**
     * 断言对象是否为空�?true为空�?false为非空白
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object){
        return "".equals(object);
    }

	/**
	 * 断言对象是否为空 true为空 false 为非�?
	 * @param object
	 * @return
	 */
	public static boolean isNull(Object object) {
		return object == null;
	}

	/**
	 * 断言表达式是否为true
	 * @param expression
	 * @return
	 */
	public static boolean isTrue(boolean expression) {
		return expression == true;
	}

	/**
	 * 断言表达式是否为false
	 * @param expression
	 * @return
	 */
	public static boolean isFalse(boolean expression) {
		return expression == false;
	}
}
