package com.core.autowired;

import com.core.AutoAppconfig;
import com.core.discount.DiscountPolicy;
import com.core.member.Grade;
import com.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.AnnotationAsyncExecutionInterceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class selectAllBean {

    @Test
    public void selectAllBean() {
        /** 상황: 할인 정책을 회원이 선택하는 경우, Bean을 모두 조회한다. **/

        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppconfig.class, DiscountBeans.class);

        DiscountBeans discountBeans = ac.getBean(DiscountBeans.class);
        Member member = new Member(1L, "mamberA", Grade.VIP);

        int fixDiscountPrice = discountBeans.selectDiscountPolicy(member, 10000, "fixDiscountPolicy");
        int rateDiscountPrice = discountBeans.selectDiscountPolicy(member, 20000, "rateDiscountPolicy");

        Assertions.assertThat(fixDiscountPrice).isEqualTo(1000);
        Assertions.assertThat(rateDiscountPrice).isEqualTo(2000);

    }

    public static class DiscountBeans {
        private final Map<String, DiscountPolicy> discountPolicyMap;
        private final List<DiscountPolicy> discountPolicyList;

        @Autowired
        public DiscountBeans(Map<String, DiscountPolicy> discountPolicyMap, List<DiscountPolicy> discountPolicyList) {
            this.discountPolicyMap = discountPolicyMap;
            this.discountPolicyList = discountPolicyList;
        }

        public int selectDiscountPolicy(Member member, int price, String policy) {
            DiscountPolicy selectedPolicy = discountPolicyMap.get(policy);
            return selectedPolicy.discount(member, price);
        }
    }
}
