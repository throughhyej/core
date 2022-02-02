package com.core.member;

import com.core.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

public class MemberApp {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        Member member1 = new Member(1L, "Member1", Grade.VIP);
        memberService.join(member1);
        Member member2 = new Member(2L, "Member2", Grade.BASIC);
        memberService.join(member2);

        Member findMember = memberService.findMember(member1.getId());
        System.out.println("member1 == findMember > " + (member1 == findMember));

    }
}
