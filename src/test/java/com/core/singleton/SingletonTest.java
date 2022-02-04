package com.core.singleton;

import com.core.AppConfig;
import com.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
}
