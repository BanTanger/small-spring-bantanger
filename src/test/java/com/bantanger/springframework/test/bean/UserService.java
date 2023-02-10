package com.bantanger.springframework.test.bean;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.support.manage.DisposableBean;
import com.bantanger.springframework.beans.factory.support.manage.InitializingBean;

/**
 * 模拟Bean
 * @author BanTanger 半糖
 * @Date 2023/2/6 17:23
 */
public class UserService implements InitializingBean, DisposableBean {

    private String uId;

    private String company;

    private String location;

    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId) + "," + company + "," + location;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws BeansException {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

}
