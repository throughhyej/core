package com.core.singleton;

import com.core.AppConfig;
import com.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BeforeSignletoneTest {

    @Test
    @DisplayName("new 키워드로 객체 생성 시, 객체 확인 (싱글톤 적용 전)")
    public void getInstance() {
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        /* com.core.member.MemberServiceImpl@ae13544 */
        System.out.println("memberService1 = " + memberService1);

        /* com.core.member.MemberServiceImpl@3d34d211 */
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }
}
