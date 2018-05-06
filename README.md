# logeasy

Spring boot easy logging. logging is important to debug your app, this project helps you to do that easily.

# How to build 

```
mvn clean install
```

# How to config

```
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
```

# How to use

* add dependency 

```
<dependency>
	<groupId>org.omidbiz</groupId>
	<artifactId>logeasy</artifactId>
	<version>0.0.1</version>
</dependency>
```

# Example

```
public class WhateverService 
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

```

for more information please visit the test case.

# TO DO

* add spel 
* add spring boot Configurer
