package com.bantanger.springframework.test.bean;

/**
 * 模拟Bean
 * @author BanTanger 半糖
 * @Date 2023/2/6 17:23
 */
public class UserService {

    private String name;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("模拟Bean:UserService -- 查询用户信息: " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }
}
