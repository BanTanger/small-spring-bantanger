package com.bantanger.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/7 11:56
 */
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    /*
     * 修改使用 static 静态代码块初始化数据方式
     * 改为提供 initDataMethod 和 destroyDataMethod 两个更优雅的方式进行处理
     */

    // 初始化方法
    public void initDataMethod() {
        hashMap.put("10001", "小鲨鱼");
        hashMap.put("10002", "半糖");
        hashMap.put("10003", "bantanger");
    }

    // 销毁方法，清空内存中的初始化数据
    public void destroyDataMethod() {
        System.out.println("执行：destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}
