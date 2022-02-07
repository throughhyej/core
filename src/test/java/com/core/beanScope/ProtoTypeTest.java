package com.core.beanScope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class ProtoTypeTest {

    @Test
    public void prototypeBeanScope() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("prototypeBean1 호출 전");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean2 호출 전");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        ac.close(); // 스프링 컨테이너에 의해 init만 될 뿐, close가 되지 않음

        // prototype을 생성한 클라이언트가 해당 bean의 소멸을 책임쳐야 한다.
        prototypeBean1.destroy();
        prototypeBean2.destroy();

        /* result (호출될 때마다 인스턴스가 생성되므로 init도 호출시마다 실행.
         * 스프링 컨테이너가 bean 등록, 생성, 초기화까지 관여하지만 그 후로는 관리하지 않기 때문에
         * 소멸은 클라이언트가 직접 해주어야 한다.
         *
         * prototypeBean1 호출 전
         * PrototypeBean.init
         * prototypeBean2 호출 전
         * PrototypeBean.init
         * prototypeBean1 = com.core.beanScope.ProtoTypeTest$PrototypeBean@1800a575
         * prototypeBean2 = com.core.beanScope.ProtoTypeTest$PrototypeBean@1458ed9c
         * 12:44:33.059 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@31fa1761, started on Mon Feb 07 12:44:32 KST 2022
         * PrototypeBean.destroy
         * PrototypeBean.destroy
         **/

    }

    @Scope("prototype")
    public static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

}
