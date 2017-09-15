/**
 * 
 */
package com.dfire.qa.meal.vo.cash;

/**
 *
 * @author <a href="mailto:zxh1000@163.com">张向华</a>
 * @version $Revision$
 */
public final class ShortUtils {
	public static Short nullToFalse(Short source) {
		return nullToDefault(source, (short) 0);
	}
	
	public static Short nullToTrue(Short source) {
		return nullToDefault(source, (short) 1);
	}
	public static Short nullToDefault(Short source, short def) {
		if (source == null) {
			return def;
		}
		return source;
	}
}
