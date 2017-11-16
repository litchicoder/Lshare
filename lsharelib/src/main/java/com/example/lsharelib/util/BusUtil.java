package com.example.lsharelib.util;

import org.greenrobot.eventbus.EventBus;

/**
 * Author: liujicheng
 * Package:com.example.lsharelib.util
 * Date:   2017/11/9
 * Desc: com.example.lsharelib.util
 */
public class BusUtil {

    /***
     * 添加到订阅队列
     *
     * @param obj 用于接收消息的对象，该类必须要有一个公用的on***Event(Event) 方法
     */
    public static void register(Object obj) {
        if (!EventBus.getDefault().isRegistered(obj)) {
            EventBus.getDefault().register(obj);
        }
    }


    /**
     * 讲订阅对象从队列中移除
     *
     * @param obj 要移除的订阅对象
     */
    public static void unregister(Object obj) {
        if (EventBus.getDefault().isRegistered(obj)) {
            EventBus.getDefault().unregister(obj);
        }
    }


    /**
     * 发送消息到事件队列中
     *
     * @param msg 消息
     */
    public static void postEvent(Object msg) {
        EventBus.getDefault().post(msg);
    }
}
