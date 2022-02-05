package com.core.scan;

import com.core.AutoAppconfig;
import com.core.member.MemberService;
import com.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class AutoAppconfigTest {

    @Test
    public void autoAppconfigTest() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppconfig.class);
        MemberService memberService = ac.getBean(MemberService.class); /* name 없이 타입으로만 조회 필요 */
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

        // Creating shared instance of singleton bean 'autoAppconfig'
        // Creating shared instance of singleton bean 'rateDiscountPolicy'
        // Creating shared instance of singleton bean 'memberServiceImpl'
        // Creating shared instance of singleton bean 'memoryMemberRepository'
        // Autowiring by type from bean name 'memberServiceImpl' via constructor to bean named 'memoryMemberRepository'
        // Creating shared instance of singleton bean 'orderServiceImpl'
        // Autowiring by type from bean name 'orderServiceImpl' via constructor to bean named 'memoryMemberRepository'
        // Autowiring by type from bean name 'orderServiceImpl' via constructor to bean named 'rateDiscountPolicy'
    }
}