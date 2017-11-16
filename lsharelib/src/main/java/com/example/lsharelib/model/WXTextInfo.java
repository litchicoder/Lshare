package com.example.lsharelib.model;

/**
 * Author: liujicheng
 * Package:com.example.lsharelib.model
 * Date:   2017/11/8
 * Desc: com.example.lsharelib.model
 */
public class WXTextInfo extends WXBaseInfo{

    private String text;

    public String getText() {
        return text == null ? "" : text;
    }

    public void setText(String text) {
        this.text = text == null ? "" : text;
    }
}
