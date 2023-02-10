package com.bantanger.springframework.test;

import cn.hutool.core.io.IoUtil;
import com.bantanger.springframework.beans.factory.config.definition.PropertyValue;
import com.bantanger.springframework.beans.factory.config.definition.PropertyValues;
import com.bantanger.springframework.beans.factory.config.definition.BeanDefinition;
import com.bantanger.springframework.beans.factory.config.definition.BeanReference;
import com.bantanger.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.bantanger.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.bantanger.springframework.context.support.ClassPathXmlApplicationContext;
import com.bantanger.springframework.core.io.load.impl.DefaultResourceLoader;
import com.bantanger.springframework.core.io.resource.Resource;
import com.bantanger.springframework.test.bean.UserService;

import com.bantanger.springframework.test.common.MyBeanFactoryPostProcessor;
import com.bantanger.springframework.test.common.MyBeanPostProcessor;
import org.junit.Before;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/6 17:24
 */
public class ApiTest {

    @Test
    public void test_prototype() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        UserService userService02 = applicationContext.getBean("userService", UserService.class);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);

        // 4. 打印十六进制哈希
        System.out.println(userService01 + " 十六进制哈希：" + Integer.toHexString(userService01.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());
    }

    @Test
    public void test_factory_bean() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        // 2. 调用代理方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

}
