package com.core;

import com.core.member.MemberRepository;
import com.core.member.MemberService;
import com.core.member.MemberServiceImpl;
import com.core.member.MemoryMemberRepository;
import com.core.order.DiscountPolicy;
import com.core.order.FixDiscountPolicy;
import com.core.order.OrderService;
import com.core.order.OrderServiceImpl;

// 애플리케이션 구성을 한 눈에 알아보기 쉽다.
// 설정 파일에서 DI를 설정함으로써 SOLID의 DIP, OCP 준수k
public class AppConfig {

    public MemberRepository memberRepository() {
        /** 회원 DB 확정 시, 변경 **/
        return new MemoryMemberRepository();
    }

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        /** 할인 정책 변경 시, 변경 **/
        return new FixDiscountPolicy();
    }
}
