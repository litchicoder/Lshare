package com.example.lsharelib;

/**
 * Author: liujicheng
 * Package:com.example.lsharelib
 * Date:   2017/11/8
 * Desc: com.example.lsharelib
 */
public class ShareFactory {

    public static <S extends ShareAPI> S creatShareAPI(Class<S> clazz) {
        S s = null;
        try {
            s = (S) Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return s;
    }
}
