package com.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class lifecycleTest {

    @Test
    public void springLifecycleTest() {
        /** 권장하지 않는 구기술
         ** spring에 의존적, 메서드 이름 변경 불가, 외부 라이브러리에 적용 불가
         **/
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(Config1.class);
        ac.close();
    }

    public static class Config1 {
        @Bean
        public UseSpring useSpring() {
            UseSpring useSpring = new UseSpring();
            useSpring.setUrl("useSpring > http://hello-spring:dev");
            return useSpring;
        }
    }

    @Test
    public void beanLifecycleTest() {
        /** 외부 라이브러리 close() 시 용이:
         ** destroyMethod = "(deferred)" default 값이어서 close, shutdown 등 추측 method로 종료
         ** init, close 메소드 이름 변경 가능
         ** 스프링 bean이 스프링 코드에 의존하지 않음
         **/
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(Config2.class);
        ac.close();
    }

    public static class Config2 {
        @Bean(initMethod = "init") // destroyMethod = "close", destroyMethod = ""
        public UseBean useBean() {
            UseBean useBean = new UseBean();
            useBean.setUrl("useBean > http://hello-spring:dev");
            return useBean;
        }
    }

    @Test
    public void annotationLifecycleTest() {
        /** 권장 방법: Java 표준이어서 스프링 외 컨테이너에서도 동작
         ** 단, 외부 라이브러리에 사용 불가하여 외부 라이브러리에 적용해야 할 경우 @Bean() 옵션 이용 **/
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(Config3.class);
        ac.close();
    }

    public static class Config3 {
        @Bean
        public UseAnnotation useAnnotation() {
            UseAnnotation useAnnotation = new UseAnnotation();
            useAnnotation.setUrl("useAnnotation > http://hello-spring:dev");
            return useAnnotation;
        }
    }

}
