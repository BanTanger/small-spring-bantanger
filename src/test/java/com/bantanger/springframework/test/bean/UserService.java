package com.bantanger.springframework.test.bean;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.BeanFactory;
import com.bantanger.springframework.beans.factory.aware.BeanClassLoaderAware;
import com.bantanger.springframework.beans.factory.aware.BeanFactoryAware;
import com.bantanger.springframework.beans.factory.aware.BeanNameAware;
import com.bantanger.springframework.beans.factory.support.manage.DisposableBean;
import com.bantanger.springframework.beans.factory.support.manage.InitializingBean;
import com.bantanger.springframework.context.ApplicationContext;
import com.bantanger.springframework.context.ApplicationContextAware;

/**
 * 模拟Bean
 * @author BanTanger 半糖
 * @Date 2023/2/6 17:23
 */
public class UserService implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

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
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader:" + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanName:" + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

}
