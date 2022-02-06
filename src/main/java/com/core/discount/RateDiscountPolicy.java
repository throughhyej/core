package com.core.discount;

import com.core.annotation.MainDiscountPolicy;
import com.core.member.Grade;
import com.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
//@Primary
//@Qualifier("rateDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy {

    public final int discountFixPercentage = 10;

    @Override
    public int discount(Member member, int price) {
        int discount = member.getGrade() == Grade.VIP ? price * discountFixPercentage / 100 : 0;
        return discount;
    }
}
