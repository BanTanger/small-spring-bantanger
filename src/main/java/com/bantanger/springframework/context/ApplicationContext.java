package com.bantanger.springframework.context;

import com.bantanger.springframework.beans.factory.ListableBeanFactory;

/**
 * 应用上下文接口
 * 继承了关于 BeanFactory 的方法，例如所有 getBean 方法
 * @author BanTanger 半糖
 * @Date 2023/2/7 23:45
 */
public interface ApplicationContext extends ListableBeanFactory {

    // ApplicationContext 本身是 Central 接口，但目前还不需要添加一些获取ID和父类上下文，所以暂时没有接口方法的定义。

}
