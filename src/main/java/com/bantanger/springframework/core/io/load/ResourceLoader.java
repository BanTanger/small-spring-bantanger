package com.bantanger.springframework.core.io.load;

import com.bantanger.springframework.core.io.resource.Resource;

/**
 * 定义统一资源加载器
 * 统一资源器可以将不同的资源加载方式集中到同一的类服务下进行处理
 * 外部用户只需要传递资源地址即可
 * @author BanTanger 半糖
 * @Date 2023/2/7 13:32
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 核心接口: 定义外部调度资源加载器统一接口
     * @param location 外部资源地址
     * @return Resource 接口实例
     */
    Resource getResource(String location);

}
