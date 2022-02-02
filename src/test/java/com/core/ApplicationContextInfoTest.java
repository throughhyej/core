package com.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    // 스프링 컨테이너 생성
    AnnotationConfigApplicationContext annotAc = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("생성된 스프링 빈 조회")
    public void findAllBeans() {
        String[] beanDefinitionNames = annotAc.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = annotAc.getBean(beanDefinitionName);
            System.out.println("beanName = " + beanDefinitionName + ", Object = " + bean);
        }
    }

    @Test
    @DisplayName("스프링에서 생성한 스프링 빈 조회")
    public void findSpringBeans() {
        String[] beanDefinitionNames = annotAc.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // 빈 정의 getBeanDefinition(beanName)
            BeanDefinition beanDefinition = annotAc.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
                // Object bean = annotAc.getBean(beanDefinitionName);
                System.out.println("beanName = " + beanDefinitionName);
            }
        }
    }

    @Test
    @DisplayName("개발자가 등록한 스프링 빈 조회")
    public void findCustomSpringBean() {
        String[] beanDefinitionNames = annotAc.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // 빈 정의 getBeanDefinition(beanName)
            BeanDefinition beanDefinition = annotAc.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = annotAc.getBean(beanDefinitionName);
                System.out.println("beanName = " + beanDefinitionName + ", Object = " + bean);
            }

        }
    }

}
