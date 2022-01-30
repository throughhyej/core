package com.core.orders;

import com.core.members.Grade;
import com.core.members.Member;
import com.core.members.MemberService;
import com.core.members.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        memberService.join(new Member(1L, "memberA", Grade.VIP));
        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
