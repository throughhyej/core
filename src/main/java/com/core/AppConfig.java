package com.core;

import com.core.discount.DiscountPolicy;
import com.core.discount.RateDiscountPolicy;
import com.core.member.MemberRepository;
import com.core.member.MemberService;
import com.core.member.MemberServiceImpl;
import com.core.member.MemoryMemberRepository;
import com.core.order.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// DI Container 역할 (IOC Container, 어셈블러, 오브젝트 팩토리 라고 부르기도 함)
// 애플리케이션 구성을 한 눈에 알아보기 쉽다.
// 설정 파일에서 DI를 설정함으로써 SOLID의 DIP, OCP 준수

@Configuration
public class AppConfig {

    @Bean /** 싱글톤으로 관리되는 빈 **/
    public MemberRepository memberRepository() {
        /** 회원 DB 확정 시, 변경 **/
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("call AppConfig.discountPolicy");
        /** 할인 정책 변경 시, 변경 **/
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
