package com.core.findBeans;

import com.core.AppConfig;
import com.core.member.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ConfigurationConfig.class);

    @Test
    @DisplayName("같은 타입의 빈 여러 개 존재 시 실패 테스트 케이스")
    public void findByType() {
        // MemberRepository bean = ac.getBean(MemberRepository.class); // NoUniqueBeanDefinitionException 발생
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("같은 타입의 빈 여러 개 존재 시 테스트 케이스")
    public void findByName() {
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemoryMemberRepository.class);
    }

    @Test
    @DisplayName("같은 타입의 빈 여러 개 존재 시, 모두 조회")
    public void findAllBeanByType() {
        Map<String, MemberRepository> beans = ac.getBeansOfType(MemberRepository.class);
        for (String key : beans.keySet()) {
            System.out.println("key = " + key + ", value = " + beans.get(key));
        }
        System.out.println("beans > " + beans);
        org.assertj.core.api.Assertions.assertThat(beans.size()).isEqualTo(2);
    }

    @Configuration
    public static class ConfigurationConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }

}
