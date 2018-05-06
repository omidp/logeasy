package org.logeasy.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.logeasy.logs.LoggerPostProcessor;
import org.logeasy.trace.TracePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author omidp
 *
 */
@RunWith(SpringRunner.class)
public class LogTest
{

    @TestConfiguration()
    static class LogTraceTestContextConfiguration
    {

        @Bean
        public TraceTestService testService()
        {
            return new TraceTestService();
        }

        @Bean
        public TracePostProcessor trace()
        {
            return new TracePostProcessor();
        }

        @Bean
        public LoggerPostProcessor log()
        {
            return new LoggerPostProcessor();
        }

    }

    @Autowired
    TraceTestService service;

    @Before
    public void setUp()
    {

    }

    @Test
    public void testTraceAnnotation()
    {
        service.test();
        service.testWithParams("omid", new Long(1));
        service.testTraceDisable("null", null);
    }

}