package com.revature.quizzard.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.revature.quizzard")
@Import({MvcConfig.class, OrmConfig.class, AopConfig.class})
public class AppConfig {

}
