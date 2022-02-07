package com.core.beanScope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest {

    /* prototype은 호출될 때마다 새로운 인스턴스를 생성하는 스코프인데,
     * 아래처럼 단순하게 싱글톤 클래스가 참조변수로 갖고 있게 하면 prototype도 싱글톤이 되어버린다.
     * int singletonCount2 = singleton2.logic(); 도 1이 되어야 하는데, 여기서는 2를 반환한다.
     *
     * 싱글톤 클래스의 참조변수인 prototype이 의도한대로 prototype scope를 갖게 하려면
     * 1. spring: ObjectProvider<T> 사용
     * 2. java 표준: Provider<T> 사용
     *
     **/

    @Test
    public void singletonWithPrototypeTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Singleton.class, Prototype.class);
        Singleton singleton1 = ac.getBean(Singleton.class);
        int singletonCount1 = singleton1.logic();
        Singleton singleton2 = ac.getBean(Singleton.class);
        int singletonCount2 = singleton2.logic();

        System.out.println("singleton1 = " + singleton1);
        System.out.println("singleton2 = " + singleton2);

        Assertions.assertThat(singletonCount1).isEqualTo(1);
        // Assertions.assertThat(singletonCount2).isEqualTo(2); // 1. 일 때 결과
        Assertions.assertThat(singletonCount2).isEqualTo(1); // 2/3. 일 때 결과

        ac.close();
    }

    public static class Singleton {
        // 1. singleton처럼 동작
        // private Prototype prototype;

        // @Autowired
        // public Singleton(Prototype prototype) {
        //     this.prototype = prototype;
        // }

        // 2. Spring: ObjectProvider<T> 사용
        // 객체 인스턴스를 찾아서 반환해준다. (prototype의 경우 생성해서 반환)
        // @Autowired
        // private ObjectProvider<Prototype> provider;

        // 3. Java표준: Provider<T> 사용
        // 객체 인스턴스를 찾아서 반환해준다. (prototype의 경우 생성해서 반환)
        @Autowired
        private Provider<Prototype> provider;

        public int logic() {
            // Prototype prototype = provider.getObject(); // 2. 일 때 로직
            Prototype prototype = provider.get(); // 3. 일 때 로직
            System.out.println("prototype = " + prototype);
            prototype.addCount();
            return prototype.getCount();
        }
    }

    @Scope("prototype")
    public static class Prototype {
        private int count = 0;
        public void addCount() {
            count++;
        }
        public int getCount() {
            return count;
        }
        @PostConstruct
        public void init() {
            System.out.println("Prototype.init");
        }
        @PreDestroy
        public void destroy() {
            System.out.println("Prototype.destroy");
        }
    }

}
