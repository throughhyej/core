package com.core.order;

import com.core.AppConfig;
import com.core.member.Grade;
import com.core.member.Member;
import com.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Member memberA = new Member(1L, "memberA", Grade.BASIC);
        memberService.join(memberA);
        Member memberB = new Member(2L, "memberB", Grade.VIP);
        memberService.join(memberB);

        Order itemA = orderService.createOrder(memberA.getId(), "itemA", 10000);
        Order itemB = orderService.createOrder(memberB.getId(), "itemB", 20000);
        System.out.println(itemA + "\n" + itemB);

        System.out.println("itemA 할인 금액 > " + itemA.getDiscountPrice() + ", " + "itemA 결제 가격 > " + itemA.calculatePrice());
        System.out.println("itemB 할인 금액 > " + itemB.getDiscountPrice() + ", " + "itemB 결제 가격 > " + itemB.calculatePrice());

    }
}
