package com.core.xml;

import com.core.member.MemberService;
import com.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class xmlConfigTest {

    ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    public void testWithXml() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


}
