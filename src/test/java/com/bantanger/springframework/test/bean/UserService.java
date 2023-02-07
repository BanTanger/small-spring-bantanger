package com.bantanger.springframework.test.bean;

/**
 * 模拟Bean
 * @author BanTanger 半糖
 * @Date 2023/2/6 17:23
 */
public class UserService {

    private String uId;

    private UserDao userDao;

    public UserService() {
    }

    public UserService(String uId, UserDao userDao) {
        this.uId = uId;
        this.userDao = userDao;
    }

    public void queryUserInfo() {
        System.out.println("模拟Bean:UserService -- 查询用户信息: " + userDao.queryUserName(uId));
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
