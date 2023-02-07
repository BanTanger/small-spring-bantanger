package com.bantanger.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/7 11:56
 */
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "小鲨鱼");
        hashMap.put("10002", "半糖");
        hashMap.put("10003", "bantanger");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}
