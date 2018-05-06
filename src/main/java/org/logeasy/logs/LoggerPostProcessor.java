package org.logeasy.logs;

import java.lang.reflect.Field;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

/**
 * @author : Omid Pourhadi omidpourhadi [AT] gmail [DOT] com
 * @version 0.1
 */
public class LoggerPostProcessor implements BeanPostProcessor
{

    private boolean afterInitialization = false;

    public boolean isAfterInitialization()
    {
        return afterInitialization;
    }

    public void setAfterInitialization(boolean afterInitialization)
    {
        this.afterInitialization = afterInitialization;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
    {
        if (!this.afterInitialization)
        {
            doProcess(bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException
    {
        if (this.afterInitialization)
        {
            doProcess(bean);
        }
        return bean;
    }

    protected void doProcess(Object bean)
    {
        ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback() {
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException
            {
                if (field.getAnnotation(Logger.class) != null)
                {
                    ReflectionUtils.makeAccessible(field);
                    org.slf4j.Logger log = LoggerFactory.getLogger(bean.getClass());
                    field.set(bean, log);
                }
            }
        });
    }

}
