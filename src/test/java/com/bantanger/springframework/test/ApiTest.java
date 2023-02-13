package com.bantanger.springframework.test;

import com.bantanger.springframework.aop.AdvisedSupport;
import com.bantanger.springframework.aop.TargetSource;
import com.bantanger.springframework.aop.asprctj.AspectJExpressionPointCut;
import com.bantanger.springframework.aop.framework.Cglib2AopProxy;
import com.bantanger.springframework.aop.framework.JdkDynamicAopProxy;
import com.bantanger.springframework.test.bean.IUserService;
import com.bantanger.springframework.test.bean.UserService;
import com.bantanger.springframework.test.bean.UserServiceInterceptor;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/6 17:24
 */
public class ApiTest {

    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointCut pointCut = new AspectJExpressionPointCut("execution(* com.bantanger.springframework.test.bean.UserService.*(..))");

        Class<UserService> clazz = UserService.class;
        Method queryUserInfo = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointCut.matches(clazz));
        System.out.println(pointCut.matches(queryUserInfo, clazz));
    }

    @Test
    public void test_dynamic() {
        // 目标对象
        IUserService userService = new UserService();

        /*
        组装代理信息
         */
        AdvisedSupport advisedSupport = new AdvisedSupport();
        // 填充目标对象。通过这种方式，实现了 Spring AOP 中的核心:
        // 不通过 new 的方式创建一个对象实例，其实本质而言，是 Spring 底层 new 出所需要的 bean
        advisedSupport.setTargetSource(new TargetSource(userService));
        // 填充用户自定义拦截器
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointCut("execution(* com.bantanger.springframework.test.bean.IUserService.*(..))"));

        // 代理对象(JdkDynamicAopProxy)
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果:" + proxy_jdk.queryUserInfo());

        // 代理对象(Cglib2AopProxy)
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果:" + proxy_cglib.register("半糖"));
    }

}
