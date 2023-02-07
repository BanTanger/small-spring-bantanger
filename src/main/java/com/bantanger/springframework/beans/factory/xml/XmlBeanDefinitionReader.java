package com.bantanger.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.config.BeanDefinition;
import com.bantanger.springframework.beans.factory.config.BeanReference;
import com.bantanger.springframework.beans.factory.config.PropertyValue;
import com.bantanger.springframework.beans.factory.support.read.impl.AbstractBeanDefinitionReader;
import com.bantanger.springframework.beans.factory.support.registry.BeanDefinitionRegistry;
import com.bantanger.springframework.core.io.load.ResourceLoader;
import com.bantanger.springframework.core.io.resource.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * 读取 XML 文件中定义的 bean
 *
 * @author BanTanger 半糖
 * @Date 2023/2/7 15:08
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (
                    // 将加载好的资源转化成流，调用 doLoadBeanDefinitions，
                    // 把 bean信息注入到定义 bean 信息工厂 BeanDefinition
                    InputStream inputStream = resource.getInputStream()
            ) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource +
                    "解析XML: " + resource + " 文件出现IO异常", e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    /**
     * 读取 XML 配置信息，并将其加载到 beanDefinition
     *
     * @param inputStream 资源加载流
     * @throws ClassNotFoundException
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        // 读取 XML 配置信息，将其解析成 Document 对象
        Document doc = XmlUtil.readXML(inputStream);
        // XML 文件所有元素
        Element root = doc.getDocumentElement();
        // XML文件节点列表 <beans><beans/>
        NodeList childNodes = root.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            // 判断元素
            if (!(childNodes.item(i) instanceof Element)) {
                continue;
            }
            // 判断对象
            if (!"bean".equals(childNodes.item(i).getNodeName())) {
                continue;
            }

            // 解析标签：bean 并获取其 id、name、className
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            // 获取 Class，方便获取类中的名称
            Class<?> aClass = Class.forName(className);
            // 优先级：id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(aClass.getSimpleName());
            }

            // 定义 Bean
            BeanDefinition beanDefinition = new BeanDefinition(aClass);
            // 读取属性并填充
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                // 判断元素
                if (!(childNodes.item(j) instanceof Element)) {
                    continue;
                }
                // 判断对象
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) {
                    continue;
                }

                // 解析标签: property 并获取其 name、value、ref
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                // 获取属性指: 引入值对象
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创造属性信息并注入到 bean 定义
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegister().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed -- " +
                        "beanName[" + beanName + "]不允许复制");
            }
            // 注册 BeanDefinition
            getRegister().registerBeanDefinition(beanName, beanDefinition);
        }
    }

}
