package com.core.orders;

import com.core.members.Grade;
import com.core.members.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        return member.getGrade() == Grade.VIP ? discountFixAmount : 0;
    }
}
