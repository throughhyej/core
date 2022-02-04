package com.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("new 키워드로 객체 생성 시, 객체 확인 (싱글톤 적용 후)")
    public void getInstance() {
        // SingletonService()' has private access in 'com.core.singleton.SingletonService
        // private 생성자로 인해 아래처럼 생성 불가
        // new SingletonService();

        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // com.core.singleton.SingletonService@20d525
        System.out.println("singletonService1 = " + singletonService1);
        // com.core.singleton.SingletonService@20d525
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);

    }
}
