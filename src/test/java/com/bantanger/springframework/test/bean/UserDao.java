package com.bantanger.springframework.test.bean;

import com.bantanger.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/16 18:07
 */
@Component
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "小鲨鱼, 上海, 米哈游");
        hashMap.put("10002", "半糖, 深圳, 腾讯");
        hashMap.put("10003", "bantanger, 北京, 百度");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}
