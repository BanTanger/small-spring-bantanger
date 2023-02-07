package com.bantanger.springframework.core.io.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * 定义通用核心处理资源加载流的接口
 * 具体实现: ClassPath、FileSystem、URL
 * @author BanTanger 半糖
 * @Date 2023/2/7 13:32
 */
public interface Resource {

    /**
     * 获取 InputStream 流
     * @return InputStream 流
     * @throws IOException IO异常
     */
    InputStream getInputStream() throws IOException;

}
