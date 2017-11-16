package com.example.lsharelib.listener;

/**
 * Author: liujicheng
 * Package:com.example.lsharelib.listener
 * Date:   2017/11/8
 * Desc: com.example.lsharelib.listener
 */
public interface ShareListener {

    void onStart();

    void onSuccess();

    void onFail(int code, String msg);
}
