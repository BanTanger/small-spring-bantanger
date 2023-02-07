package com.bantanger.springframework.core.io.resource.impl;

import cn.hutool.core.lang.Assert;
import com.bantanger.springframework.core.io.resource.Resource;
import com.bantanger.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * ClassPath 方式加载资源
 * @author BanTanger 半糖
 * @Date 2023/2/7 13:36
 */
public class ClassPathResource implements Resource {

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null -- 路径不能为空");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException(
                    this.path + " cannot be opened because it does not exist -- " +
                            this.path + " 不存在, 不能打开");
        }
        return is;
    }

}
