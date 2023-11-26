package com.charity.utils;

import net.sf.ehcache.Element;
import net.sf.ehcache.store.compound.ReadWriteCopyStrategy;

import java.io.Serializable;

/**
 * 优化存储策略
 * <p>
 * ==========================================================================
 * 郑重说明：本项目免费开源！原创作者为：薛伟同学，严禁私自出售。
 * ==========================================================================
 * B站账号：薛伟同学
 * 微信公众号：薛伟同学
 * 作者博客：http://xuewei.world
 * ==========================================================================
 * 陆陆续续总会收到粉丝的提醒，总会有些人为了赚取利益倒卖我的开源项目。
 * 不乏有粉丝朋友出现钱付过去，那边只把代码发给他就跑路的，最后还是根据线索找到我。。
 * 希望各位朋友擦亮慧眼，谨防上当受骗！
 * ==========================================================================
 *
 * @author <a href="http://xuewei.world/about">XUEW</a>
 */
public class MyCopyStrategy implements ReadWriteCopyStrategy<Element> {
    @Override
    public Element copyForWrite(Element value) {
        if (value != null) {
            Object temp = value.getObjectValue();
            return new Element(value.getObjectKey(), temp);
        }
        return value;
    }

    @Override
    public Element copyForRead(Element storedValue) {
        if (storedValue != null) {
            Object temp = storedValue.getObjectValue();
            return new Element(storedValue.getObjectKey(), temp);
        }
        return storedValue;
    }
}