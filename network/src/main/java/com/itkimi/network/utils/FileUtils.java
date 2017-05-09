package com.itkimi.network.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by kimi on 2017/5/5 0005.
 * Email: 24750@163.com
 */

public class FileUtils {

    public static void closeIO(Closeable ... closeables){
        for (Closeable var : closeables) {
            if(var != null){
                try {
                    var.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
