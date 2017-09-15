/************************* 版权声明 **********************************
 * 版权所有：Copyright (c) 2008, 2009 黄晓峰
 *
 * 工程名称：	com.zmsoft.core
 * 创建者：	Administrator 创建日期： 2009-10-16
 * 创建记录：	创建类结构。
 *
 * ************************* 变更记录 ********************************
 * 修改者： 
 * 修改日期：
 * 修改记录：
 *
 * 
 * ......************************* To Do List*****************************
 * 
 *
 * Suberversion 信息
 * ID:			$Id$
 * 源代码URL：	$HeadURL$
 * 最后修改者：	$LastChangedBy$
 * 最后修改日期：	$LastChangedDate$
 * 最新版本：		$LastChangedRevision$
 **/

package com.dfire.qa.meal.vo.cash.base;
import java.util.UUID;

import com.twodfire.util.UuidUtil;

/**
 * 基类.
 * 
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public abstract class Base extends AbstractStorage {
	/**
	 * <code>[serialVersionUID]</code>.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <code>自动</code>.
	 */
	public static final Short AUTO = (short) 2;

	/**
	 * <code>是</code>.
	 */
	public static final Short TRUE = (short) 1;

	/**
	 * <code>否</code>.
	 */
	public static final Short FALSE = (short) 0;

	public void initBaseData(String entityId) {
		setId(UuidUtil.generate(entityId));
		setCreateTime(System.currentTimeMillis());
		setIsValid(Base.TRUE);
		setOpTime(System.currentTimeMillis());
	}
}
