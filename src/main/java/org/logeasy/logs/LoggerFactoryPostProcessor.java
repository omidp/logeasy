package org.logeasy.logs;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author : Omid Pourhadi omidpourhadi [AT] gmail [DOT] com
 * @version 0.1
 */
public class LoggerFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException
    {
        if (!(beanFactory instanceof DefaultListableBeanFactory))
        {
            throw new IllegalStateException("LoggerFactoryPostProcessor needs to operate on a DefaultListableBeanFactory");
        }
        DefaultListableBeanFactory dlbf = (DefaultListableBeanFactory) beanFactory;
        dlbf.addBeanPostProcessor(new LoggerPostProcessor());

    }

}
