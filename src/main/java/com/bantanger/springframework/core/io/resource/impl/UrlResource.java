package com.bantanger.springframework.core.io.resource.impl;

import cn.hutool.core.lang.Assert;
import com.bantanger.springframework.core.io.resource.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * URL 方式加载资源
 * @author BanTanger 半糖
 * @Date 2023/2/7 13:36
 */
public class UrlResource implements Resource {

    private final URL url;

    public UrlResource(URL url) {
        Assert.notNull(url,"URL must not be null -- URL不能为空");
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = this.url.openConnection();
        try {
            return urlConnection.getInputStream();
        } catch (IOException ex) {
            if (urlConnection instanceof HttpURLConnection){
                ((HttpURLConnection) urlConnection).disconnect();
            }
            throw ex;
        }
    }

}
