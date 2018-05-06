package org.logeasy.trace;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : Omid Pourhadi omidpourhadi [AT] gmail [DOT] com
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface Trace {

    boolean enable() default true;

    LogLevel level() default LogLevel.INFO;

}
