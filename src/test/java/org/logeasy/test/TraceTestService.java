package org.logeasy.test;

import org.logeasy.logs.Logger;
import org.logeasy.trace.LogLevel;
import org.logeasy.trace.Trace;

/**
 * @author omidp
 *
 */
public class TraceTestService 
{

    
    @Logger
    org.slf4j.Logger LOGGER;
    
    @Trace
    public void test()
    {
        LOGGER.info("TEST");
    }
    
    @Trace
    public void testWithParams(String username, Long userId)
    {
        LOGGER.info("TEST WITH PARAMS");
    }
    
    
    @Trace(enable=false, level=LogLevel.TRACE)
    public void testTraceDisable(String username, Long userId)
    {
        LOGGER.info("testTraceDisable");
    }
    
}
