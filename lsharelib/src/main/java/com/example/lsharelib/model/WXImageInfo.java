package com.example.lsharelib.model;

/**
 * Author: liujicheng
 * Package:com.example.lsharelib.model
 * Date:   2017/11/13
 * Desc: com.example.lsharelib.model
 */
public class WXImageInfo extends WXBaseInfo{

    private String imgPath;

    public String getImgPath() {
        return imgPath == null ? "" : imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? "" : imgPath;
    }
}
