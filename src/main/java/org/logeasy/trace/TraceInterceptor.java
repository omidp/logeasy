package org.logeasy.trace;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

/**
 * @author omidp
 *
 */
public class TraceInterceptor implements MethodInterceptor
{

    @Override
    public Object invoke(MethodInvocation i) throws Throwable
    {
        Method method = i.getMethod();
        Trace annotation = method.getAnnotation(Trace.class);
        StringBuilder content = new StringBuilder("Entering : ").append(createLog(method, i.getArguments(), i.getThis().getClass()));
        printLog(annotation.level(), i.getThis().getClass(), content);
        Object ret = i.proceed();
        content = new StringBuilder("Exiting : ").append(createLog(method, i.getArguments(), i.getThis().getClass()));
        printLog(annotation.level(), i.getThis().getClass(), content);
        return ret;
    }

    private void printLog(LogLevel logLevel, Class<? extends Object> clz, StringBuilder content)
    {
        org.slf4j.Logger log = LoggerFactory.getLogger(clz);
        switch (logLevel)
        {
        case INFO:
            log.info(content.toString());
            break;
        case TRACE:
            log.trace(content.toString());
            break;
        case DEBUG:
            log.debug(content.toString());
            break;
        case ERROR:
            log.error(content.toString());
            break;
        case WARN:
            log.warn(content.toString());
            break;
        default:
            log.info(content.toString());
            break;
        }

    }

    private String createLog(Method method, Object[] args, Class<?> clz)
    {
        String className = clz.getSimpleName();
        String methodName = method.getName();
        StringBuilder sb = new StringBuilder("[" + className + "] :  " + methodName + "(");
        for (int i = 0; i < args.length; i++)
        {
            if (i > 0)
            {
                sb.append(", ");
            }
            Object arg = args[i];
            if (arg instanceof String && arg != null)
            {
                sb.append("\"").append(arg).append("\"");
            }
            else
            {
                sb.append(arg);
            }

        }
        sb.append(")");
        return sb.toString();

    }

}
