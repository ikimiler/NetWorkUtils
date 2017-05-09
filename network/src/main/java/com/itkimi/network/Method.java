package com.itkimi.network;

/**
 * Created by kimi on 2017/5/5 0005.
 * Email: 24750@163.com
 */

public enum Method {

    GET(1),POST(2),POST_JSON(3),UPLOAD(4),DOWNLOAD(5);

    int value ;

    Method(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
