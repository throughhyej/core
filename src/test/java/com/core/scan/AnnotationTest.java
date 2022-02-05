package com.core.scan;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

public class AnnotationTest {

    @Test
    public void annotationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        // Creating shared instance of singleton bean 'annotationTest.Config'
        // Creating shared instance of singleton bean 'beanA'
        // BeanB는 제외되어 bean 등록이 되지 않음
    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = IncludeAnnotation.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ExcludeAnnotation.class)
                             // BeanA도 따로 제외시키고 싶다면
                             // , @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class)
    )
    public static class Config {

    }
}
