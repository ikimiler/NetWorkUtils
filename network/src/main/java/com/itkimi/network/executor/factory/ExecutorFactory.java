package com.itkimi.network.executor.factory;

import com.itkimi.network.executor.IExecutor;

/**
 * Created by kimi on 2017/5/5 0005.
 * Email: 24750@163.com
 */

public abstract class ExecutorFactory {

    /**
     * 具体请求工厂类，扩展请继承
     * @return
     */
    public abstract IExecutor create();

}
