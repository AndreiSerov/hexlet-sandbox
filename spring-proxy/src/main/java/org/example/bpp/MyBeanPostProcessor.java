package org.example.bpp;

import org.example.annotation.Transactional;
import org.example.persist.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Class<?>> beans = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(MyBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof Repository) {
            beans.put(beanName, bean.getClass());
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {

        if (!beans.containsKey(beanName)) {
            return bean;
        }
        Class<?> beanClass = beans.get(beanName);

        return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
                if (method.isAnnotationPresent(Transactional.class)) {
                    log.info("Transaction Begin");
                    final Object result = method.invoke(bean, args);
                    log.info("Transaction Commit");
                    return result;
                }


                return method.invoke(bean, args);
            }
        );
    }
}
