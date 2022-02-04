package com.core.singleton;

import com.core.AppConfig;
import com.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class SingletonTest {

    @Test
    @DisplayName("new 키워드로 객체 생성 시, 객체 확인 (싱글톤 적용 후)")
    public void getInstance() {
        // SingletonService()' has private access in 'com.core.singleton.SingletonService
        // private 생성자로 인해 아래처럼 생성 불가
        // new SingletonService();

        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // com.core.singleton.SingletonService@20d525
        System.out.println("singletonService1 = " + singletonService1);
        // com.core.singleton.SingletonService@20d525
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);

    }

    @Test
    @DisplayName("스프링 컨테이너 적용 후 객체 확인")
    public void springConatainer() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // com.core.member.MemberServiceImpl@2c7b5824
        System.out.println("memberService1 = " + memberService1);
        // com.core.member.MemberServiceImpl@2c7b5824
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);

    }

    @Test
    @DisplayName("Stateful, 상태유지로 설계된 싱글톤 객체 사용 시 문제점")
    public void useStatefulSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(Singleton.class);
        StatefulSingleton statefulSingleton1 = ac.getBean("statefulSingleton", StatefulSingleton.class);
        StatefulSingleton statefulSingleton2 = ac.getBean("statefulSingleton", StatefulSingleton.class);

        statefulSingleton1.order("order1", 10000);
        statefulSingleton2.order("order2", 20000);

        /* statefulSingleton1의 price는 10000인데, 20000이 됨 */
        System.out.println("statefulSingleton1.getPrice() : " + statefulSingleton1.getPrice());

        Assertions.assertThat(statefulSingleton1).isSameAs(statefulSingleton2);

    }

    @Test
    @DisplayName("Stateful -> Stateless 수정")
    public void useStatelessSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(Singleton.class);
        StatelessSingleton statelessSingleton1 = ac.getBean("statelessSingleton", StatelessSingleton.class);
        StatelessSingleton statelessSingleton2 = ac.getBean("statelessSingleton", StatelessSingleton.class);

        int price1 = statelessSingleton1.order("order1", 10000);
        int price2 = statelessSingleton2.order("order2", 20000);

        System.out.println("price1 = " + price1);

        Assertions.assertThat(statelessSingleton1).isSameAs(statelessSingleton2);
        Assertions.assertThat(price1).isEqualTo(10000);
    }

    public static class Singleton {
        @Bean
        public StatefulSingleton statefulSingleton() {
            return new StatefulSingleton();
        }

        @Bean
        public StatelessSingleton statelessSingleton() {
            return new StatelessSingleton();
        }
    }
}
