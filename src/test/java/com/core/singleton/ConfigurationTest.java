package com.core.singleton;

import com.core.AppConfig;
import com.core.member.MemberRepository;
import com.core.member.MemberServiceImpl;
import com.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationTest {
    /* AppConfig.java의 @Configuration
     *
     * AppConfig.java를 보면
     * 여러 번 호출되고, 호출될 때마다 new 키워드로 객체를 생성한다.
     * 이 때, 싱글톤이 깨지는지 아닌지 확인하는 테스트이다.
     **/

    @Test
    @DisplayName("싱글톤이 유지되는지 테스트")
    public void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        /* 아래 두 개의 빈을 가져올 때, new MemberRepository() 됨
         * 구현 클래스로 뽑는 로직은 좋지 않으나, 테스트를 위해 어쩔 수 없이 구현체 클래스 이용
         * 구현 클래스에만 getMemberRepository() 생성했기 때문이다.
         **/
         MemberServiceImpl memberServiceImpl = ac.getBean("memberService", MemberServiceImpl.class);
         OrderServiceImpl orderServiceImpl = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberService_repository = memberServiceImpl.getMemberRepository();
        MemberRepository orderService_repository = orderServiceImpl.getMemberRepository();
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        Assertions.assertThat(memberRepository).isSameAs(memberService_repository);
        Assertions.assertThat(memberRepository).isSameAs(orderService_repository);


        /* AppConfig.java의 스프링 빈에 대해 soutm을 남겨둔 상태: 호출될 때마다 생성되는지 확인하기 위함
         * 메모리에 올라갈 때 각 한 번씩만 호출됨 (여러 번 호출되지 않음을 확인)
         **/

        // call AppConfig.memberRepository
        // call AppConfig.memberService
        // call AppConfig.orderService
        // call AppConfig.discountPolicy

        /* AppConfig.java의 @Configuration을 제거하면,
         * bean 등록은 가능하지만, 싱글톤을 보장해주지 못한다.
         *
         * @Configuration 사용 시,
         * new AnnotationConfigApplicationContext(AppConfig.class); 로 초기화 할 때,
         * 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 이용해서
         * AppConfig.java를 상속받는 클래스를 생성하고 그 안의 것들을 Bean으로 등록
         * .getBean(AppConfig.class); 결과: bean = class hello.core.AppConfig$$EnhancerBySpringCGLB$$....
         *
         * @Configuration 미사용 시,
         * .getBean(AppConfig.class); 결과: bean = class hello.core.AppConfig
         * AppConfig.java 소스 그대로 new로 객체 생성이 되어 MemberRepository의 주소값이 제각각이 된다.
         *
         **/

    }
}
