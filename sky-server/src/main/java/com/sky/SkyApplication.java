package com.sky;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication //@SpringBootApplication是一个复合注解，用于标记Spring Boot应用的主类。它包含了以下三个注解的功能：
//@Configuration：表明该类是一个配置类，可以在其中定义Bean。
//@ComponentScan：启用组件扫描，自动扫描并注册Spring Bean。
//@EnableAutoConfiguration：开启自动配置功能，根据项目的依赖配置来自动配置Spring应用。
@EnableTransactionManagement //开启注解方式的事务管理通过该注解，可以在Service层的方法上使用@Transactional注解来声明事务，实现事务管理功能。
@Slf4j
@EnableCaching //通过该注解，可以在Service层的方法上使用@Cacheable、@CachePut、@CacheEvict等注解来实现方法级别的缓存。
@EnableScheduling //通过该注解，可以在方法上使用@Scheduled注解来指定方法的执行时间，实现定时任务的功能。
public class SkyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkyApplication.class, args);
        log.info("server started");
    }
}
