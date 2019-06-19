package by.epam.kvirykashvili.javalabtask.service.config;

import by.epam.kvirykashvili.javalabtask.config.AppConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AppConfig.class)
@ComponentScan("by.epam.kvirykashvili.javalabtask.service")
public class ServiceConfig {

}
