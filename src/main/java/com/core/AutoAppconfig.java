package com.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan (
        basePackages = {},
        includeFilters = {},
        /** 현재까지 작성된 예제 코드들로부터 영향을 받지 않기 위해 스캔 대상 제외 **/
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppconfig {

    /* 자동 bean 등록 */

    /* @ComponentScan: @Componant가 부여된 [구현 클래스]를 bean으로 등록하기 위해 찾는데, 찾는 범위를 지정한다.
     *                 default: @ComponentScan이 붙은 패키지의 하위 <-> basePackages: 지정한 범위
     *                 따라서, basc package 바로 하위에 설정 파일을 위치시키는 게 좋다.
     *
     *                 includeFilters: 찾는 범위에 포함되어 있지 않아도 포함하는 설정
     *                 excludeFilters: 찾는 범위에 포함되어도 제외하는 설정
     *
     * @Componant는 구현 클래스에 부여해야 하며, DI 부분에 @Autowired 키워드를 붙여주어야 한다.
     * -> 스프링 bean에서만 @Autowired 키워드가 동작한다. 그 외 클래스에서는 무의미하다.
     * -> 여기서는 [DI 부분]은 생성자 (생성자가 하나일 때는 @Autowired 생략 가능)
     * AppConfig.java 를 통해 직접 구현할 때에는 해당 로직에서 DI를 처리해주었지만,
     * @Componant 키워드 만으로는 DI 관계를 파악하지 못하기 떄문에 @Autowired 처리를 통해 의존성 주입을 완성해준다.
     *
     * 아래 어노테이션은 @Component를 포함하고 있어 Bean 등록이 됨
     * @Controller: 스프링 MVC 컨트롤러로 인식
     * @Service: 특벽한 의미 없음, 개발자들에게 핵심 비즈니스 로직이 포함되어 있는 비즈니스 계층을 인식시킴
     * @Repository: 스프링 데이터 접근 계층으로 인식. 데이터 계층의 예외를 스프링 예외로 변환
     * @Configuration: 스프링 설정 정보로 인식. 스프링 bean이 싱글톤을 유지시킴
     *
     *
     * DI 방법
     * 1. 생성자 주입 방식: 생성자 호출 시점에 1번만 호출됨을 보장, 불변/필수 의존관계에 사용
     * 2. setter(수정자) 주입 방식: 자바빈 프로퍼티 규약의 수정자 메서드(setter)를 사용, 선택/변경 가능성이 있는 의존관계에서 사용
     *                          자동 주입 대상이 없으면 수정자 메서드 자체가 호출이 되지 않아 예외 미발생
     * 3. 필드 주입 방식: 외부에서 변경이 불가능하여 테스트가 불편하다. 테스트 코드, @Configuration 클래스 외에는 비권장*
     *                 DI 프레임워크 없이 순수 자바 코드로 테스트 코드 작성 등이 불가
     * 4. 일반 메서드 주입 방식: 한 번에 여러 필드를 주입 받을 수 있으나 일반적으로 잘 사용하지 않음.
     *
     *
     * 자동 주입 bean 조회 시 타입으로 조회하게 되는데
     * 이 때, 같은 타입의 구현체가 2개 이상 bean으로 등록되어 있는 경우 NoUniqueBeanDefinitionException 발생
     * -> TEST를 위해 FixDiscountPolicy에 @Compontant 부여하여 bean으로 등록
     * -> 테스트 케이스 돌리면 autoAppconfigTest.java에서 에러 발생
     *
     * 1. @Autowired 이용
     *    - @Autowired는 타입으로 bean을 조회하고, 2개 이상의 bean이 조회되면 추가로 필드명을 매칭시킨다.
     *      OrderServiceImpl.java bean 의존성 주입 부분 수정
     *      public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {}
     *      -> 수정이 생길 때 비즈니스 로직을 바꿔야해서 불편할 것 같음
     *
     * 2. @Qualifier 이용
     *    - 추가 구분자를 매핑시키는 방법
     *      각 bean에 @Qualifier("rateDiscountPolicy"), @Qualifier("fixDiscountPolicy") 부여 후
     *      OrderServiceImpl.java bean 의존성 주입 부분 수정
     *      public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("rateDiscountPolicy") DiscountPolicy discountPolicy) { }
     *      -> 수정이 생길 때 비즈니스 로직 수정 + bean에 @Qualifier 부여해야 해서 불편할 것 같음
     *
     * 3. @Primary 이용
     *    - 사용할 bean에 @Primary 추가
     *
     *  ** 활용 예:
     *      코드에서 자주 사용하는 메인 데이터베이스의 커넥션을 획득하는 스프링 빈 (@Primary)
     *      코드에서 특별한 기능으로 가끔 사용하는 서브 데이터베이스 커넥션을 획득하는 스프링 빈 (@Qualifier)
     *
     *  ** @Qualifier가 @Primary 보다 우선순위를 갖는다.
     *
     **/

}
