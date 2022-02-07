package com.core.beanScope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    public void singletonBeanScope() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        System.out.println("singletonBean1 호출 전");
        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean2 호출 전");
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);

        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);

        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);
        ac.close();

        /* result (스프링 컨테이너에 의해, 인스턴스는 하나. init 한 번. 자동적으로 close 한 번)
         *
         * SingletonBean.init
         * singletonBean1 호출 전
         * singletonBean2 호출 전
         * singletonBean1 = com.core.beanScope.SingletonTest$SingletonBean@d41f816
         * singletonBean2 = com.core.beanScope.SingletonTest$SingletonBean@d41f816
         * 12:29:18.765 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@31fa1761, started on Mon Feb 07 12:29:18 KST 2022
         * SingletonBean.destroy
         **/

    }

    @Scope("singleton")
    public static class SingletonBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }

}
