package com.core.autowired;

import com.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredOptionTest {

    @Test
    public void optionTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(optionTest.class);
    }

    public static class optionTest {

        /* 테스트용 DI */

        @Autowired(required = false)
        public void requiredOption(Member member) {
            /* required = false 옵션
             * 자동 주입 대상인 Member가 bean에 등록되어 있지 않으면 requiredOption()이 호출되지 않는다.
             **/
            System.out.println("optionTest.requiredOption().member -> " + member);
        }

        @Autowired
        public void nullableOption(@Nullable Member member) {
            /* @Nullable 옵션
             * 자동 주입 대상인 Member가 bean에 등록되어 있지 않으면 member에는 null이 대입된다.
             **/
            System.out.println("optionTest.nullableOption().member -> " + member);
        }

        @Autowired
        public void java8Optional(Optional<Member> member) {
            /* Optional<> 옵션
             * 자동 주입 대상인 Member가 bean에 등록되어 있지 않으면 member에는 Optional.empty가 대입된다.
             **/
            System.out.println("optionTest.java8Optional().member -> " + member);
        }
    }

}
