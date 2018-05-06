package org.logeasy.trace;

import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;

/**
 * @author omidp
 *
 */
public class TracePostProcessor extends AbstractBeanFactoryAwareAdvisingPostProcessor
{

    @Override
    public void setBeanFactory(BeanFactory beanFactory)
    {
        super.setBeanFactory(beanFactory);
        if (!(beanFactory instanceof ListableBeanFactory))
        {
            throw new IllegalArgumentException("Cannot use Trace autodetection without ListableBeanFactory");
        }
        this.advisor = new TraceAdvisor((ListableBeanFactory) beanFactory, Trace.class);
    }

}
