package com.core.order;

import com.core.member.Grade;
import com.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {

    public final int discountFixPercentage = 10;

    @Override
    public int discount(Member member, int price) {
        int discount = member.getGrade() == Grade.VIP ? price * discountFixPercentage / 100 : 0;
        return discount;
    }
}
