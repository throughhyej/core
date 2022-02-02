package com.core.findBeans;

import com.core.AppConfig;
import com.core.member.MemberService;
import com.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindBean {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    public void findbyBeanName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 타입으로 조회")
    // 같은 타입이 여러개일 때 에러 발생 -> 빈 이름 지정 필요 (ApplicationContextSameBeanFindTest 참고)
    public void findByBeanType() {
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구현체로 조회")
    // DIP 위반이지만 정말 필요할 때 사용
    public void findByBeanImpl() {
        MemberServiceImpl memberServiceImpl = ac.getBean(MemberServiceImpl.class);
        Assertions.assertThat(memberServiceImpl).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("실패 테스트 케이스")
    public void failCase() {
        // MemberService bean = ac.getBean("abcde", MemberService.class);
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                                    () -> ac.getBean("abcde", MemberService.class));
    }

}
