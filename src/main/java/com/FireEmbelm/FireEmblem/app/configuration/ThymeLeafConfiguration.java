package com.FireEmbelm.FireEmblem.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeLeafConfiguration {

    @Bean
    @Primary
    @Scope("singleton")
    public ITemplateResolver getTemplateResolver() {
        ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();
        classLoaderTemplateResolver.setTemplateMode(TemplateMode.HTML);
        classLoaderTemplateResolver.setSuffix(".html");
        classLoaderTemplateResolver.setPrefix("/templates/");
        classLoaderTemplateResolver.setCharacterEncoding("UTF-8");
        return classLoaderTemplateResolver;
    }

    @Bean
    @Primary
    @Scope("singleton")
    public ITemplateEngine getTemplateEngine(ITemplateResolver iTemplateResolver) {
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(iTemplateResolver);
        templateEngine.setDialect(new Java8TimeDialect());
        return templateEngine;
    }

}
