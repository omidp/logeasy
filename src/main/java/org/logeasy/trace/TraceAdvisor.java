package org.logeasy.trace;

import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.beans.factory.ListableBeanFactory;

/**
 * @author omidp
 *
 */
public class TraceAdvisor extends AbstractPointcutAdvisor
{

    private final Pointcut pointcut;
    private final TraceInterceptor advice;

    public TraceAdvisor(ListableBeanFactory beanFactory, Class<Trace> traceAnnotationType)
    {        
        this.advice = new TraceInterceptor();
        this.pointcut = new StaticMethodMatcherPointcutAdvisor() {

            @Override
            public boolean matches(Method method, Class<?> targetClass)
            {
                boolean annotationPresent = method.isAnnotationPresent(traceAnnotationType);
                if(annotationPresent)
                {
                    annotationPresent = isEnable(method.getAnnotation(Trace.class));
                }
                return annotationPresent;
            }
        };
    }
    
    private boolean isEnable(Trace trace)
    {
        if (trace.enable())
        {
            return true;
        }
        return false;
    }

    @Override
    public Pointcut getPointcut()
    {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice()
    {
        return advice;
    }

}
