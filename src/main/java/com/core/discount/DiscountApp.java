package com.core.discount;

import com.core.AppConfig;
import com.core.member.Grade;
import com.core.member.Member;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DiscountApp {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        DiscountPolicy discountPolicy = applicationContext.getBean("discountPolicy", DiscountPolicy.class);

        Member member = new Member(1L, "member1", Grade.VIP);
        int discount = discountPolicy.discount(member, 20000);

        System.out.println("VIP member: discount > " + discount);

        member = new Member(2L, "member2", Grade.BASIC);
        discount = discountPolicy.discount(member, 20000);

        System.out.println("BASIC member: discount > " + discount);

    }
}
