package com.core.java;

import com.core.discount.RateDiscountPolicy;
import com.core.member.Grade;
import com.core.member.Member;
import com.core.member.MemoryMemberRepository;
import com.core.order.Order;
import com.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JavaTest {

    @Test
    @DisplayName("순수 자바 테스트")
    public void javaTest() {
        /* OrderServiceImpl.java 로직 테스트를 스프링 없이 순수 자바로 테스트
         *  > 생성자 주입 방식을 이용하기 때문에 순수하게 자바로 테스트가 가능하다.
         **/

        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "memberA", Grade.VIP));
        memberRepository.save(new Member(2L, "memberB", Grade.BASIC));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new RateDiscountPolicy());
        Order orderA = orderService.createOrder(1L, "ITEM1", 10000);
        Order orderB = orderService.createOrder(2L, "ITEM1", 10000);

        Assertions.assertThat(orderA.getDiscountPrice()).isEqualTo(1000);
        Assertions.assertThat(orderB.getDiscountPrice()).isEqualTo(0);

    }
}
