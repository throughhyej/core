package com.core.beanDefinition;

import com.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    // factory method를 통해 간접적으로 bean 등록
    // ex: definition = Generic bean: class [null]; factoryBeanName=appConfig; factoryMethodName=memberRepository;
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // 직접 bean 등록
    // ex: definition = Generic bean: class [com.core.member.MemberServiceImpl]; factoryBeanName=null; factoryMethodName=null;
    // GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    public void getBeanDefinition() {
        String[] beanNames = ac.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            /* ApplicationContext 인터페이스에는 getBeanDefinition(beanName)가 존재하지 않아 구현체 이용 */
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanName = " + beanName + ", definition = " + beanDefinition);
            }
        }
    }
}
