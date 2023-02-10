package com.bantanger.springframework.core.io.load.impl;

import cn.hutool.core.lang.Assert;
import com.bantanger.springframework.core.io.load.ResourceLoader;
import com.bantanger.springframework.core.io.resource.Resource;
import com.bantanger.springframework.core.io.resource.impl.ClassPathResource;
import com.bantanger.springframework.core.io.resource.impl.FileSystemResource;
import com.bantanger.springframework.core.io.resource.impl.UrlResource;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 包装资源加载器
 * 在获取资源的实现中，将三种不同类型的资源处理方式做了包装
 * 依次判断是否为 ClassPath、URL、File
 *
 * @author BanTanger 半糖
 * @Date 2023/2/7 13:38
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null -- 资源路径不能为空");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            // ClassPath 方式加载资源: 获取 location: 后的内容，并加载资源
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                // URL 方式加载资源
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                // File 加载资源
                return new FileSystemResource(location);
            }
        }
    }

}
